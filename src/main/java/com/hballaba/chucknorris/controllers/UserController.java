package com.hballaba.chucknorris.controllers;

import com.hballaba.chucknorris.dao.UserDAO;
import com.hballaba.chucknorris.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserController {
    private UserDAO userDAO;
    private Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "user/login";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult, Model model){
        System.out.println("Post registration");

        //проверка на ошибки валидации
        if(bindingResult.hasErrors()) {
            return "user/login";
        }
        logger.info("UserForm: " + userForm);
        if(userDAO.save(userForm)) {
            System.out.println("already exist");
            model.addAttribute("usernameError", "The user with username " + userForm.getUsername() + " already exists");
            return  "user/login";
        }

        return "redirect:/";
    }
}
