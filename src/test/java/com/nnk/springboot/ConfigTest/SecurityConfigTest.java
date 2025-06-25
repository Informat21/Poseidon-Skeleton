package com.nnk.springboot.ConfigTest;

import com.nnk.springboot.config.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;

public class SecurityConfigTest {

    private SecurityConfig securityConfig;

    @Before
    public void setUp() {
        securityConfig = new SecurityConfig();
    }

    @Test
    public void testPasswordEncoderBean() {
        PasswordEncoder encoder = securityConfig.passwordEncoder();
        assertNotNull(encoder);
        String rawPassword = "mypassword";
        String encodedPassword = encoder.encode(rawPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }
}
