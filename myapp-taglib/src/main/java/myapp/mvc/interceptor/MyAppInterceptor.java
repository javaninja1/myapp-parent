package myapp.mvc.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class MyAppInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(MyAppInterceptor.class);

    private List<String> colorList;

    
    @Autowired
    public List<String> getColorCodeList() {
        return colorList;
    }
    
    @Autowired
    @Resource(name = "colorList")
    public void setColorCodeList(List<String> colorList) {
        this.colorList = colorList;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        LOG.debug("preHandle...");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        LOG.trace("START: postHandle");
       // String colorList = (String) request.getAttribute("colorList");
        modelAndView.addObject("colorList", colorList);
        LOG.trace("END: postHandle");    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        LOG.debug("afterCompletion...");
    }

    
}