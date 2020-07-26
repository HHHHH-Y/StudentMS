package huyue.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 测试 json 序列化和反序列化是否可以成功
 * User: HHH.Y
 * Date: 2020-07-26
 */
public class JSONUtilTest {
    @Test
    public void testWrite() {
        // 测试 将一个 java 对象序列化成 json 字符串
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        String s = JSONUtil.write(map);
        System.out.println(s);
        Assert.assertNotNull(s);
    }

    @Test
    public void testRead() {
        // 输入流可以通过类加载器获取, 获取 test.json 中的内容
        InputStream is = getClass().getClassLoader().getResourceAsStream("test.json");
        HashMap<String, String> map = JSONUtil.read(is, HashMap.class);
        System.out.println(map);
        Assert.assertNotNull(map);
    }

}
