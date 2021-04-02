/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Ingredientes;
import java.util.ArrayList;
import java.util.List;
import Entidades.OrdenHasPlatillo;
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
        if (platillo.getIngredientesList() == null) {
            platillo.setIngredientesList(new ArrayList<Ingredientes>());
        }
        if (platillo.getOrdenHasPlatilloList() == null) {
            platillo.setOrdenHasPlatilloList(new ArrayList<OrdenHasPlatillo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ingredientes> attachedIngredientesList = new ArrayList<Ingredientes>();
            for (Ingredientes ingredientesListIngredientesToAttach : platillo.getIngredientesList()) {
                ingredientesListIngredientesToAttach = em.getReference(ingredientesListIngredientesToAttach.getClass(), ingredientesListIngredientesToAttach.getIdingredientes());
                attachedIngredientesList.add(ingredientesListIngredientesToAttach);
            }
            platillo.setIngredientesList(attachedIngredientesList);
            List<OrdenHasPlatillo> attachedOrdenHasPlatilloList = new ArrayList<OrdenHasPlatillo>();
            for (OrdenHasPlatillo ordenHasPlatilloListOrdenHasPlatilloToAttach : platillo.getOrdenHasPlatilloList()) {
                ordenHasPlatilloListOrdenHasPlatilloToAttach = em.getReference(ordenHasPlatilloListOrdenHasPlatilloToAttach.getClass(), ordenHasPlatilloListOrdenHasPlatilloToAttach.getIdOrdenHasPlatillo());
                attachedOrdenHasPlatilloList.add(ordenHasPlatilloListOrdenHasPlatilloToAttach);
            }
            platillo.setOrdenHasPlatilloList(attachedOrdenHasPlatilloList);
            em.persist(platillo);
            for (Ingredientes ingredientesListIngredientes : platillo.getIngredientesList()) {
                ingredientesListIngredientes.getPlatilloList().add(platillo);
                ingredientesListIngredientes = em.merge(ingredientesListIngredientes);
            }
            for (OrdenHasPlatillo ordenHasPlatilloListOrdenHasPlatillo : platillo.getOrdenHasPlatilloList()) {
                Platillo oldPlatilloOfOrdenHasPlatilloListOrdenHasPlatillo = ordenHasPlatilloListOrdenHasPlatillo.getPlatillo();
                ordenHasPlatilloListOrdenHasPlatillo.setPlatillo(platillo);
                ordenHasPlatilloListOrdenHasPlatillo = em.merge(ordenHasPlatilloListOrdenHasPlatillo);
                if (oldPlatilloOfOrdenHasPlatilloListOrdenHasPlatillo != null) {
                    oldPlatilloOfOrdenHasPlatilloListOrdenHasPlatillo.getOrdenHasPlatilloList().remove(ordenHasPlatilloListOrdenHasPlatillo);
                    oldPlatilloOfOrdenHasPlatilloListOrdenHasPlatillo = em.merge(oldPlatilloOfOrdenHasPlatilloListOrdenHasPlatillo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Platillo platillo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Platillo persistentPlatillo = em.find(Platillo.class, platillo.getIdplatillo());
            List<Ingredientes> ingredientesListOld = persistentPlatillo.getIngredientesList();
            List<Ingredientes> ingredientesListNew = platillo.getIngredientesList();
            List<OrdenHasPlatillo> ordenHasPlatilloListOld = persistentPlatillo.getOrdenHasPlatilloList();
            List<OrdenHasPlatillo> ordenHasPlatilloListNew = platillo.getOrdenHasPlatilloList();
            List<String> illegalOrphanMessages = null;
            for (OrdenHasPlatillo ordenHasPlatilloListOldOrdenHasPlatillo : ordenHasPlatilloListOld) {
                if (!ordenHasPlatilloListNew.contains(ordenHasPlatilloListOldOrdenHasPlatillo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdenHasPlatillo " + ordenHasPlatilloListOldOrdenHasPlatillo + " since its platillo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Ingredientes> attachedIngredientesListNew = new ArrayList<Ingredientes>();
            for (Ingredientes ingredientesListNewIngredientesToAttach : ingredientesListNew) {
                ingredientesListNewIngredientesToAttach = em.getReference(ingredientesListNewIngredientesToAttach.getClass(), ingredientesListNewIngredientesToAttach.getIdingredientes());
                attachedIngredientesListNew.add(ingredientesListNewIngredientesToAttach);
            }
            ingredientesListNew = attachedIngredientesListNew;
            platillo.setIngredientesList(ingredientesListNew);
            List<OrdenHasPlatillo> attachedOrdenHasPlatilloListNew = new ArrayList<OrdenHasPlatillo>();
            for (OrdenHasPlatillo ordenHasPlatilloListNewOrdenHasPlatilloToAttach : ordenHasPlatilloListNew) {
                ordenHasPlatilloListNewOrdenHasPlatilloToAttach = em.getReference(ordenHasPlatilloListNewOrdenHasPlatilloToAttach.getClass(), ordenHasPlatilloListNewOrdenHasPlatilloToAttach.getIdOrdenHasPlatillo());
                attachedOrdenHasPlatilloListNew.add(ordenHasPlatilloListNewOrdenHasPlatilloToAttach);
            }
            ordenHasPlatilloListNew = attachedOrdenHasPlatilloListNew;
            platillo.setOrdenHasPlatilloList(ordenHasPlatilloListNew);
            platillo = em.merge(platillo);
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
            for (OrdenHasPlatillo ordenHasPlatilloListNewOrdenHasPlatillo : ordenHasPlatilloListNew) {
                if (!ordenHasPlatilloListOld.contains(ordenHasPlatilloListNewOrdenHasPlatillo)) {
                    Platillo oldPlatilloOfOrdenHasPlatilloListNewOrdenHasPlatillo = ordenHasPlatilloListNewOrdenHasPlatillo.getPlatillo();
                    ordenHasPlatilloListNewOrdenHasPlatillo.setPlatillo(platillo);
                    ordenHasPlatilloListNewOrdenHasPlatillo = em.merge(ordenHasPlatilloListNewOrdenHasPlatillo);
                    if (oldPlatilloOfOrdenHasPlatilloListNewOrdenHasPlatillo != null && !oldPlatilloOfOrdenHasPlatilloListNewOrdenHasPlatillo.equals(platillo)) {
                        oldPlatilloOfOrdenHasPlatilloListNewOrdenHasPlatillo.getOrdenHasPlatilloList().remove(ordenHasPlatilloListNewOrdenHasPlatillo);
                        oldPlatilloOfOrdenHasPlatilloListNewOrdenHasPlatillo = em.merge(oldPlatilloOfOrdenHasPlatilloListNewOrdenHasPlatillo);
                    }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<OrdenHasPlatillo> ordenHasPlatilloListOrphanCheck = platillo.getOrdenHasPlatilloList();
            for (OrdenHasPlatillo ordenHasPlatilloListOrphanCheckOrdenHasPlatillo : ordenHasPlatilloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Platillo (" + platillo + ") cannot be destroyed since the OrdenHasPlatillo " + ordenHasPlatilloListOrphanCheckOrdenHasPlatillo + " in its ordenHasPlatilloList field has a non-nullable platillo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
