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
import java.util.ArrayList;
import java.util.List;
import Entidades.Ingredientes;
import Entidades.Platillo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jbran
 */
public class PlatilloJpaController implements Serializable {

    public PlatilloJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PV_LaYaquesitaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Platillo platillo) {
        if (platillo.getOrdenList() == null) {
            platillo.setOrdenList(new ArrayList<Orden>());
        }
        if (platillo.getIngredientesList() == null) {
            platillo.setIngredientesList(new ArrayList<Ingredientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Orden> attachedOrdenList = new ArrayList<Orden>();
            for (Orden ordenListOrdenToAttach : platillo.getOrdenList()) {
                ordenListOrdenToAttach = em.getReference(ordenListOrdenToAttach.getClass(), ordenListOrdenToAttach.getIdorden());
                attachedOrdenList.add(ordenListOrdenToAttach);
            }
            platillo.setOrdenList(attachedOrdenList);
            List<Ingredientes> attachedIngredientesList = new ArrayList<Ingredientes>();
            for (Ingredientes ingredientesListIngredientesToAttach : platillo.getIngredientesList()) {
                ingredientesListIngredientesToAttach = em.getReference(ingredientesListIngredientesToAttach.getClass(), ingredientesListIngredientesToAttach.getIdingredientes());
                attachedIngredientesList.add(ingredientesListIngredientesToAttach);
            }
            platillo.setIngredientesList(attachedIngredientesList);
            em.persist(platillo);
            for (Orden ordenListOrden : platillo.getOrdenList()) {
                ordenListOrden.getPlatilloList().add(platillo);
                ordenListOrden = em.merge(ordenListOrden);
            }
            for (Ingredientes ingredientesListIngredientes : platillo.getIngredientesList()) {
                ingredientesListIngredientes.getPlatilloList().add(platillo);
                ingredientesListIngredientes = em.merge(ingredientesListIngredientes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Platillo platillo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Platillo persistentPlatillo = em.find(Platillo.class, platillo.getIdplatillo());
            List<Orden> ordenListOld = persistentPlatillo.getOrdenList();
            List<Orden> ordenListNew = platillo.getOrdenList();
            List<Ingredientes> ingredientesListOld = persistentPlatillo.getIngredientesList();
            List<Ingredientes> ingredientesListNew = platillo.getIngredientesList();
            List<Orden> attachedOrdenListNew = new ArrayList<Orden>();
            for (Orden ordenListNewOrdenToAttach : ordenListNew) {
                ordenListNewOrdenToAttach = em.getReference(ordenListNewOrdenToAttach.getClass(), ordenListNewOrdenToAttach.getIdorden());
                attachedOrdenListNew.add(ordenListNewOrdenToAttach);
            }
            ordenListNew = attachedOrdenListNew;
            platillo.setOrdenList(ordenListNew);
            List<Ingredientes> attachedIngredientesListNew = new ArrayList<Ingredientes>();
            for (Ingredientes ingredientesListNewIngredientesToAttach : ingredientesListNew) {
                ingredientesListNewIngredientesToAttach = em.getReference(ingredientesListNewIngredientesToAttach.getClass(), ingredientesListNewIngredientesToAttach.getIdingredientes());
                attachedIngredientesListNew.add(ingredientesListNewIngredientesToAttach);
            }
            ingredientesListNew = attachedIngredientesListNew;
            platillo.setIngredientesList(ingredientesListNew);
            platillo = em.merge(platillo);
            for (Orden ordenListOldOrden : ordenListOld) {
                if (!ordenListNew.contains(ordenListOldOrden)) {
                    ordenListOldOrden.getPlatilloList().remove(platillo);
                    ordenListOldOrden = em.merge(ordenListOldOrden);
                }
            }
            for (Orden ordenListNewOrden : ordenListNew) {
                if (!ordenListOld.contains(ordenListNewOrden)) {
                    ordenListNewOrden.getPlatilloList().add(platillo);
                    ordenListNewOrden = em.merge(ordenListNewOrden);
                }
            }
            for (Ingredientes ingredientesListOldIngredientes : ingredientesListOld) {
                if (!ingredientesListNew.contains(ingredientesListOldIngredientes)) {
                    ingredientesListOldIngredientes.getPlatilloList().remove(platillo);
                    ingredientesListOldIngredientes = em.merge(ingredientesListOldIngredientes);
                }
            }
            for (Ingredientes ingredientesListNewIngredientes : ingredientesListNew) {
                if (!ingredientesListOld.contains(ingredientesListNewIngredientes)) {
                    ingredientesListNewIngredientes.getPlatilloList().add(platillo);
                    ingredientesListNewIngredientes = em.merge(ingredientesListNewIngredientes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = platillo.getIdplatillo();
                if (findPlatillo(id) == null) {
                    throw new NonexistentEntityException("The platillo with id " + id + " no longer exists.");
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
            Platillo platillo;
            try {
                platillo = em.getReference(Platillo.class, id);
                platillo.getIdplatillo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The platillo with id " + id + " no longer exists.", enfe);
            }
            List<Orden> ordenList = platillo.getOrdenList();
            for (Orden ordenListOrden : ordenList) {
                ordenListOrden.getPlatilloList().remove(platillo);
                ordenListOrden = em.merge(ordenListOrden);
            }
            List<Ingredientes> ingredientesList = platillo.getIngredientesList();
            for (Ingredientes ingredientesListIngredientes : ingredientesList) {
                ingredientesListIngredientes.getPlatilloList().remove(platillo);
                ingredientesListIngredientes = em.merge(ingredientesListIngredientes);
            }
            em.remove(platillo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Platillo> findPlatilloEntities() {
        return findPlatilloEntities(true, -1, -1);
    }

    public List<Platillo> findPlatilloEntities(int maxResults, int firstResult) {
        return findPlatilloEntities(false, maxResults, firstResult);
    }

    private List<Platillo> findPlatilloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Platillo.class));
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

    public Platillo findPlatillo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Platillo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlatilloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Platillo> rt = cq.from(Platillo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
