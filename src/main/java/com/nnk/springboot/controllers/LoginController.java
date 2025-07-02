package com.nnk.springboot.controllers;


import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class LoginController {


    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView("user/list");
        mav.addObject("users", userService.findAll());
        return mav;
    }

    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView("403");
        mav.addObject("errorMsg", "You are not authorized for the requested data.");
        return mav;
    }
}
