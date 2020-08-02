package huyue.servlet;

import huyue.dao.StudentDAO;
import huyue.model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description: 查询学生详情信息
 * User: HHH.Y
 * Date: 2020-08-02
 */
@WebServlet("/student/queryById")
public class StudentQueryByIdServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        // 由于数据库中 id 是 int 类型的, 所以需要将它变成 int 类型的
        Student student = StudentDAO.queryById(Integer.parseInt(id));
        return student;
    }
}
