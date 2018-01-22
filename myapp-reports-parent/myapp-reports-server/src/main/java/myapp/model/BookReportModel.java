package myapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Entity
public class BookReportModel {
    
    @Id
    Integer bookId;
    String  title;
    
    
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
    public BookReportModel() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
}
