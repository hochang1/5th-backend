package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Controller
public class CookieCotroller {

	
	@GetMapping("/coolie-test")
	public String cookieTest(HttpServletResponse response) {
		
		// 쿠키생성
		Cookie c1 = new Cookie("cookie-id", "cookie-value");
		c1.setMaxAge(60 * 60);
		response.addCookie(c1);
		
		return "cookie";
	}
	
	@GetMapping("/coolie-check")
	public String cookiCheck(HttpServletRequest request) {
		
		// 쿠키 확인(클라이언트 -> 서버 - 자동으로 요청 객체에포함)
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName());
			System.out.println(cookie.getValue());
			System.out.println();
		}
		
		return "cookie";
	}
	
	
}
