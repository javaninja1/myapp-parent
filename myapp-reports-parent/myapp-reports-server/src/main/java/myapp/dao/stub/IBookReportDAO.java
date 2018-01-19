package myapp.dao.stub;

import java.util.List;

import myapp.entity.view.ViewBook;
import myapp.model.Book;
import myapp.model.BookReport;

public interface IBookReportDAO {

    public void save(Book book);

    public List<Book> getAll();

    public Book findByTitle(String title);

    public void remove(Book book);

    public List<Book> findByBookIdGreaterThan(Integer bookId);

    public ViewBook findByTitleNative(String title);

    public BookReport findByTitleNativeWithSqlMapping(String title);

}
