package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    private UserController userController;

    @Before
    public void setUp() {

      userController= new UserController(userService);
    }

    @Test
    public void testHome() {
        when(userService.findAll()).thenReturn(Arrays.asList(new User()));

        String result = userController.home(model);

        verify(userService).findAll();
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
        user.setPassword("Admin1234!");
        when(bindingResult.hasErrors()).thenReturn(false);
        lenient().when(userService.findAll()).thenReturn(Arrays.asList(user));

        String result = userController.validate(user, bindingResult, model);

        verify(userService).save(any(User.class));
        assertEquals("redirect:/user/list", result);
    }

    @Test
    public void testValidate_Invalid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = userController.validate(new User(), bindingResult, model);

        verify(userService, never()).save(any());
        assertEquals("user/add", result);
    }

    @Test
    public void testShowUpdateForm_ValidId() {
        User user = new User();
        user.setPassword("secret");
        when(userService.findById(1)).thenReturn(Optional.of(user));

        String result = userController.showUpdateForm(1, model);

        verify(model).addAttribute("user", user);
        assertEquals("user/update", result);
        assertEquals("", user.getPassword()); // password should be reset
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShowUpdateForm_InvalidId() {
        when(userService.findById(999)).thenReturn(Optional.empty());

        userController.showUpdateForm(999, model);
    }

    @Test
    public void testUpdateUser_Valid() {
        User user = new User();
        user.setPassword("updatedPassword");
        when(bindingResult.hasErrors()).thenReturn(false);
        lenient().when(userService.findAll()).thenReturn(Arrays.asList(user));

        String result = userController.updateUser(1, user, bindingResult);

        verify(userService).save(any(User.class));
        assertEquals("redirect:/user/list", result);
    }

    @Test
    public void testUpdateUser_Invalid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = userController.updateUser(1, new User(), bindingResult);

        verify(userService, never()).save(any());
        assertEquals("user/update", result);
    }

    @Test
    public void testDeleteUser_ValidId() {
        User user = new User();
        when(userService.findById(1)).thenReturn(Optional.of(user));
        lenient().when(userService.findAll()).thenReturn(Arrays.asList());

        String result = userController.deleteUser(1);

        verify(userService).deleteById(1);
        assertEquals("redirect:/user/list", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUser_InvalidId() {
        when(userService.findById(999)).thenReturn(Optional.empty());

        userController.deleteUser(999);
    }
}
