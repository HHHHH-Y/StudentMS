package huyue.dao;

import huyue.model.Classes;
import huyue.model.DictionaryTag;
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
 * Date: 2020-08-02
 */
public class ClassesDAO {
    public static List<Classes> queryAsDict() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Classes> list = new ArrayList<>();
        try {
            // 1. 获取数据库连接
            c = DBUtil.getConnection();
            // 在复制 sql 语句的时候, 在需要空格的地方加上空格或者 tab 缩进
            String sql = "select id, classes_name, classes_graduate_year, classes_major from classes";
            // 2. 创建操作命令对象
            ps = c.prepareStatement(sql);
            // 3. 执行 sql 语句
            rs = ps.executeQuery();
            // 4. 处理查询结果集
            while (rs.next()) {
                Classes classes = new Classes();
                // 设置属性: 通过结果集获取, 并设置
                classes.setDictionaryTagKey(String.valueOf(rs.getInt("id"))); // DictionaryTagKey 这是一个 String 类型的
                classes.setDictionaryTagValue(rs.getString("classes_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassMajor(rs.getString("classes_major"));
                list.add(classes);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("查询班级数据字典出错", e);
        } finally {
            DBUtil.close(c, ps, rs);
        }
    }
}
