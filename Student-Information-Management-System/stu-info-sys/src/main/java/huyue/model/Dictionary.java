package huyue.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-07-31
 */
@Setter
@Getter
@ToString
public class Dictionary {
    private Integer id;
    private String dictionaryKey;
    private String dictionaryValue;
    private String dictionaryDesc;
    private Date createTime;
}
