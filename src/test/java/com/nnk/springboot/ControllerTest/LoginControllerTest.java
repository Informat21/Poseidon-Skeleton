package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    private LoginController loginController;

    @Before
    public void setUp() {
        loginController = new LoginController();
        loginController = injectUserRepository(loginController, userRepository);
//        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
// Définir un ViewResolver simulé pour éviter les erreurs de "circular path"
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp"); // ou .html si tu utilises Thymeleaf

        mockMvc = MockMvcBuilders
                .standaloneSetup(loginController)
                .setViewResolvers(viewResolver)
                .build();
    }

    // Méthode utilitaire pour injecter le mock manuellement car @Autowired n'est pas pris en compte par défaut
    private LoginController injectUserRepository(LoginController controller, UserRepository userRepository) {
        try {
            java.lang.reflect.Field field = LoginController.class.getDeclaredField("userRepository");
            field.setAccessible(true);
            field.set(controller, userRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return controller;
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testGetAllUserArticles() throws Exception {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/secure/article-details"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void testErrorPage() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("403"))
                .andExpect(model().attribute("errorMsg", "You are not authorized for the requested data."));
    }
}
