package huyue.servlet;

import huyue.dao.DictionaryTagDAO;
import huyue.model.DictionaryTag;
import huyue.model.Response;
import huyue.util.JSONUtil;
import huyue.util.ThreadLocalHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 写一个抽象的父类, 将其作为 servlet 的模板
 *
 * 模板方法是提供一种统一的处理逻辑, 在不同的条件调用不同的方法(子类重写的方法), 理解模板方法设计, 理解 java 多态的知识
 * User: HHH.Y
 * Date: 2020-08-01
 */
public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); // 只需要在 doPost 中完成一段逻辑, 就可以完成所有 doGet 和 doPost 的请求
    }

    // 模板方法. 参照 HTTPServlet 的 service 和 doXXX() 方法的关系, service 就是一个模板方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        // 通过输出流向 HTTP 前端发送内容
        PrintWriter writer = resp.getWriter();
        Response response = new Response();

        // HttpServletRequest 对象.getParameter() 方法, 可以获取哪些请求数据? 可以获取到 url 和请求体中, 满足 k1=v1&k2=v2 格式的数据
        // 一般说表单提交的默认方式, 表示的意思是请求的 Content-Type 字段 x-www-form-urlencoded
        // get 会怎么样? 在 url 中.  post 会怎么样? 在请求体中.  格式是 k1=v1&k2=v2
        // 表单不使用默认的方式时, 比如手写前端代码, 发送 Ajax 请求, 请求格式为 application / json: 请求体, 数据为 json 的字符串
        // HttpServletRequest 对象.getInputStream() 通过输入流获取. 请求体都可以获取到, 怎么解析依赖自己的代码实现
        // 找到要查找的 key
        // 从数据库中查找 key 对应的 value 值, 放到 tags 中, 如果在查找数据库时出现了异常, 应该要进行异常的处理
        try {
            // 所有的子类都需要重写这个方法, 且如果产生异常, 也方便处理这个异常
            Object o = process(req, resp);
            // 执行 jdbc 成功之后才设置的这些选项
            response.setSuccess(true);
            response.setCode("200");
            response.setTotal(ThreadLocalHolder.getTOTAL().get()); // 不管是否分页操作, 分页接口都获取当前线程中的 total 变量
            response.setMessage("操作成功");
            response.setData(o);
        } catch (Exception e) {
            response.setCode("ERR500");
            response.setMessage(e.getMessage()); // 将捕获到的异常设置到 message 中
            // 获取异常的堆栈信息
            StringWriter sw = new StringWriter(); // 相关的堆栈信息保存在 sw 中
            // 从输出流中拿到信息放入 sw 中
            PrintWriter pw = new  PrintWriter(sw);
            // 将堆栈信息放入到输出流 pw 中
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            System.err.println(stackTrace); // 先将堆栈信息打印到控制台
            response.setStackTrace(stackTrace); // 将堆栈信息放入响应信息中
        } finally {
            ThreadLocalHolder.getTOTAL().remove(); // 在线程结束前, 一定要记得删除变量, 如果不删除, 可能存在内存泄漏的问题
        }
        // 将 response 转换成一个 json 字符串打印在前端界面上
        writer.println(JSONUtil.write(response));
        writer.flush();
    }

    // 该方法可能会由于数据库查找错误而抛出异常
    protected abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
