/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Entidades.Orden;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Usuarios;
import Entidades.OrdenHasPlatillo;
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
        if (orden.getOrdenHasPlatilloList() == null) {
            orden.setOrdenHasPlatilloList(new ArrayList<OrdenHasPlatillo>());
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
            List<OrdenHasPlatillo> attachedOrdenHasPlatilloList = new ArrayList<OrdenHasPlatillo>();
            for (OrdenHasPlatillo ordenHasPlatilloListOrdenHasPlatilloToAttach : orden.getOrdenHasPlatilloList()) {
                ordenHasPlatilloListOrdenHasPlatilloToAttach = em.getReference(ordenHasPlatilloListOrdenHasPlatilloToAttach.getClass(), ordenHasPlatilloListOrdenHasPlatilloToAttach.getOrdenHasPlatilloPK());
                attachedOrdenHasPlatilloList.add(ordenHasPlatilloListOrdenHasPlatilloToAttach);
            }
            orden.setOrdenHasPlatilloList(attachedOrdenHasPlatilloList);
            em.persist(orden);
            if (idusuario != null) {
                idusuario.getOrdenList().add(orden);
                idusuario = em.merge(idusuario);
            }
            for (OrdenHasPlatillo ordenHasPlatilloListOrdenHasPlatillo : orden.getOrdenHasPlatilloList()) {
                Orden oldOrdenOfOrdenHasPlatilloListOrdenHasPlatillo = ordenHasPlatilloListOrdenHasPlatillo.getOrden();
                ordenHasPlatilloListOrdenHasPlatillo.setOrden(orden);
                ordenHasPlatilloListOrdenHasPlatillo = em.merge(ordenHasPlatilloListOrdenHasPlatillo);
                if (oldOrdenOfOrdenHasPlatilloListOrdenHasPlatillo != null) {
                    oldOrdenOfOrdenHasPlatilloListOrdenHasPlatillo.getOrdenHasPlatilloList().remove(ordenHasPlatilloListOrdenHasPlatillo);
                    oldOrdenOfOrdenHasPlatilloListOrdenHasPlatillo = em.merge(oldOrdenOfOrdenHasPlatilloListOrdenHasPlatillo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orden orden) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orden persistentOrden = em.find(Orden.class, orden.getIdorden());
            Usuarios idusuarioOld = persistentOrden.getIdusuario();
            Usuarios idusuarioNew = orden.getIdusuario();
            List<OrdenHasPlatillo> ordenHasPlatilloListOld = persistentOrden.getOrdenHasPlatilloList();
            List<OrdenHasPlatillo> ordenHasPlatilloListNew = orden.getOrdenHasPlatilloList();
            List<String> illegalOrphanMessages = null;
            for (OrdenHasPlatillo ordenHasPlatilloListOldOrdenHasPlatillo : ordenHasPlatilloListOld) {
                if (!ordenHasPlatilloListNew.contains(ordenHasPlatilloListOldOrdenHasPlatillo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdenHasPlatillo " + ordenHasPlatilloListOldOrdenHasPlatillo + " since its orden field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                orden.setIdusuario(idusuarioNew);
            }
            List<OrdenHasPlatillo> attachedOrdenHasPlatilloListNew = new ArrayList<OrdenHasPlatillo>();
            for (OrdenHasPlatillo ordenHasPlatilloListNewOrdenHasPlatilloToAttach : ordenHasPlatilloListNew) {
                ordenHasPlatilloListNewOrdenHasPlatilloToAttach = em.getReference(ordenHasPlatilloListNewOrdenHasPlatilloToAttach.getClass(), ordenHasPlatilloListNewOrdenHasPlatilloToAttach.getOrdenHasPlatilloPK());
                attachedOrdenHasPlatilloListNew.add(ordenHasPlatilloListNewOrdenHasPlatilloToAttach);
            }
            ordenHasPlatilloListNew = attachedOrdenHasPlatilloListNew;
            orden.setOrdenHasPlatilloList(ordenHasPlatilloListNew);
            orden = em.merge(orden);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getOrdenList().remove(orden);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getOrdenList().add(orden);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (OrdenHasPlatillo ordenHasPlatilloListNewOrdenHasPlatillo : ordenHasPlatilloListNew) {
                if (!ordenHasPlatilloListOld.contains(ordenHasPlatilloListNewOrdenHasPlatillo)) {
                    Orden oldOrdenOfOrdenHasPlatilloListNewOrdenHasPlatillo = ordenHasPlatilloListNewOrdenHasPlatillo.getOrden();
                    ordenHasPlatilloListNewOrdenHasPlatillo.setOrden(orden);
                    ordenHasPlatilloListNewOrdenHasPlatillo = em.merge(ordenHasPlatilloListNewOrdenHasPlatillo);
                    if (oldOrdenOfOrdenHasPlatilloListNewOrdenHasPlatillo != null && !oldOrdenOfOrdenHasPlatilloListNewOrdenHasPlatillo.equals(orden)) {
                        oldOrdenOfOrdenHasPlatilloListNewOrdenHasPlatillo.getOrdenHasPlatilloList().remove(ordenHasPlatilloListNewOrdenHasPlatillo);
                        oldOrdenOfOrdenHasPlatilloListNewOrdenHasPlatillo = em.merge(oldOrdenOfOrdenHasPlatilloListNewOrdenHasPlatillo);
                    }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<OrdenHasPlatillo> ordenHasPlatilloListOrphanCheck = orden.getOrdenHasPlatilloList();
            for (OrdenHasPlatillo ordenHasPlatilloListOrphanCheckOrdenHasPlatillo : ordenHasPlatilloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Orden (" + orden + ") cannot be destroyed since the OrdenHasPlatillo " + ordenHasPlatilloListOrphanCheckOrdenHasPlatillo + " in its ordenHasPlatilloList field has a non-nullable orden field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuarios idusuario = orden.getIdusuario();
            if (idusuario != null) {
                idusuario.getOrdenList().remove(orden);
                idusuario = em.merge(idusuario);
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
