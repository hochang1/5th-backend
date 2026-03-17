package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MVCController {
	
	//@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public void test1()	{
		System.out.println("Test1");
	}
	
	//@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public void test1(HttpServletRequest request) {
		String sid = request.getParameter("sid");
	}
	
	
}