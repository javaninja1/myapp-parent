package myapp.entity.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table (name = "book")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region= "myapp.model.Book")
//@Cacheable (true)
public class DOBook  implements Serializable {


    private static final long serialVersionUID = 1L;

    @Column (name = "bookId")
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer bookId;
    
    @Column (name = "title")
    String  title;
    
    
    
    public DOBook() {
        super();
    }

    public DOBook(Integer bookId, String title) {
        super();
        this.bookId = bookId;
        this.title = title;
    }
    
    public DOBook(String title) {
        this.title = title;
    }

    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + "]";
    }
    
}
