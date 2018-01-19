package myapp.dao.stub;

import java.util.List;
import java.util.SortedMap;

import myapp.entity.domain.DOBook;
import myapp.model.BookModel;

public interface IBookDAO {

    public void save(DOBook book);

    public List<DOBook> getAll();

    public DOBook findByTitle(String title);

    public void remove(DOBook book);

    public List<DOBook> findByBookIdGreaterThan(Integer bookId);

    public BookModel findByTitleNative(String title);
    
    public SortedMap<String, Object> getEMProperties();

    void printSessionStats();

    public int getMaxBookId();

}
