package huyue.servlet;

import huyue.dao.StudentDAO;
import huyue.model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 查询学生信息
 * User: HHH.Y
 * Date: 2020-08-01
 */
@WebServlet("/student/query")
public class StudentQueryServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 因为要查询的学生信息是所有的学生信息, 所以 query() 只需要调用无参的即可
        List<Student> students = StudentDAO.query();
        return students;
    }
}
