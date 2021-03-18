/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Orden;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Usuarios;
import Entidades.Platillo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jbran
 */
public class OrdenJpaController implements Serializable {

    public OrdenJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PV_LaYaquesitaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orden orden) {
        if (orden.getPlatilloList() == null) {
            orden.setPlatilloList(new ArrayList<Platillo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios idusuario = orden.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                orden.setIdusuario(idusuario);
            }
            List<Platillo> attachedPlatilloList = new ArrayList<Platillo>();
            for (Platillo platilloListPlatilloToAttach : orden.getPlatilloList()) {
                platilloListPlatilloToAttach = em.getReference(platilloListPlatilloToAttach.getClass(), platilloListPlatilloToAttach.getIdplatillo());
                attachedPlatilloList.add(platilloListPlatilloToAttach);
            }
            orden.setPlatilloList(attachedPlatilloList);
            em.persist(orden);
            if (idusuario != null) {
                idusuario.getOrdenList().add(orden);
                idusuario = em.merge(idusuario);
            }
            for (Platillo platilloListPlatillo : orden.getPlatilloList()) {
                platilloListPlatillo.getOrdenList().add(orden);
                platilloListPlatillo = em.merge(platilloListPlatillo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orden orden) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orden persistentOrden = em.find(Orden.class, orden.getIdorden());
            Usuarios idusuarioOld = persistentOrden.getIdusuario();
            Usuarios idusuarioNew = orden.getIdusuario();
            List<Platillo> platilloListOld = persistentOrden.getPlatilloList();
            List<Platillo> platilloListNew = orden.getPlatilloList();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                orden.setIdusuario(idusuarioNew);
            }
            List<Platillo> attachedPlatilloListNew = new ArrayList<Platillo>();
            for (Platillo platilloListNewPlatilloToAttach : platilloListNew) {
                platilloListNewPlatilloToAttach = em.getReference(platilloListNewPlatilloToAttach.getClass(), platilloListNewPlatilloToAttach.getIdplatillo());
                attachedPlatilloListNew.add(platilloListNewPlatilloToAttach);
            }
            platilloListNew = attachedPlatilloListNew;
            orden.setPlatilloList(platilloListNew);
            orden = em.merge(orden);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getOrdenList().remove(orden);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getOrdenList().add(orden);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Platillo platilloListOldPlatillo : platilloListOld) {
                if (!platilloListNew.contains(platilloListOldPlatillo)) {
                    platilloListOldPlatillo.getOrdenList().remove(orden);
                    platilloListOldPlatillo = em.merge(platilloListOldPlatillo);
                }
            }
            for (Platillo platilloListNewPlatillo : platilloListNew) {
                if (!platilloListOld.contains(platilloListNewPlatillo)) {
                    platilloListNewPlatillo.getOrdenList().add(orden);
                    platilloListNewPlatillo = em.merge(platilloListNewPlatillo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orden.getIdorden();
                if (findOrden(id) == null) {
                    throw new NonexistentEntityException("The orden with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orden orden;
            try {
                orden = em.getReference(Orden.class, id);
                orden.getIdorden();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orden with id " + id + " no longer exists.", enfe);
            }
            Usuarios idusuario = orden.getIdusuario();
            if (idusuario != null) {
                idusuario.getOrdenList().remove(orden);
                idusuario = em.merge(idusuario);
            }
            List<Platillo> platilloList = orden.getPlatilloList();
            for (Platillo platilloListPlatillo : platilloList) {
                platilloListPlatillo.getOrdenList().remove(orden);
                platilloListPlatillo = em.merge(platilloListPlatillo);
            }
            em.remove(orden);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orden> findOrdenEntities() {
        return findOrdenEntities(true, -1, -1);
    }

    public List<Orden> findOrdenEntities(int maxResults, int firstResult) {
        return findOrdenEntities(false, maxResults, firstResult);
    }

    private List<Orden> findOrdenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orden.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Orden findOrden(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orden.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orden> rt = cq.from(Orden.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
