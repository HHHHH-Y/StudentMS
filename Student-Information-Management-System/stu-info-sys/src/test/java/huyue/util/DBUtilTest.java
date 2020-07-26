package huyue.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * Description: 测试数据库连接是否可以获取到
 * User: HHH.Y
 * Date: 2020-07-26
 */
public class DBUtilTest {
    @Test
    public void testConnection() {
        Assert.assertNotNull(DBUtil.getConnection());
    }
}
