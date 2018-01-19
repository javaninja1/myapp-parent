package myapp.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import myapp.entity.domain.DOBook;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class BookServiceTest {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private BookService bookService;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    ////START TESTS
    
    @Test
    public void testSaveBook() {
        bookService.saveBook("book1");
        DOBook book = bookService.findByTitle("book1");
        Assert.assertNotNull(book);
        Assert.assertTrue("book1".equals(book.getTitle()));
        
       // Assert.assertEquals("book1", book.getTitle());
        bookService.delete(book);
        
        exception.expect(NoResultException.class);
        book = bookService.findByTitle("book1");

        
    }
    
    //END TESTS

}
