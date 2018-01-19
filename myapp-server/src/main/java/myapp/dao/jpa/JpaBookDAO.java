package myapp.dao.jpa;

import java.util.List;
import java.util.SortedMap;

import javax.persistence.TypedQuery;

import myapp.dao.stub.IBookDAO;
import myapp.model.Book;
import myapp.model.BookView;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBookDAO extends BaseJpaDAO<Book,Integer> implements IBookDAO  {
    
    
    private static final Logger LOG = LoggerFactory.getLogger(JpaBookDAO.class);


    @Override
    public void save(Book book) {
        persist(book);
    }

    @Override
    public SortedMap<String,Object> getEMProperties() {
        return getProperties();
    }
    
    @Override
    public List<Book> getAll() {
        TypedQuery<Book> t =  query("select e from Book e", Book.class);
        return t.getResultList();
    }

    @Override
    public Book findByTitle(String title) {
         Book b =  query("select e from Book e where title = :title", Book.class)
                .setParameter("title", title)
                .getSingleResult();
         return retrieveByPK(b.getBookId());
    }

    @Override
    public BookView findByTitleNative(String title) {
        TypedQuery<BookView> typedQuery = getEntityManager().createNamedQuery("Book.findByTitle", BookView.class);
        typedQuery.setMaxResults(1);
        typedQuery.setParameter("title", title);
        return typedQuery.getResultList()
                         .stream()
                         .findFirst()
                          .orElse(null);
    }

    
    @Override
    public void remove(Book book) {
        getEntityManager().remove(book);
    }
    
    @Override
    public List<Book> findByBookIdGreaterThan(Integer bookId) {
        TypedQuery<Book> typedQuery = getEntityManager().createNamedQuery("Book.findByBookIdGreaterThan", Book.class);
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
        
        Book book = (Book) session.load(Book.class, 1);
        printData(book, stats, 1);
        
        book = (Book) session.load(Book.class, 1);
        printData(book, stats, 2);
        
        //clear first level cache, so that second level cache is used
        session.evict(book);
        book = (Book) session.load(Book.class, 1);
        printData(book, stats, 3);
        
        book = (Book) session.load(Book.class, 3);
        printData(book, stats, 4);
        
        book = (Book) otherSession.load(Book.class, 1);
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

    private static void printData(Book book, Statistics stats, int count) {
        LOG.debug("{}:{}", count , book);
        printStats(stats, count);
    }

    @Override
    public int getMaxBookId() {
        return query("select max(bookId) from Book e", Integer.class).getFirstResult();
    }

    @Override
    public Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    public Class<Integer> getKeyClass() {
        return Integer.class;
    }
}
