package myapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "book")
public class Book {

    @Column (name = "bookId")
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer bookId;
    
    @Column (name = "title")
    String  title;
    
    
    
    public Book() {
        super();
    }

    public Book(Integer bookId, String title) {
        super();
        this.bookId = bookId;
        this.title = title;
    }
    
    public Book(String title) {
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
