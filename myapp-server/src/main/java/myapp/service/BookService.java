package myapp.service;

import java.util.List;
import java.util.Map;

import myapp.dao.repository.QueryRepository;
import myapp.dao.stub.IBookDAO;
import myapp.model.Book;
import myapp.model.BookView;
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
    public Book getBook(Integer bookId) {
        Book book = new Book(5, "Tale of two cities");
        return book;
    }
    
    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }
    
    @Override
    public Map<String,Object> getEMProperties() {
      //  bookDAO.printSessionStats();
        return bookDAO.getEMProperties();
    }
    
    @Override
    public void saveBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        bookDAO.save(book);
    }

    @Override
    public Book findByTitle(String title) {
        LOG.info("title:{}", title); //info level needed to see logs during unit test
        return bookDAO.findByTitle(title);
    }
    
    @Override
    public BookView findByTitleNative(String title) {
        LOG.info("title:{}", title); //info level needed to see logs during unit test
        return bookDAO.findByTitleNative(title);
    }
    @Override
    public void delete(Book book) {
        bookDAO.remove(book);
    }

    @Override
    public List<Book> findByBookIdGreaterThan(Integer bookId) {
        return bookDAO.findByBookIdGreaterThan(bookId);
    }

    @Override
    public int getMaxBookId() {
        return bookDAO.getMaxBookId();
    }
    

}
