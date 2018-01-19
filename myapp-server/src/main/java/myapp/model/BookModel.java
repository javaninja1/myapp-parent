package myapp.model;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class BookModel {
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
    
    
}
