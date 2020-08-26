package huyue.dao;

import huyue.model.Page;
import huyue.model.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentDAOTest {

    @Test
    public void testQuery() {
        Page page = new Page();
        page.setPageSize(7);
        page.setPageNumber(1);
        StudentDAO.query(page);
    }

    @Test
    public void testQueryById() {
        StudentDAO.queryById(6);
    }


    // 对插入学生信息做单元测试
    @Test
    public void testInsert() {
        Student student = new Student();
        student.setStudentName("小小的梦想");
        student.setStudentNo("s007");
        student.setIdCard("011111111");
        student.setClassesId(1);
        student.setStudentEmail("123@qq.com");

        StudentDAO.insert(student);
    }


    @Test
    public void testUpdate() {
        Student student = new Student();
        student.setStudentName("guozhuzhu");
        student.setStudentEmail("xihuanguyueyue@qq.com");
        student.setIdCard("20161017");
        student.setClassesId(2);
        student.setStudentNo("20161017");
        student.setId(1);
        StudentDAO.update(student);
    }

    // 对删除学生信息做单元测试
    @Test
    public void testDelete() {
        String[] ids = {"10"};
        StudentDAO.delete(ids);
    }
}