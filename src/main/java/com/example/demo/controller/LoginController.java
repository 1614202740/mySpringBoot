package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @RequestMapping(value = "admit")
    public String toAdmit(){
        return "admit";
    }

    @RequestMapping(value = "user")
    public ModelAndView toUser(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            model.addAttribute("uid",((UserDetails) principal).getUsername());
        return new ModelAndView("user","userModel",model);
    }

    @RequestMapping(value = "view")
    public ModelAndView toHome(Model model){
        return new ModelAndView("view","userModel",model);
    }
}
