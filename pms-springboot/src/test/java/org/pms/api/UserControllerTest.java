package org.pms.api;


import org.ike.pms.api.entity.User;
import org.ike.pms.api.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserControllerTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void addUser() {
        User user = new User();
        user.setUserName("ancey");
        user.setPassword("123");
        user.setPhone("18200000000");
        int count = userMapper.insert(user);
        System.out.println(count);
    }
}
