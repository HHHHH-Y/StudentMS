package huyue.servlet;

import huyue.dao.StudentDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-08-02
 */
@WebServlet("/student/delete")
public class StudentDeleteServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 由 GET student/delete?ids=2&ids=3 的 Query String 可以得出, 相同的 key 却对应不同的 value, 所以需要使用 getParameterValues() 方法返回一个 value 的字符串数组
        String[] ids = req.getParameterValues("ids"); // key = ids 时对应的 value 字符串数组
        StudentDAO.delete(ids);
        return null;
    }
}
