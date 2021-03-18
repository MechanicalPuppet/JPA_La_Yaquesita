/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Ingredientes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class IngredientesJpaController implements Serializable {

    public IngredientesJpaController() {
       this.emf = Persistence.createEntityManagerFactory("PV_LaYaquesitaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ingredientes ingredientes) {
        if (ingredientes.getPlatilloList() == null) {
            ingredientes.setPlatilloList(new ArrayList<Platillo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Platillo> attachedPlatilloList = new ArrayList<Platillo>();
            for (Platillo platilloListPlatilloToAttach : ingredientes.getPlatilloList()) {
                platilloListPlatilloToAttach = em.getReference(platilloListPlatilloToAttach.getClass(), platilloListPlatilloToAttach.getIdplatillo());
                attachedPlatilloList.add(platilloListPlatilloToAttach);
            }
            ingredientes.setPlatilloList(attachedPlatilloList);
            em.persist(ingredientes);
            for (Platillo platilloListPlatillo : ingredientes.getPlatilloList()) {
                platilloListPlatillo.getIngredientesList().add(ingredientes);
                platilloListPlatillo = em.merge(platilloListPlatillo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ingredientes ingredientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingredientes persistentIngredientes = em.find(Ingredientes.class, ingredientes.getIdingredientes());
            List<Platillo> platilloListOld = persistentIngredientes.getPlatilloList();
            List<Platillo> platilloListNew = ingredientes.getPlatilloList();
            List<Platillo> attachedPlatilloListNew = new ArrayList<Platillo>();
            for (Platillo platilloListNewPlatilloToAttach : platilloListNew) {
                platilloListNewPlatilloToAttach = em.getReference(platilloListNewPlatilloToAttach.getClass(), platilloListNewPlatilloToAttach.getIdplatillo());
                attachedPlatilloListNew.add(platilloListNewPlatilloToAttach);
            }
            platilloListNew = attachedPlatilloListNew;
            ingredientes.setPlatilloList(platilloListNew);
            ingredientes = em.merge(ingredientes);
            for (Platillo platilloListOldPlatillo : platilloListOld) {
                if (!platilloListNew.contains(platilloListOldPlatillo)) {
                    platilloListOldPlatillo.getIngredientesList().remove(ingredientes);
                    platilloListOldPlatillo = em.merge(platilloListOldPlatillo);
                }
            }
            for (Platillo platilloListNewPlatillo : platilloListNew) {
                if (!platilloListOld.contains(platilloListNewPlatillo)) {
                    platilloListNewPlatillo.getIngredientesList().add(ingredientes);
                    platilloListNewPlatillo = em.merge(platilloListNewPlatillo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ingredientes.getIdingredientes();
                if (findIngredientes(id) == null) {
                    throw new NonexistentEntityException("The ingredientes with id " + id + " no longer exists.");
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
            Ingredientes ingredientes;
            try {
                ingredientes = em.getReference(Ingredientes.class, id);
                ingredientes.getIdingredientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ingredientes with id " + id + " no longer exists.", enfe);
            }
            List<Platillo> platilloList = ingredientes.getPlatilloList();
            for (Platillo platilloListPlatillo : platilloList) {
                platilloListPlatillo.getIngredientesList().remove(ingredientes);
                platilloListPlatillo = em.merge(platilloListPlatillo);
            }
            em.remove(ingredientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ingredientes> findIngredientesEntities() {
        return findIngredientesEntities(true, -1, -1);
    }

    public List<Ingredientes> findIngredientesEntities(int maxResults, int firstResult) {
        return findIngredientesEntities(false, maxResults, firstResult);
    }

    private List<Ingredientes> findIngredientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ingredientes.class));
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

    public Ingredientes findIngredientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ingredientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getIngredientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ingredientes> rt = cq.from(Ingredientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
