package myapp.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import myapp.dao.stub.IBookReportDAO;
import myapp.model.Book;
import myapp.model.BookReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
public class JpaBookReportDAO extends BaseJpaDAO<Book,Integer> implements IBookReportDAO  {
    

    private TransactionTemplate transactionTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate tt) {
        this.transactionTemplate = tt;
    }
    
    @Override
    public void save(Book book) {
        persist(book);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> t =  query("select e from Book e", Book.class);
        return t.getResultList();
    }

    @Override
    public Book findByTitle(String title) {
        return (Book) query("select e from Book e where title = :title", Book.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public BookReport findByTitleNative(String title) {
        TypedQuery<BookReport> typedQuery = getEntityManager().createNamedQuery("BookReport.findByTitle", BookReport.class);
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
    
    

}
