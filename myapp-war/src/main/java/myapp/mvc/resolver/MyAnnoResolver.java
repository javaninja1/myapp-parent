package myapp.mvc.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import myapp.mvc.annotation.MyAnno;


public class MyAnnoResolver implements HandlerMethodArgumentResolver {
    private static final Logger LOG = LoggerFactory.getLogger(MyAnnoResolver.class);

    public MyAnnoResolver() {
        super();
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(String.class)
                && parameter.hasParameterAnnotation(MyAnno.class);
    }

    @Override
    public String resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        LOG.debug("Resolving method param for MyAnno");
        return "annotation111";
    }

}
