package com.network.bird.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面控制器，控制页面跳转
 */
@Controller
public class PageController {

    /**
     * 跳转到首页
     *
     * @return 首页的地址
     */
    @RequestMapping(value = {"", "index", "index.html"}, method = RequestMethod.GET)
    public String gotoHome() {
        return "index";
    }

    /**
     * 跳转到other文件夹下的网页
     *
     * @return 网页地址
     */
    @RequestMapping(value = "/other/**", method = RequestMethod.GET)
    public String gotoOther(HttpServletRequest request) {
        return rewriteUri(request);
    }

    /**
     * 跳转到admin文件夹下的网页
     *
     * @return 网页地址
     */
    @RequestMapping(value = "/admin/**", method = RequestMethod.GET)
    public String gotoAdmin(HttpServletRequest request) {
            return rewriteUri(request);
    }

    /**
     * 跳转到API页面
     *
     * @param request HTTP请求
     * @return 页面地址
     */
    @RequestMapping(value = "/api/**", method = RequestMethod.GET)
    public String gotoAPI(HttpServletRequest request) {
        return rewriteUri(request);
    }

    /**
     * 跳转到应用页面
     *
     * @param request HTTP请求
     * @return 应用相关页面地址
     */
    @RequestMapping(value = "/app/**", method = RequestMethod.GET)
    public String gotoAPP(HttpServletRequest request) {
        String uri = rewriteUri(request);
        return uri;
    }

    /**
     * 跳转到文章页面
     *
     * @param request HTTP请求
     * @return 页面地址
     */
    @RequestMapping(value = "/article/**", method = RequestMethod.GET)
    public String gotoArticle(HttpServletRequest request) {
        return rewriteUri(request);
    }

    /**
     * 重写uri，截取掉开头的应用名和结尾的html后缀
     *
     * @param request http请求
     * @return 新的uri
     */
    private String rewriteUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String path = request.getContextPath();
        if (path != null && path.length() > 0 && uri.startsWith(path)) {
            uri = uri.substring(path.length());
        }
        if (uri.endsWith(".html")) {
            uri = uri.substring(0, uri.lastIndexOf("."));
        }
        return uri.substring(1);//去掉 “/” jetty 会找不到路径
    }

}
