package myapp.service.stub;

import java.util.List;

import myapp.entity.domain.DOBook;
import myapp.entity.view.ViewBook;
import myapp.model.BookReportModel;

public interface IBookReportService {
  
  public DOBook getBook(Integer bookId);
  public List<DOBook> getAll();
  public List<DOBook> findByBookIdGreaterThan(Integer bookId);
  
  public DOBook findByTitle(String string);
  public ViewBook findByTitleNative(String title);
  public BookReportModel findByTitleNativeWithSqlMapping(String string);
  
}
