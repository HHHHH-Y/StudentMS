package huyue.dao;

import huyue.model.DictionaryTag;
import huyue.model.Student;
import huyue.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-08-01
 */
public class StudentDAO {
    public static List<Student> query() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            // 1. 获取数据库连接
            c = DBUtil.getConnection();
            // 在复制 sql 语句的时候, 在需要空格的地方加上空格或者 tab 缩进
            String sql = ""; // 关联班级表和学生表, 查询需要的数据(学生的数据和班级的数据)
            // 2. 创建操作命令对象
            ps = c.prepareStatement(sql);
            // 3. 执行 sql 语句
            rs = ps.executeQuery();
            // 4. 处理查询结果集
            while (rs.next()) {
                Student student = new Student();
                // 设置属性, 通过结果集获取来设置
                list.add(student);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("查询学生列表出错", e);
        } finally {
            DBUtil.close(c, ps, rs);
        }
    }
}
