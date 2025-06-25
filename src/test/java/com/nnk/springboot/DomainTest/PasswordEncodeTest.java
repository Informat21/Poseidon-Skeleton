package com.nnk.springboot.DomainTest;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertTrue;

public class PasswordEncodeTest {

    @Test
    public void testPasswordEncoding() {
        // GIVEN
        String rawPassword = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // WHEN
        String encodedPassword = encoder.encode(rawPassword);

        // THEN
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }
}



//package com.nnk.springboot;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * Created by Khang Nguyen.
// * Email: khang.nguyen@banvien.com
// * Date: 09/03/2019
// * Time: 11:26 AM
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PasswordEncodeTest {
//    @Test
//    public void testPassword() {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String pw = encoder.encode("123456");
//        System.out.println("[ "+ pw + " ]");
//    }
//}
