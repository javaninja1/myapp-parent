package myapp.mvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import myapp.dao.stub.IQueryRepository;
import myapp.model.Book;
import myapp.model.BookView;
import myapp.mvc.annotation.MyAnno;
import myapp.service.stub.IBookService;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.management.Cache;
import net.sf.ehcache.statistics.StatisticsGateway;

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
    
    


    private List<String> getCacheInfo() {
        List<String> cacheInfo = new ArrayList<>();
        List<CacheManager> tempManagers = CacheManager.ALL_CACHE_MANAGERS;
        cacheInfo.add("Number of CacheManagers:" +  tempManagers.size());
        
        for (CacheManager tempCM : tempManagers) {
            cacheInfo.add("Cache Manager Name:" + tempCM.getName());
            cacheInfo.add("ActiveConfigurationText:"+  tempCM.getActiveConfigurationText());
             String[] cacheNames = tempCM.getCacheNames();
             cacheInfo.add("*********************************************");

             for (int i = 0; i < cacheNames.length; i++) {
                 
                    String cacheName = cacheNames[i];
                    cacheInfo.add("******" + cacheName + "******");
                    Ehcache cache = tempCM.getEhcache(cacheName);
                    cacheInfo.add("MaxEntriesLocalHeap:" + cache.getCacheConfiguration().getMaxEntriesLocalHeap());
                    StatisticsGateway stats = cache.getStatistics();
                    cacheInfo.add("localHeapSizeInBytes:" +  stats.getLocalHeapSizeInBytes());
                    cacheInfo.add("cacheHitCount:" + stats.cacheHitCount());
                    cacheInfo.add("cacheMissCount:" + stats.cacheMissCount());
                    cacheInfo.add("*********************************************");
             }
                
       }
        return cacheInfo;
    }
    
    @RequestMapping( "/init")
    public ResponseEntity<String> init() {
        
        Arrays.asList("book1","book2","book3","book4")
             .stream()
             .forEach(e -> bookService.saveBook(e));
        
        ResponseEntity<String> response = new ResponseEntity<>("Saved books..." ,
                 HttpStatus.OK);
        return response;
    }

    @RequestMapping( path="/books.do", method=RequestMethod.POST)
    public String postBooks(Model model) {
        IntStream.of(1, 2).forEach(e -> bookService.saveBook("Book " + e));
        return "redirect:/books.jsp";
        
    }
    
    @RequestMapping( path="/books.do", method=RequestMethod.GET)
    public String getBooks(Model model, @MyAnno String myAnno) {
        //annotation
        model.addAttribute("result", myAnno);
        
        //createQuery
        List<Book> bookList = bookService.getAll();
        //testCache(bookList);
        model.addAttribute("bookList", bookList);
        
        //Named query
        List<Book> namedQueryList = bookService.findByBookIdGreaterThan(1);
        model.addAttribute("namedQueryList", namedQueryList);
        int maxBookId = bookService.getMaxBookId();
        model.addAttribute("maxBookId", maxBookId);

        
        //NamedNative query
        BookView bookView = bookService.findByTitleNative("book2");
        model.addAttribute("bookView", bookView);
        
        
        //Entity manager properties
        model.addAttribute("emProperties", bookService.getEMProperties());
        
        model.addAttribute("queryLookup", "GET_BOOKS:" + queryRepo.getQuery("GET_BOOKS"));
        
        model.addAttribute("cacheInfo", getCacheInfo());
        
        return "books";
        
    }




    private void testCache(List<Book> bookList) {
        CacheManager cm = CacheManager.getInstance();
        net.sf.ehcache.Cache cache = cm.getCache("myapp.model.Book");
        bookList.forEach( e -> cache.put(new Element(e.getBookId(),e)) );
        
        //Get the element from cache
        Element ele = cache.get(new Integer(1));
        if (ele != null) {
          Book b = (Book)ele.getObjectValue();
          LOG.debug("book from cache:" + b);
        } else {
           LOG.debug("book not in cache cache: id:1");
        }
    }

}
