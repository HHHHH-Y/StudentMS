package huyue.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Description: 对应前端的响应
 * User: HHH.Y
 * Date: 2020-07-26
 */
@Getter
@Setter
@ToString
public class Response {
    private boolean success; // 业务是否处理成功
    private String code; // 错误码
    private String message; // 错误消息
    private Object data; // 业务字段
    private String stackTrace; // 出现异常时, 堆栈信息
}
