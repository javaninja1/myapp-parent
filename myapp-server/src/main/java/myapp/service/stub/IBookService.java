package myapp.service.stub;

import java.util.List;
import java.util.Map;

import myapp.model.Book;
import myapp.model.BookView;

public interface IBookService {
  
  public Book getBook(Integer bookId);
  
  public void saveBook(String title);
  
  public List<Book> getAll();
  
  public Book findByTitle(String string);
  
  public void delete(Book book);
  
  public List<Book> findByBookIdGreaterThan(Integer bookId);

  public BookView findByTitleNative(String title);

   public Map<String, Object> getEMProperties();

   public int getMaxBookId();
  
}
