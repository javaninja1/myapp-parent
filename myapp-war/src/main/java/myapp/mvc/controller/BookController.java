package myapp.mvc.controller;

import java.util.Arrays;
import java.util.List;

import myapp.dao.stub.IQueryRepository;
import myapp.model.Book;
import myapp.model.BookView;
import myapp.mvc.annotation.MyAnno;
import myapp.service.stub.IBookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BookController {
    
    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    IBookService bookService;
    
    @Autowired
    IQueryRepository queryRepo;
    
    @RequestMapping( "/book")
    public ResponseEntity<String> getBook() {
        Book book = bookService.getBook(5);
        ResponseEntity<String> response = new ResponseEntity<>("Response:<br>" + book.toString(),
                 HttpStatus.OK);
        return response;
    }

    @RequestMapping( "/init")
    public ResponseEntity<String> init() {
        
        Arrays.asList("book1","book2")
             .stream()
             .forEach(e -> bookService.saveBook(e));
        
        ResponseEntity<String> response = new ResponseEntity<>("Saved books..." ,
                 HttpStatus.OK);
        return response;
    }

    @RequestMapping( path="/books.do", method=RequestMethod.POST)
    public String postBooks(Model model) {
        Arrays.asList("book1","book2")
        .stream()
        .forEach(e -> bookService.saveBook(e));
        
        return "redirect:/books.jsp";
        
    }
    
    @RequestMapping( path="/books.do", method=RequestMethod.GET)
    public String getBooks(Model model, @MyAnno String myAnno) {
        //annotation
        model.addAttribute("result", myAnno);
        
        //createQuery
        List<Book> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);
        
        //Named query
        List<Book> namedQueryList = bookService.findByBookIdGreaterThan(1);
        model.addAttribute("namedQueryList", namedQueryList);
        
        //NamedNative query
        BookView bookView = bookService.findByTitleNative("book2");
        model.addAttribute("bookView", bookView);
        
        model.addAttribute("queryLookup", queryRepo.getQuery("GET_BOOKS"));
        
        return "books";
        
    }

}
