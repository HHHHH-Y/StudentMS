package huyue.util;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * Description: 数据库连接
 * User: HHH.Y
 * Date: 2020-07-26
 */
public class DBUtil {

    /**
     * 数据库 JDBC 的操作步骤
     * 1. 创建数据库连接 Connection
     * 2. 创建操作命令对象 Statement
     * 3. 执行 sql 语句
     * 4. 如果是查询操作, 处理结果集
     * 5. 释放资源
     */

    private static final String URL = "jdbc:mysql://localhost:3306/stu_info?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "huyuelover1017";

    private static volatile DataSource dataSource;
    private DBUtil() {}
    // 使用单例模式中的懒汉模式创建 Connection 对象
    private static DataSource getDataSource() {
        if(dataSource == null) {
            synchronized (DBUtil.class) {
                if(dataSource == null) {
                    dataSource = new MysqlDataSource();

                    // 对 dataSource 进行初始化
                    ((MysqlDataSource) dataSource).setURL(URL);
                    ((MysqlDataSource) dataSource).setUser(USERNAME);
                    ((MysqlDataSource) dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    // 对外提供使用该连接的方法
    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库连接失败", e);
        }
    }

    // 释放资源
    // 如果是更新操作
    public static void close(Connection c, Statement s) {
        close(c, s, null);
    }
    // 如果是查询操作
    public static void close(Connection c, Statement s, ResultSet r) {
        try {
            if(r != null) {
                r.close();
            }
            if(s != null) {
                s.close();
            }
            if(c != null) {
                c.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("释放数据库资源出错", e);
        }
    }
}
