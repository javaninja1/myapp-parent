package myapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import myapp.entity.domain.DOBook;
import myapp.service.BookService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class BookDAOTest {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private BookService bookService;
    
    //REMOTE 2
    
    //XXXXXX
    
    //LOCAL 5
    @Test
    public void shouldHaveAnEntityManager() {
        assertNotNull(entityManager);
    }
    
    @Test
    public void shouldHaveNoObjectsAtStart() {
        List<?> results = entityManager.createQuery("from Book").getResultList();
        assertTrue(results.isEmpty());
    }
    
    @Test
    public void shouldBeAbleToPersistAnObject() {
        assertEquals(0, entityManager.createQuery("from Book").getResultList().size());
        DOBook book = new DOBook("book1");
        entityManager.persist(book);
        entityManager.flush();
        assertEquals(1, entityManager.createQuery("from Book").getResultList().size());
    }
    
    @Test
    public void shouldBeAbleToQueryForObjects() {
        shouldBeAbleToPersistAnObject();
        assertEquals(1, entityManager.createQuery("from Book where title = 'book1'").getResultList().size());
        assertEquals(0, entityManager.createQuery("from Book where title = 'book2'").getResultList().size());
    }
    
    //END TESTS

}
