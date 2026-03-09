package com.spring.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FilterController {
	
	@GetMapping("/filter")
	@ResponseBody
	public String filterTest() {
		
		System.out.println("FilterController");
		
		return "filter";
	}
	
}