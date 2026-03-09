package com.spring.di.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.spring.di.service.DIService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@Component
public class DIController {
	
	// v1 : 생성자 주입
//	private DIService diService; 
//	public DIController(DIService diService) {
//		System.out.println("DIController 생성자");
//		this.diService = diService;
//		System.out.println("Controller ===> Service : " + diService);
//	}
	
	// v2 : final은 초기값이 있어야해서 에러나므로 롬북을 이용한다 (@RequiredArgsConstructor)
//	private final DIService diService;	
	
	
	// v3 : 필드 주입 (@Autowired)
//	@Autowired
//	private DIService diService;
//	
//	@PostConstruct
//	public void init() {
//		System.out.println("@PostConstruct");
//		System.out.println("Controller ===> Service : " + diService);
//	}
	
	// v4 : 세터 주입
	private DIService diService;
	
	@Autowired
	public void setDIService(@Qualifier("specialDIService") DIService diService) {
		System.out.println("세터 주입");
		System.out.println("Controller ===> Service : " + diService);
		this.diService = diService;
	}
	
	
	
	// 기존방식
	// private DIService service = new DIService();

}
