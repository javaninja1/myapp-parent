package myapp.dao.stub;

import java.util.List;

import myapp.entity.domain.DOBook;
import myapp.entity.view.ViewBook;
import myapp.model.BookReportModel;

public interface IBookReportDAO {

    public List<DOBook> getAll();
    public DOBook findByTitle(String title);
    public List<DOBook> findByBookIdGreaterThan(Integer bookId);
    public ViewBook findByTitleNative(String title);
    public BookReportModel findByTitleNativeWithSqlMapping(String title);

}
