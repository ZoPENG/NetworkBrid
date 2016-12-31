package com.network.bird.mvc.filter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest request2 = servletRequest;
        if (servletRequest instanceof HttpServletRequest && !ServletFileUpload.isMultipartContent((HttpServletRequest) servletRequest)) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            if(("PUT".equals(httpRequest.getMethod())) || ("DELETE".equals(httpRequest.getMethod()))){
                HttpInputMessage inputMessage = new ServletServerHttpRequest(httpRequest){
                    @Override
                    public InputStream getBody() throws IOException {
                        return servletRequest.getInputStream();
                    }
                };
                MultiValueMap<String, String> formParameters = formHttpMessageConverter.read(null, inputMessage);
                request2 = new HttpFormContentRequestWrapper(httpRequest, formParameters);//PUT DELETE 特殊处理
            }
        }
        filterChain.doFilter(request2, servletResponse);
    }

    public void destroy() {}

    /**
     * TODO HttpServletRequestWrapper
     */
    private static class HttpFormContentRequestWrapper extends HttpServletRequestWrapper{

        private MultiValueMap<String, String> formParameters;

        public HttpFormContentRequestWrapper(HttpServletRequest request ,MultiValueMap<String, String> parameters) {
            super(request);
            this.formParameters = parameters != null ? parameters : new LinkedMultiValueMap<String, String>();
        }

        /**
         * TODO
         * @param name
         * @return
         */
        @Override
        public String getParameter(String name){
            String queryStringValue = super.getParameter(name);// TODO super 是个对象？
            String formValue = this.formParameters.getFirst(name);
            return (queryStringValue != null) ? queryStringValue : formValue;
        }

        @Override
        public Enumeration<String> getParameterNames(){
            Set<String> names = new LinkedHashSet<String>();
            names.addAll(Collections.list(super.getParameterNames()));
            names.addAll(this.formParameters.keySet());
            return Collections.enumeration(names); // TODO enumeration 什么类
        }

        /**
         * TODO
         *
         * @param name
         * @return
         */
        @Override
        public String[] getParameterValues(String name){
            String[] queryStringValues = super.getParameterValues(name);
            List<String> formValues = this.formParameters.get(name);

            if(formValues == null) {
                return queryStringValues;
            } else if(queryStringValues == null) {
                return formValues.toArray(new String[formValues.size()]);
            } else{
                List<String> result = new ArrayList<String>();
                result.addAll(Arrays.asList(queryStringValues));
                result.addAll(formValues);
                return result.toArray(new String[result.size()]);
            }
        }
    }
}
