package myapp.service.stub;

import java.util.List;

import myapp.entity.domain.DOBook;
import myapp.entity.view.ViewBook;

public interface IBookReportService {
  
  public DOBook getBook(Integer bookId);
  public List<DOBook> getAll();
  public DOBook findByTitle(String string);
  public List<DOBook> findByBookIdGreaterThan(Integer bookId);
  public ViewBook findByTitleNative(String title);
  
}
