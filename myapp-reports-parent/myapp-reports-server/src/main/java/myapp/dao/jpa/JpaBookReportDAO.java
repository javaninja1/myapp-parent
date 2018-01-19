package myapp.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import myapp.dao.stub.IBookReportDAO;
import myapp.model.BookReportModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import myapp.entity.domain.DOBook;
import myapp.entity.view.ViewBook;

@Repository
public class JpaBookReportDAO extends BaseJpaDAO<DOBook,Integer> implements IBookReportDAO  {
    
    private TransactionTemplate transactionTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate tt) {
        this.transactionTemplate = tt;
    }

    @Override
    public List<DOBook> getAll() {
        TypedQuery<DOBook> t =  query("select e from DOBook e", DOBook.class);
        return t.getResultList();
    }

    @Override
    public DOBook findByTitle(String title) {
        return (DOBook) query("select e from DOBook e where title = :title", DOBook.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public BookReportModel findByTitleNativeWithSqlMapping(String title) {
        TypedQuery<BookReportModel> typedQuery = getEntityManager().createNamedQuery("BookReport.findByTitle", BookReportModel.class);
        typedQuery.setMaxResults(1);
        typedQuery.setParameter("title", title);
        return typedQuery.getResultList()
                         .stream()
                         .findFirst()
                          .orElse(null);
    }
    
    @Override
    public ViewBook findByTitleNative(String title) {        
        TypedQuery<ViewBook> typedQuery = (TypedQuery<ViewBook>) getEntityManager().createNativeQuery(getQuery("ViewBook.findByTitle"), ViewBook.class);
        typedQuery.setMaxResults(1);
        typedQuery.setParameter("title", title);
        return typedQuery.getResultList()
                         .stream()
                         .findFirst()
                          .orElse(null);
    }

    @Override
    public List<DOBook> findByBookIdGreaterThan(Integer bookId) {
        TypedQuery<DOBook> typedQuery = getEntityManager().createNamedQuery("Book.findByBookIdGreaterThan", DOBook.class);
        typedQuery.setParameter("bookId", bookId).getResultList();
        return typedQuery.getResultList();
    }

    @Override
    public Class<DOBook> getEntityClass() {        
        return DOBook.class;
    }

    @Override
    public Class<Integer> getKeyClass() {
        return Integer.class;
    }
    
    

}
