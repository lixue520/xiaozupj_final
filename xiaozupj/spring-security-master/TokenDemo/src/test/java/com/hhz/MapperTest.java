package com.hhz;

import com.hhz.domain.User;
import com.hhz.mapper.MenuMapper;
import com.hhz.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class MapperTest {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void testBCryptPasswordEncoder() {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encode1 = passwordEncoder.encode("1234");
//        String encode2 = passwordEncoder.encode("1234");
//        System.out.println(encode1);
//        System.out.println(encode2);

        boolean matches = passwordEncoder.matches("1234", "$2a$10$e4hPHDvzv2h4v.XZRcTt9.75wIfUczIjUWvobSpnoHV/5VIdWUV9e");
        System.out.println(matches);
    }

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testSelectPermsByUserId() {
        List<String> list = menuMapper.selectPermsByUserId(2L);
        System.out.println(list);
    }
}
