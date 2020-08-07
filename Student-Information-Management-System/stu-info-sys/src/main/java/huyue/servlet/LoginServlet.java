package huyue.servlet;

import huyue.dao.UserDAO;
import huyue.model.User;
import huyue.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description: 登录
 * User: HHH.Y
 * Date: 2020-08-02
 */
@WebServlet("/user/login")
public class LoginServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 因为读取的是 JSON 数据, 所以应该从输入流中读取数据转化成 java 对应的类
        User user = JSONUtil.read(req.getInputStream(), User.class); // 前端请求的用户信息
        // 数据库校验用户名和密码
        User query = UserDAO.query(user);
        // 判断查询出来是否有用户存在
        if(query == null) {
            throw new RuntimeException("用户名或者密码错误, 校验失败");
        } else {
            // 通过用户名和密码查询到用户时, 生成 session, 并将用户保存到 session 中
            HttpSession session = req.getSession(); // = getSession(true), 如果没有 session, 就自动生成一个 session
        }
        return null;
    }
}
