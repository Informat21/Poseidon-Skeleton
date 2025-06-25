package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setFullname("Test User");
        user.setRole("USER");
    }

    @Test
    public void testFindAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> result = userService.findAll();

        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        when(userRepository.save(user)).thenReturn(user);

        User saved = userService.save(user);

        assertNotNull(saved);
        assertEquals("Test User", saved.getFullname());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testFindById_Existing() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1);

        assertTrue(result.isPresent());
        assertEquals("USER", result.get().getRole());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    public void testFindById_NotFound() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(999);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(999);
    }

    @Test
    public void testDeleteById() {
        userService.deleteById(1);

        verify(userRepository, times(1)).deleteById(1);
    }
}
