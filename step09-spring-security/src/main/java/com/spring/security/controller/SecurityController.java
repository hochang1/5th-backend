package com.spring.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.security.dto.SignUpRequestDto;
import com.spring.security.entity.User;
import com.spring.security.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final UserService userService;

    @GetMapping({ "", "/" })
    public String moveIndexPage() {
        return "index";
    }

    @GetMapping("/user")
    public String moveUserPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String moveAdminPage() {
        return "admin";
    }

    @GetMapping("/manager")
    public String moveManagerPage() {
        return "manager";
    }
    //2
    @PreAuthorize("hasRole('USER')")	//@EnableMethodSecurity(prePostEnabled = true) config에서 먼저 지정해줘야됨	
    @GetMapping("/about")
    public String moveAboutPage() {
        return "about";
    }

    @GetMapping("/login")
    public String moveLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String moveSignupPage() {
        return "signup";
    }
	
	// 실제 회원가입 -> 회원가입 후 index page로 이동
    @PostMapping("/signup")		//1.html로부터 form형태로 user정보 받아와서 처리
    public String signup(SignUpRequestDto dto) {		//User user로 받아오면 권한관련으로 위험함 - 엔터티로 받지말고 dto로
    	userService.signUp(dto);
    	return "redirect:/login";
    }
    
}