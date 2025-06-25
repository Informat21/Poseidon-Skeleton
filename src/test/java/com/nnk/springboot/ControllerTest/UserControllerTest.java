package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHome() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User()));

        String result = userController.home(model);

        verify(userRepository).findAll();
        verify(model).addAttribute(eq("users"), any());
        assertEquals("user/list", result);
    }

    @Test
    public void testAddUser() {
        String result = userController.addUser(new User());

        assertEquals("user/add", result);
    }

    @Test
    public void testValidate_Valid() {
        User user = new User();
        user.setPassword("1234");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        String result = userController.validate(user, bindingResult, model);

        verify(userRepository).save(any(User.class));
        verify(model).addAttribute(eq("users"), any());
        assertEquals("redirect:/user/list", result);
    }

    @Test
    public void testValidate_Invalid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = userController.validate(new User(), bindingResult, model);

        verify(userRepository, never()).save(any());
        assertEquals("user/add", result);
    }

    @Test
    public void testShowUpdateForm_ValidId() {
        User user = new User();
        user.setPassword("secret");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        String result = userController.showUpdateForm(1, model);

        verify(model).addAttribute("user", user);
        assertEquals("user/update", result);
        assertEquals("", user.getPassword()); // password should be reset
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShowUpdateForm_InvalidId() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        userController.showUpdateForm(999, model);
    }

    @Test
    public void testUpdateUser_Valid() {
        User user = new User();
        user.setPassword("updatedPassword");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        String result = userController.updateUser(1, user, bindingResult, model);

        verify(userRepository).save(any(User.class));
        verify(model).addAttribute(eq("users"), any());
        assertEquals("redirect:/user/list", result);
    }

    @Test
    public void testUpdateUser_Invalid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = userController.updateUser(1, new User(), bindingResult, model);

        verify(userRepository, never()).save(any());
        assertEquals("user/update", result);
    }

    @Test
    public void testDeleteUser_ValidId() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        String result = userController.deleteUser(1, model);

        verify(userRepository).delete(user);
        verify(model).addAttribute(eq("users"), any());
        assertEquals("redirect:/user/list", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUser_InvalidId() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        userController.deleteUser(999, model);
    }
}
