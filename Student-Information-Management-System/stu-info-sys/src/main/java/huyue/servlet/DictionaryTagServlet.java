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
public class DictionaryTagServlet extends AbstractBaseServlet {

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String key = req.getParameter("dictionaryKey");
        List<DictionaryTag> tags = DictionaryTagDAO.query(key);
        return tags;
    }
}
