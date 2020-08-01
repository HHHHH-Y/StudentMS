package huyue.servlet;

import huyue.model.Response;
import huyue.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-07-26
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        // 通过输出流向 HTTP 前端发送内容
        PrintWriter writer = resp.getWriter();
        Response response = new Response();
        response.setCode("200");
        response.setMessage("操作成功");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        response.setData(list);
        // 将 response 转换成一个 json 字符串打印在前端界面上
        writer.println(JSONUtil.write(response));
        writer.flush();
    }
}
