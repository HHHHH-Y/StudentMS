package huyue.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description: 分页
 * 将 url 中的请求转换成 分页 的类
 * User: HHH.Y
 * Date: 2020-08-07
 */
@Getter
@Setter
@ToString
public class Page {
    private String searchText; // 条件查询的字段
    private String sortOrder; // 排序的条件
    private Integer pageSize; // 每一页的数量
    private Integer pageNumber; // 当前的页码

    // 将 url 中的请求转变为 Java 的实体类
    public static Page parse(HttpServletRequest req) {
        Page p = new Page();
        p.searchText = req.getParameter("searchText");
        p.sortOrder = req.getParameter("sortOrder");
        p.pageSize = Integer.parseInt(req.getParameter("pageSize"));
        p.pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        return p;
    }
}
