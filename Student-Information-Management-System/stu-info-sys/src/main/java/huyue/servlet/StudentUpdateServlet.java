package huyue.servlet;

import huyue.dao.StudentDAO;
import huyue.model.Student;
import huyue.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-08-02
 */
@WebServlet("/student/update")
public class StudentUpdateServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 需要从请求信息中拿到需要更改的学生信息
        Student student = JSONUtil.read(req.getInputStream(), Student.class);
        StudentDAO.update(student);
        return null;
    }
}
