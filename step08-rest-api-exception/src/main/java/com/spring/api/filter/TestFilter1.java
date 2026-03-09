package com.spring.api.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFilter1 implements Filter{
	
	public TestFilter1() {
//		log.info("TestFilter1 ==============> 생성자");
		System.out.println("TestFilter1 ==============> 생성자");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		log.info("TestFilter1 ==============> pre-handle");
		System.out.println("TestFilter1 ==============> pre-handle");
		
		chain.doFilter(request, response);
		
//		log.info("TestFilter1 ==============> post-handle");
		System.out.println("TestFilter1 ==============> post-handle");
	}
	
}