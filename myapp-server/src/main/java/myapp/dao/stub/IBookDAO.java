package myapp.dao.stub;

import java.util.List;

import myapp.model.Book;
import myapp.model.BookView;

public interface IBookDAO {

    public void save(Book book);

    public List<Book> getAll();

    public Book findByTitle(String title);

    public void remove(Book book);

    public List<Book> findByBookIdGreaterThan(Integer bookId);

    public BookView findByTitleNative(String title);

}
