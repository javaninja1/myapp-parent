package myapp.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;



public class MyTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String param1;
    
    
    public MyTag() {
        super();
    }
    
    

    public String getParam1() {
        return param1;
    }



    public void setParam1(String param1) {
        this.param1 = param1;
    }



    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.print("<span style='color:blue'>");
            out.print("Param1:" + param1);
            out.print("</span>");
          
        } catch (IOException e) {
            throw new JspException(e);
        }
        return EVAL_BODY_INCLUDE;
    }
    
    
}