package myapp.entity.view;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ViewBook implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="bookId")
    private int bookId;
    
    @Column(name="title")
    private String title;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
