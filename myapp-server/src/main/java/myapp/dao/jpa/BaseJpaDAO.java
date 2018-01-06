package myapp.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class BaseJpaDAO<TYPE, PK> {
    
    private EntityManager entityManager;
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
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
