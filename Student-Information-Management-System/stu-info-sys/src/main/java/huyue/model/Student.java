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
public class Student {
    private Integer id;
    private String studentName;
    private String studentNo;
    private String idCard;
    private String studentEmail;
    private Integer classesId;
    private Date createTime;
    private Classes classes;
}
