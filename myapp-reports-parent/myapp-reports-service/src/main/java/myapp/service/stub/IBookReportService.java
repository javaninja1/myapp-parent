package myapp.service.stub;

import java.util.List;

import myapp.model.Book;
import myapp.model.BookReport;

public interface IBookReportService {
  
  public Book getBook(Integer bookId);
  
  public void saveBook(String title);
  
  public List<Book> getAll();
  
  public Book findByTitle(String string);
  
  public void delete(Book book);
  
  public List<Book> findByBookIdGreaterThan(Integer bookId);

  public BookReport findByTitleNative(String title);
  
}
