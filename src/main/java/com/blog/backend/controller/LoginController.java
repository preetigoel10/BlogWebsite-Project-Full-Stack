package com.blog.backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping(path = "/checkUser", produces = "application/json")
    public String checkLogin(){
        return "\"login successful\"";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Logout servlet : "+authentication);

        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            request.getSession().invalidate();
        }

        return "\"logout successful\"";
    }
}
