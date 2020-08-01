package huyue.servlet;

import huyue.dao.DictionaryTagDAO;
import huyue.model.DictionaryTag;
import huyue.model.Response;
import huyue.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 查询专业字典
 * User: HHH.Y
 * Date: 2020-08-01
 */
// Servlet 路径一定要以 "/" 开头, 否则启动 Tomcat 就会报错
@WebServlet("/dict/tag/query")
public class DictionaryTagServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        List<DictionaryTag> tags = null;
        // 从数据库中查找 key 对应的 value 值, 放到 tags 中, 如果在查找数据库时出现了异常, 应该要进行异常的处理
        try {
            String key = req.getParameter("dictionaryKey");
            tags = DictionaryTagDAO.query(key);
            // 执行 jdbc 成功之后才设置的这些选项
            response.setSuccess(true);
            response.setCode("200");
            response.setMessage("操作成功");
        } catch (Exception e) {
            response.setCode("ERR500");
            response.setMessage(e.getMessage()); // 将捕获到的异常设置到 message 中
            // 获取异常的堆栈信息
            StringWriter sw = new StringWriter(); // 相关的堆栈信息保存在 sw 中
            // 从输出流中拿到信息放入 sw 中
            PrintWriter pw = new PrintWriter(sw);
            // 将堆栈信息放入到输出流 pw 中
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            System.err.println(stackTrace); // 先将堆栈信息打印到控制台
            response.setStackTrace(stackTrace); // 将堆栈信息放入响应信息中

        }
        response.setData(tags);
        // 将 response 转换成一个 json 字符串打印在前端界面上
        writer.println(JSONUtil.write(response));
        writer.flush();
    }
}
