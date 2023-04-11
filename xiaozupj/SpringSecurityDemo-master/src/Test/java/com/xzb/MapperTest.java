package com.xzb;

import com.xzb.domain.User;
import com.xzb.mapper.MenuMapper;
import com.xzb.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testPasswordEncoder(){
        System.out.println(encoder.encode("1234"));
        System.out.println(encoder.encode("123456"));
        System.out.println(encoder.matches("1234",
                "$2a$10$52ogufdPD7jH/9Co218INugLzfd5pewbBllkaRrASBOu0lDMvVhLS"));
        System.out.println(encoder.matches("1234",
                "$2a$10$lPRVUCikKSTiJBIGubsmruaeFFu1/tE2nzrWZ4pbU/cZvjj8m/Yp."));
    }

    @Test
    public void testSelectPermsByUserId(){
        System.out.println(menuMapper.selectPermsByUserId(1L));
    }
}