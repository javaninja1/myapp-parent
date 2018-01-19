package myapp.dao.jpa;

import java.util.List;
import java.util.SortedMap;

import javax.persistence.TypedQuery;

import myapp.dao.stub.IBookDAO;
import myapp.entity.domain.DOBook;
import myapp.model.BookModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBookDAO extends BaseJpaDAO<DOBook,Integer> implements IBookDAO  {
    
    
    private static final Logger LOG = LoggerFactory.getLogger(JpaBookDAO.class);


    @Override
    public Class<DOBook> getEntityClass() {
        return DOBook.class;
    }

    @Override
    public Class<Integer> getKeyClass() {
        return Integer.class;
    }

    @Override
    public void save(DOBook book) {
        persist(book);
    }

    @Override
    public SortedMap<String,Object> getEMProperties() {
        return getProperties();
    }
    
    @Override
    public List<DOBook> getAll() {
        TypedQuery<DOBook> t =  query("select e from DOBook e", DOBook.class);
        return t.getResultList();
    }

    @Override
    public DOBook findByTitle(String title) {
         DOBook b =  query("select e from DOBook e where title = :title", DOBook.class)
                .setParameter("title", title)
                .getSingleResult();
         return retrieveByPK(b.getBookId());
    }

    @Override
    public BookModel findByTitleNative(String title) {
        TypedQuery<BookModel> typedQuery = getEntityManager().createNamedQuery("Book.findByTitle", BookModel.class);
        typedQuery.setMaxResults(1);
        typedQuery.setParameter("title", title);
        return typedQuery.getResultList()
                         .stream()
                         .findFirst()
                          .orElse(null);
    }

    
    @Override
    public void remove(DOBook book) {
        getEntityManager().remove(book);
    }
    
    @Override
    public List<DOBook> findByBookIdGreaterThan(Integer bookId) {
        TypedQuery<DOBook> typedQuery = getEntityManager().createNamedQuery("Book.findByBookIdGreaterThan", DOBook.class);
        typedQuery.setParameter("bookId", bookId).getResultList();
        return typedQuery.getResultList();
    }
    
    

    @Override
    public void printSessionStats() {
        
        
        LOG.debug("Temp Dir:{}", System.getProperty("java.io.tmpdir"));
        SessionFactory sessionFactory = null;
        Statistics stats = sessionFactory.getStatistics();
        LOG.debug("Stats enabled:{}", stats.isStatisticsEnabled());
        stats.setStatisticsEnabled(true);
        LOG.debug("Stats enabled:{}", stats.isStatisticsEnabled());
        
        Session session = sessionFactory.openSession();
        Session otherSession = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Transaction otherTransaction = otherSession.beginTransaction();
        
        //printStats(stats, 0);
        
        DOBook book = (DOBook) session.load(DOBook.class, 1);
        printData(book, stats, 1);
        
        book = (DOBook) session.load(DOBook.class, 1);
        printData(book, stats, 2);
        
        //clear first level cache, so that second level cache is used
        session.evict(book);
        book = (DOBook) session.load(DOBook.class, 1);
        printData(book, stats, 3);
        
        book = (DOBook) session.load(DOBook.class, 3);
        printData(book, stats, 4);
        
        book = (DOBook) otherSession.load(DOBook.class, 1);
        printData(book, stats, 5);
        
        //Release resources
        transaction.commit();
        otherTransaction.commit();
        sessionFactory.close();
    }

    private static void printStats(Statistics stats, int i) {
        LOG.debug("***** {} *****", i);
        LOG.debug("Fetch Count:{}",stats.getEntityFetchCount());
        LOG.debug("Second Level Hit Count:{}", stats.getSecondLevelCacheHitCount());
        LOG.debug("Second Level Miss Count:{}",stats.getSecondLevelCacheMissCount());
        LOG.debug("Second Level Put Count:{}",stats.getSecondLevelCachePutCount());
    }

    private static void printData(DOBook book, Statistics stats, int count) {
        LOG.debug("{}:{}", count , book);
        printStats(stats, count);
    }

    @Override
    public int getMaxBookId() {
        return query("select max(bookId) from DOBook e", Integer.class).getFirstResult();
    }
}
