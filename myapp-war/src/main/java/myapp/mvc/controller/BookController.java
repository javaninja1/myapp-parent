package myapp.mvc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import myapp.dao.stub.IQueryRepository;
import myapp.entity.domain.DOBook;
import myapp.model.BookForm;
import myapp.model.BookModel;
import myapp.mvc.annotation.MyAnno;
import myapp.service.stub.IBookService;
import myapp.util.CacheUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    IBookService bookService;

    @Autowired
    IQueryRepository queryRepo;

    @RequestMapping(params = "initData", path = "/books.do", method = RequestMethod.POST)
    public String postInitData(Model model) {
        IntStream.of(1, 2).forEach(e -> bookService.saveBook("Book " + e));
        return "redirect:/books.jsp";

    }

    @RequestMapping(params = "addBook", value = "books.do", method = RequestMethod.POST)
    public String postAddBook(@ModelAttribute BookForm form) {
        bookService.saveBook(form.getTitle());
        return "redirect:/books.jsp";
    }
    
    @RequestMapping(path = "/books.do", method = RequestMethod.GET)
    public String getBooks(Model model, @MyAnno String myAnno) {
        LOG.debug("myAnno:{}", myAnno);
        
        BookForm form = new BookForm();
        model.addAttribute("command", form);
        
        // annotation
        model.addAttribute("result", myAnno);

        // createQuery
        List<DOBook> bookList = bookService.getAll();
        // testCache(bookList);
        model.addAttribute("bookList", bookList);

        // Named query
        List<DOBook> namedQueryList = bookService.findByBookIdGreaterThan(1);
        model.addAttribute("namedQueryList", namedQueryList);
        int maxBookId = bookService.getMaxBookId();
        model.addAttribute("maxBookId", maxBookId);

        // NamedNative query
        BookModel bookView = bookService.findByTitleNative("book2");
        model.addAttribute("bookView", bookView);

        // Entity manager properties
        model.addAttribute("emProperties", bookService.getEMProperties());

        model.addAttribute("queryLookup",
                "GET_BOOKS:" + queryRepo.getQuery("GET_BOOKS"));

        model.addAttribute("cacheInfo", CacheUtil.getCacheInfo());

        return "books";

    }

    //UNUSED
    
    @RequestMapping("/book")
    public ResponseEntity<String> getBook() {
        DOBook book = bookService.getBook(5);
        ResponseEntity<String> response = new ResponseEntity<>("Response:<br>"
                + book.toString(), HttpStatus.OK);
        return response;
    }

    @RequestMapping("/init")
    public ResponseEntity<String> init() {
        Arrays.asList("book1", "book2", "book3", "book4").stream()
                .forEach(e -> bookService.saveBook(e));
        ResponseEntity<String> response = new ResponseEntity<>(
                "Saved books...", HttpStatus.OK);
        return response;
    }
}
