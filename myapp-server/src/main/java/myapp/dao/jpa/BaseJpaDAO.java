package myapp.dao.jpa;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseJpaDAO<TYPE, PK> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseJpaDAO.class);

    private EntityManager entityManager;
    
    public abstract Class<TYPE> getEntityClass();

    public abstract Class<PK> getKeyClass();
    
    @Autowired
    myapp.dao.stub.IQueryRepository queryRepo;
    
    protected String getQuery(String queryName) {
        return queryRepo.getQuery(queryName);
    }
    
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    protected SortedMap<String, Object> getProperties() {
        SortedMap<String, Object> properties = new TreeMap<String, Object>();
        properties.putAll(entityManager.getProperties());
        properties.putAll(entityManager.getEntityManagerFactory().getProperties());
        return properties;
    }

    public TYPE retrieveByPK(PK key) {
        if (key == null) {
            return null;
        }
        return getEntityManager().find(getEntityClass(), key);
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
