package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("securepassword");
        user.setFullname("Test User");
        user.setRole("ADMIN");
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        // GIVEN
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        // WHEN
        UserDetails userDetails = userDetailsService.loadUserByUsername("testuser");

        // THEN
        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("securepassword", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN")));

        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername_UserNotFound() {
        // GIVEN
        when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        // WHEN
        userDetailsService.loadUserByUsername("unknown");

        // THEN (exception is expected)
    }
}
