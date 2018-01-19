package myapp.service.stub;

import java.util.List;
import java.util.Map;

import myapp.entity.domain.DOBook;
import myapp.model.BookModel;

public interface IBookService {
  
  public DOBook getBook(Integer bookId);
  
  public void saveBook(String title);
  
  public List<DOBook> getAll();
  
  public DOBook findByTitle(String string);
  
  public void delete(DOBook book);
  
  public List<DOBook> findByBookIdGreaterThan(Integer bookId);

  public BookModel findByTitleNative(String title);

   public Map<String, Object> getEMProperties();

   public int getMaxBookId();
  
}
