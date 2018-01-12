package myapp.service;

import java.util.List;

import myapp.dao.repository.QueryRepository;
import myapp.dao.stub.IBookDAO;
import myapp.dao.stub.IBookReportDAO;
import myapp.model.Book;
import myapp.model.BookReport;
import myapp.model.BookView;
import myapp.service.stub.IBookReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookReportService implements IBookReportService {

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Autowired
    QueryRepository queryRepo;
    
    @Autowired
    IBookReportDAO bookReportDAO;
    
    @Override
    public Book getBook(Integer bookId) {
        Book book = new Book(5, "Tale of two cities");
        return book;
    }
    
    @Override
    public List<Book> getAll() {
        return bookReportDAO.getAll();
    }
    
    @Override
    public void saveBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        bookReportDAO.save(book);
    }

    @Override
    public Book findByTitle(String title) {
        LOG.info("title:{}", title); //info level needed to see logs during unit test
        return bookReportDAO.findByTitle(title);
    }
    
    @Override
    public BookReport findByTitleNative(String title) {
        LOG.info("title:{}", title); //info level needed to see logs during unit test
        return bookReportDAO.findByTitleNative(title);
    }
    @Override
    public void delete(Book book) {
        bookReportDAO.remove(book);
    }

    @Override
    public List<Book> findByBookIdGreaterThan(Integer bookId) {
        return bookReportDAO.findByBookIdGreaterThan(bookId);
    }
    

}
