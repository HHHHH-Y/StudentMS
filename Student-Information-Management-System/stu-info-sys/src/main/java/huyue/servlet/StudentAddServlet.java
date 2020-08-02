package huyue.servlet;

import huyue.dao.StudentDAO;
import huyue.model.Student;
import huyue.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description: 新增学生信息
 * User: HHH.Y
 * Date: 2020-08-02
 */
@WebServlet("/student/add")
public class StudentAddServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 由于请求信息是 JSON 格式的, 所以需要将 JSON 格式转换成 Java 对象
        Student student = JSONUtil.read(req.getInputStream(), Student.class);
        StudentDAO.insert(student);
        return null;
    }
}
