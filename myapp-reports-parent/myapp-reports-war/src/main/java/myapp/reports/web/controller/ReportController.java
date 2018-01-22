package myapp.reports.web.controller;

import java.util.Arrays;
import java.util.List;

import myapp.dao.stub.IQueryRepository;
import myapp.entity.domain.DOBook;
import myapp.entity.view.ViewBook;
import myapp.model.BookReportModel;
import myapp.mvc.annotation.MyAnno;
import myapp.service.stub.IBookReportService;
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
public class ReportController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    IBookService bookService;

    @Autowired
    IBookReportService bookReportService;

    @Autowired
    IQueryRepository queryRepo;
    
    @RequestMapping( "/report")
    public ResponseEntity<String> getBook() {
        DOBook book = bookService.getBook(5);
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

    @RequestMapping( path="/report.do", method=RequestMethod.POST)
    public String postReport(Model model) {
        Arrays.asList("book1","book2")
        .stream()
        .forEach(e -> bookService.saveBook(e));
        
        return "redirect:/report.jsp";
        
    }
    
    @RequestMapping( path="/report.do", method=RequestMethod.GET)
    public String getReport(Model model, @MyAnno String myAnno) {
        LOG.debug("myAnno:{}", myAnno);
        
        //annotation
        model.addAttribute("result", myAnno);
        
       // model.addAttribute("result", myAnno);
        
        //createQuery
        List<DOBook> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);
        
        //Named query
        List<DOBook> namedQueryList = bookService.findByBookIdGreaterThan(1);
        model.addAttribute("namedQueryList", namedQueryList);
        
        //NamedNative query
        ViewBook viewBook = bookReportService.findByTitleNative("Book 2");
        model.addAttribute("viewBook", viewBook);
        
        
      //NamedNative query
        BookReportModel bookReportModel = bookReportService.findByTitleNativeWithSqlMapping("Book 2");
        model.addAttribute("bookReportModel", bookReportModel);
        
        
        //TODO: make this work
        //BookReport bookReport = bookReportService.findByTitleNative("book2");
        
        model.addAttribute("queryLookup", "ViewBook.findByTitle: " + queryRepo.getQuery("ViewBook.findByTitle"));
        
        return "report";
        
    }

}
