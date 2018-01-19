package myapp.service;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import myapp.dao.repository.QueryRepository;
import myapp.dao.stub.IBookDAO;
import myapp.entity.domain.DOBook;
import myapp.model.BookModel;
import myapp.service.stub.IBookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Autowired
    QueryRepository queryRepo;
    
    @Autowired
    IBookDAO bookDAO;
    
    @Override
    public DOBook getBook(Integer bookId) {
        DOBook book = new DOBook(5, "Tale of two cities");
        return book;
    }
    
    @Override
    public List<DOBook> getAll() {
        return bookDAO.getAll();
    }
    
    @Override
    public SortedMap<String,Object> getEMProperties() {
      //  bookDAO.printSessionStats();
        return bookDAO.getEMProperties();
    }
    
    @Override
    public void saveBook(String title) {
        DOBook book = new DOBook();
        book.setTitle(title);
        bookDAO.save(book);
    }

    @Override
    public DOBook findByTitle(String title) {
        LOG.info("title:{}", title); //info level needed to see logs during unit test
        return bookDAO.findByTitle(title);
    }
    
    @Override
    public BookModel findByTitleNative(String title) {
        LOG.info("title:{}", title); //info level needed to see logs during unit test
        return bookDAO.findByTitleNative(title);
    }
    @Override
    public void delete(DOBook book) {
        bookDAO.remove(book);
    }

    @Override
    public List<DOBook> findByBookIdGreaterThan(Integer bookId) {
        return bookDAO.findByBookIdGreaterThan(bookId);
    }

    @Override
    public int getMaxBookId() {
        return bookDAO.getMaxBookId();
    }
    

}
