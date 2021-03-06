package introsde.assignment.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Entity Manager wrapper for accessing the database
 */
public enum PersonMeasureDao {
    instance;
    private EntityManagerFactory emf;

    private PersonMeasureDao() {
        if (emf!=null) {
            emf.close();
        }
        emf = Persistence.createEntityManagerFactory("introsde-2015-assignment-3");
    }

    public EntityManager createEntityManager() {
        try {
            return emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    
    }

    public void closeConnections(EntityManager em) {
        em.close();
    }

    public EntityTransaction getTransaction(EntityManager em) {
        return em.getTransaction();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }  
}
