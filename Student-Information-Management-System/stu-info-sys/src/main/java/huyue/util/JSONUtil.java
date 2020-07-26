package huyue.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * Description: 序列化为 json 字符串和反序列化 json 字符串
 * User: HHH.Y
 * Date: 2020-07-26
 */
public class JSONUtil {

    // jackson 框架提供的一个处理 json 的类: ObjectMapper
    private static final ObjectMapper M = new ObjectMapper();
    static {
        // 设置序列化和反序列化的日期格式
        M.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 反序列化 json 字符串为 java 对象
     * 1. 由于输出的 java 对象的类型是不确定的, 所以应该使用泛型
     * 2. 从输入流中读取 json 字符串, 返回 T 类型的 java 对象
     *
     * json 为什么要从输入流中进行读取呢?
     * 因为 json 的格式是 ("k1" : "v1"), 而 getParameter() 只能读取 k1 = v1 & k2 = v2 格式的数据
     * 所以要使用 getInputStream() 方法
     */
    public static <T> T read(InputStream is, Class<T> clazz) {
        try {
            return M.readValue(is, clazz);
        } catch (IOException e) {
            throw new RuntimeException("json 反序列化失败, 传入的数据格式和 class 类型不匹配", e);
        }
    }

    // 序列化 java 对象为 json 字符串
    public static String write(Object o) {
        try {
            return M.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json 序列化失败", e);
        }
    }
}
