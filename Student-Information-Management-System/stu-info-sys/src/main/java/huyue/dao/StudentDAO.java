package huyue.dao;

import huyue.model.Classes;
import huyue.model.DictionaryTag;
import huyue.model.Student;
import huyue.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
            String sql = "select s.id," +
                    "       s.create_time," +
                    "       s.student_name," +
                    "       s.student_no," +
                    "       s.id_card," +
                    "       s.student_email," +
                    "       s.classes_id," +
                    "       c.id cid," +
                    "       c.classes_name," +
                    "       c.classes_graduate_year," +
                    "       c.classes_major," +
                    "       c.classes_desc" +
                    "   from student s" +
                    "         join classes c on s.classes_id = c.id"; // 关联班级表和学生表, 查询需要的数据(学生的数据和班级的数据)
            // 2. 创建操作命令对象
            ps = c.prepareStatement(sql);
            // 3. 执行 sql 语句
            rs = ps.executeQuery();
            // 4. 处理查询结果集
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentNo(rs.getString("student_no"));
                student.setIdCard(rs.getString("id_card"));
                student.setStudentEmail(rs.getString("student_email"));
                student.setClassesId(rs.getInt("classes_id"));
                // new Date() 的类型是 util 的, 但是从数据库中拿到的时间是 sql 的, 所以需要使用 .getTime() 方法将其转换成 long 类型
                student.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
                Classes classes = new Classes();
                classes.setId(rs.getInt("cid"));
                classes.setClassesName(rs.getString("classes_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassMajor(rs.getString("classes_major"));
                classes.setClassDesc(rs.getString("classes_desc"));
                student.setClasses(classes);
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

    public static Student queryById(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = new Student();
        try {
            // 1. 获取数据库连接
            c = DBUtil.getConnection();
            // 在复制 sql 语句的时候, 在需要空格的地方加上空格或者 tab 缩进
            String sql = "select s.id," +
                    "       s.create_time," +
                    "       s.student_name," +
                    "       s.student_no," +
                    "       s.id_card," +
                    "       s.student_email," +
                    "       s.classes_id," +
                    "       c.id cid," +
                    "       c.classes_name," +
                    "       c.classes_graduate_year," +
                    "       c.classes_major," +
                    "       c.classes_desc" +
                    "   from student s" +
                    "         join classes c on s.classes_id = c.id" +
                    "   where s.id = ?"; // 关联班级表和学生表, 查询需要的数据(学生的数据和班级的数据)
            // 2. 创建操作命令对象
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            // 3. 执行 sql 语句
            rs = ps.executeQuery();
            // 4. 处理查询结果集
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentNo(rs.getString("student_no"));
                student.setIdCard(rs.getString("id_card"));
                student.setStudentEmail(rs.getString("student_email"));
                student.setClassesId(rs.getInt("classes_id"));
                // new Date() 的类型是 util 的, 但是从数据库中拿到的时间是 sql 的, 所以需要使用 .getTime() 方法将其转换成 long 类型
                student.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
                Classes classes = new Classes();
                classes.setId(rs.getInt("cid"));
                classes.setClassesName(rs.getString("classes_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassMajor(rs.getString("classes_major"));
                classes.setClassDesc(rs.getString("classes_desc"));
                student.setClasses(classes);
                // 设置属性, 通过结果集获取来设置
            }
            return student;
        } catch (Exception e) {
            throw new RuntimeException("查询学生详情信息出错", e);
        } finally {
            DBUtil.close(c, ps, rs);
        }
    }

    public static void insert(Student student) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            // 1. 获取数据库连接
            c = DBUtil.getConnection();
            // 在复制 sql 语句的时候, 在需要空格的地方加上空格或者 tab 缩进
            String sql = "insert into student (student_name, student_no, id_card, classes_id, student_email) values (?,?,?,?,?)";
            // 2. 创建操作命令对象
            ps = c.prepareStatement(sql);
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getStudentNo());
            ps.setString(3, student.getIdCard());
            ps.setInt(4, student.getClassesId());
            ps.setString(5, student.getStudentEmail());
            // 3. 执行 sql 语句 (获取插入成功的数据个数)
            int num = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("新增学生信息出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }

    public static void update(Student student) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            // 1. 获取数据库连接
            c = DBUtil.getConnection();
            // 在复制 sql 语句的时候, 在需要空格的地方加上空格或者 tab 缩进
            String sql = "update student set student_name = ?, student_no = ?, id_card = ?, classes_id = ?, student_email = ? where id = ?"; // 是根据学生 id 进行学生信息的修改
            // 2. 创建操作命令对象
            ps = c.prepareStatement(sql);
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getStudentNo());
            ps.setString(3, student.getIdCard());
            ps.setInt(4, student.getClassesId());
            ps.setString(5, student.getStudentEmail());
            ps.setInt(6, student.getId());
            // 3. 执行 sql 语句 (获取更改成功的数据个数)
            int num = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("修改学生信息出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }

    public static void delete(String[] ids) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            // 1. 获取数据库连接
            c = DBUtil.getConnection();
            // 在复制 sql 语句的时候, 在需要空格的地方加上空格或者 tab 缩进
            // 由于删除的是字符串数组中的内容, 没有办法具体确定, 所以需要使用字符串拼接的方式对 sql 语句进行拼接
            StringBuilder sql = new StringBuilder("delete from student where id in(");
            for (int i = 0; i < ids.length; i++) {
                if(i != 0) {
                    sql.append(",");
                }
                sql.append("?");
            }
            sql.append(")");
            // 2. 创建操作命令对象
            ps = c.prepareStatement(sql.toString());
            for (int i = 0; i < ids.length; i++) {
                ps.setInt(i + 1, Integer.parseInt(ids[i])); // JDBC 设置占位符是从 1 开始的
            }
            // 3. 执行 sql 语句 (获取插入成功的数据个数)
            int num = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("删除学生信息出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }
}
