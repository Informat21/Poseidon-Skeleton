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
