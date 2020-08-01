package huyue.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 建立数据库和 Java 实体类的映射关系
 * User: HHH.Y
 * Date: 2020-07-31
 */
@Setter
@Getter
@ToString
public class DictionaryTag {
    // 这里需要使用包装数据类型, 因为 new 对象的时候, 基本数据类型的默认值可能会出现问题
    private Integer id;
    private String dictionaryTagKey;
    private String dictionaryTagValue;
    private String dictionaryTagDesc;
    private Integer dictionaryId;
    // 一般日期类型使用 java.util.Date 和数据库映射
    private Date createTime;
}
