package huyue.dao;

import huyue.model.User;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class UserDAOTest {

    @Test
    public void query() {
        User user = new User();
        user.setId(1);
        user.setNickname("风一样的男子");
        user.setEmail("123@qq.com");
        UserDAO.query(user);
    }
}