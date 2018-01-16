package myapp.dao.jpa;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseJpaDAO<TYPE, PK> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseJpaDAO.class);

    private EntityManager entityManager;
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    protected Map<String, Object> getProperties() {
        return entityManager.getProperties();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    protected void persist(TYPE type) {
        entityManager.persist(type);
    }
    
//    protected  <T> TypedQuery<T> query(String query, Class<T> clazz) {
//        TypedQuery<T> t =  getEntityManager().createQuery(query, clazz);
//        return t;
//    }
    
    protected <T> T doubleIt(T t) {
        return t;
    }
    
    protected <T> TypedQuery<T> query(String query, Class<T> resultType, Object... params) {
        TypedQuery<T> q = getEntityManager().createQuery(query, resultType);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                q.setParameter(i + 1, params[i]);
            }
        }
        return q;
    }


    


}
