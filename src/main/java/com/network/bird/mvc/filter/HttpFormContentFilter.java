package com.network.bird.mvc.filter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * 默认情况下使用request.getParameter()等方法时无法获得PUT和DELETE请求体中的参数，只能获得url中的参数，该过滤器解决这个问题
 * </p>
 * Created by zhoupeng on 2016/12/28.
 */
public class HttpFormContentFilter implements Filter {

    private final FormHttpMessageConverter formHttpMessageConverter = new AllEncompassingFormHttpMessageConverter();

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest request2 = servletRequest;
        if (servletRequest instanceof HttpServletRequest && !ServletFileUpload.isMultipartContent((HttpServletRequest) servletRequest)) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        }
    }

    public void destroy() {

    }
}
