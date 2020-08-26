package huyue.dao;

import huyue.model.Classes;
import huyue.model.Student;
import huyue.model.User;
import huyue.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户登录时数据库的操作
 * User: HHH.Y
 * Date: 2020-08-02
 */
public class UserDAO {
    public static User query(User u) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User query = null;
        try {
            // 1. 获取数据库连接
            c = DBUtil.getConnection();
            // 在复制 sql 语句的时候, 在需要空格的地方加上空格或者 tab 缩进
            String sql = "select id, nickname, email, create_time from user where username = ? and password = ?";
            // 2. 创建操作命令对象
            ps = c.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            // 3. 执行 sql 语句
            rs = ps.executeQuery();
            // 4. 处理查询结果集
            while (rs.next()) {
                // 查询出来的对象一定有数据
                query = u;
                query.setId(rs.getInt("id"));
                query.setNickname(rs.getString("nickname"));
                query.setEmail(rs.getString("email"));
                query.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
            }
            return query;
        } catch (Exception e) {
            throw new RuntimeException("登录用户名密码校验出错", e);
        } finally {
            DBUtil.close(c, ps, rs);
        }
    }
}
