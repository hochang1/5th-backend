package com.spring.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jpa.service.DeptService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DeptController {
	
	private final DeptService deptService;
	
	@GetMapping(value = "/jpa-test")
	@ResponseBody		//json 형식으로 http에게 자동 변환해서 리턴
	public Object jpaTest() {
		Object result = null;
		
		result = deptService.jpaTest();
		
		return result;
	}
}
