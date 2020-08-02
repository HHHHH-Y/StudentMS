package huyue.filter;

import huyue.model.Response;
import huyue.util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description: 写一个过滤器的算法, 主要是用来限制登录用户
 * 如果用户没有登录, 需要重定向到登录页面
 * 如果用户登录了, 就可以跳转至 main 页面
 * User: HHH.Y
 * Date: 2020-08-02
 */

/**
 * 配置过滤器, 只要请求路径配置到过滤器路径, 都会先执行过滤器的 doFilter 方法.
 * 至于是否往后边的顺序执行, 依赖我们是否再次调用 filterChain.doFilter 方法
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 由于这里的 request 和 response 都是 Servlet 类型的, 所以需要强制类型转换
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        // 后台的接口只校验除登录接口之外的后台接口, 没有登录的时候, 不允许访问
        // 前端的资源: 只校验 /public/page/main.html 首页, 其他的都放行
        String uri = req.getServletPath(); // 获取请求路径
        // 只有在这种情况下才会进行校验, 其他时候都不会进行校验
        HttpSession session = req.getSession(false); // 没有 session 的时候, 返回 null
        // 判断是否登录了, 如果没有登录
        if (session == null) {
            // 首页重定向到登录页面, 如果是后端接口, 就返回错误的 json 数据
            req.setCharacterEncoding("utf-8");
            res.setCharacterEncoding("utf-8");
            // 首页重定向到登录页面
            if ("/public/page/main.html".equals(uri)) {
                res.setContentType("text/html; charset=UTF-8");
                // 重定向
                String schema = req.getScheme(); // http 协议
                String host = req.getServerName(); // 服务器 ip
                int port = req.getServerPort(); // 端口号
                String ctx = req.getContextPath(); // 项目部署路径, 应用上下文
               /* res.sendRedirect("/public/index/html"); // 这种写法跳转会出问题
                res.sendRedirect("public/index/html"); // 这种写法跳转会出问题*/
               String basePath = schema + "://" + host + ":" + port + ctx;
                res.sendRedirect(basePath + "/public/index.html");
                return;
                // 未登录的情况下, 拦截操作
            } else if (!uri.startsWith("/public/") && !uri.startsWith("/static/") && !"/user/login".equals(uri)){
                // 请求后端非登陆接口, 未登录不允许访问的请求, 一般返回 401 状态码
                res.setContentType("application/json");
                PrintWriter writer = res.getWriter();

                Response response = new Response();
                response.setCode("ERR401");
                response.setMessage("不允许访问");
                res.setStatus(401);
                writer.println(JSONUtil.write(response));
                writer.flush();
                return;
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
