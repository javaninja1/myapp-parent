package myapp.service.stub;

import java.util.List;

import myapp.entity.view.ViewBook;
import myapp.model.Book;

public interface IBookReportService {
  
  public Book getBook(Integer bookId);
  
  public void saveBook(String title);
  
  public List<Book> getAll();
  
  public Book findByTitle(String string);
  
  public void delete(Book book);
  
  public List<Book> findByBookIdGreaterThan(Integer bookId);

  public ViewBook findByTitleNative(String title);
  
}
