package com.spring.api.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestInterceptor1 implements HandlerInterceptor{
	
	public TestInterceptor1() {
//		log.info("TestInterceptor1 =============> 생성자");
		System.out.println("TestInterceptor1 =============> 생성자");
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler) throws Exception {
		
//		log.info("TestInterceptor1 =============> pre-handle");
		System.out.println("TestInterceptor1 =============> pre-handle");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler,
							@Nullable ModelAndView modelAndView) throws Exception {
		
//		log.info("TestInterceptor1 =============> post-handle");
		System.out.println("TestInterceptor1 =============> post-handle");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler,
								@Nullable Exception ex) throws Exception {
		
//		log.info("TestInterceptor1 =============> after-completion");
		System.out.println("TestInterceptor1 =============> after-completion");
	}
}