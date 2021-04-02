/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Orden;
import Entidades.OrdenHasPlatillo;
import Entidades.Platillo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jbran
 */
public class OrdenHasPlatilloJpaController implements Serializable {

    public OrdenHasPlatilloJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PV_LaYaquesitaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdenHasPlatillo ordenHasPlatillo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orden orden = ordenHasPlatillo.getOrden();
            if (orden != null) {
                orden = em.getReference(orden.getClass(), orden.getIdorden());
                ordenHasPlatillo.setOrden(orden);
            }
            Platillo platillo = ordenHasPlatillo.getPlatillo();
            if (platillo != null) {
                platillo = em.getReference(platillo.getClass(), platillo.getIdplatillo());
                ordenHasPlatillo.setPlatillo(platillo);
            }
            em.persist(ordenHasPlatillo);
            if (orden != null) {
                orden.getOrdenHasPlatilloList().add(ordenHasPlatillo);
                orden = em.merge(orden);
            }
            if (platillo != null) {
                platillo.getOrdenHasPlatilloList().add(ordenHasPlatillo);
                platillo = em.merge(platillo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdenHasPlatillo ordenHasPlatillo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdenHasPlatillo persistentOrdenHasPlatillo = em.find(OrdenHasPlatillo.class, ordenHasPlatillo.getIdOrdenHasPlatillo());
            Orden ordenOld = persistentOrdenHasPlatillo.getOrden();
            Orden ordenNew = ordenHasPlatillo.getOrden();
            Platillo platilloOld = persistentOrdenHasPlatillo.getPlatillo();
            Platillo platilloNew = ordenHasPlatillo.getPlatillo();
            if (ordenNew != null) {
                ordenNew = em.getReference(ordenNew.getClass(), ordenNew.getIdorden());
                ordenHasPlatillo.setOrden(ordenNew);
            }
            if (platilloNew != null) {
                platilloNew = em.getReference(platilloNew.getClass(), platilloNew.getIdplatillo());
                ordenHasPlatillo.setPlatillo(platilloNew);
            }
            ordenHasPlatillo = em.merge(ordenHasPlatillo);
            if (ordenOld != null && !ordenOld.equals(ordenNew)) {
                ordenOld.getOrdenHasPlatilloList().remove(ordenHasPlatillo);
                ordenOld = em.merge(ordenOld);
            }
            if (ordenNew != null && !ordenNew.equals(ordenOld)) {
                ordenNew.getOrdenHasPlatilloList().add(ordenHasPlatillo);
                ordenNew = em.merge(ordenNew);
            }
            if (platilloOld != null && !platilloOld.equals(platilloNew)) {
                platilloOld.getOrdenHasPlatilloList().remove(ordenHasPlatillo);
                platilloOld = em.merge(platilloOld);
            }
            if (platilloNew != null && !platilloNew.equals(platilloOld)) {
                platilloNew.getOrdenHasPlatilloList().add(ordenHasPlatillo);
                platilloNew = em.merge(platilloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ordenHasPlatillo.getIdOrdenHasPlatillo();
                if (findOrdenHasPlatillo(id) == null) {
                    throw new NonexistentEntityException("The ordenHasPlatillo with id " + id + " no longer exists.");
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
            OrdenHasPlatillo ordenHasPlatillo;
            try {
                ordenHasPlatillo = em.getReference(OrdenHasPlatillo.class, id);
                ordenHasPlatillo.getIdOrdenHasPlatillo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenHasPlatillo with id " + id + " no longer exists.", enfe);
            }
            Orden orden = ordenHasPlatillo.getOrden();
            if (orden != null) {
                orden.getOrdenHasPlatilloList().remove(ordenHasPlatillo);
                orden = em.merge(orden);
            }
            Platillo platillo = ordenHasPlatillo.getPlatillo();
            if (platillo != null) {
                platillo.getOrdenHasPlatilloList().remove(ordenHasPlatillo);
                platillo = em.merge(platillo);
            }
            em.remove(ordenHasPlatillo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdenHasPlatillo> findOrdenHasPlatilloEntities() {
        return findOrdenHasPlatilloEntities(true, -1, -1);
    }

    public List<OrdenHasPlatillo> findOrdenHasPlatilloEntities(int maxResults, int firstResult) {
        return findOrdenHasPlatilloEntities(false, maxResults, firstResult);
    }

    private List<OrdenHasPlatillo> findOrdenHasPlatilloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdenHasPlatillo.class));
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

    public OrdenHasPlatillo findOrdenHasPlatillo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenHasPlatillo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenHasPlatilloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdenHasPlatillo> rt = cq.from(OrdenHasPlatillo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
