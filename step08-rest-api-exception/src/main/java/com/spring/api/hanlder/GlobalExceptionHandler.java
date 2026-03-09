package com.spring.api.hanlder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.api.dto.ErrorResponse;

@ControllerAdvice		// controller 많을때 한꺼번에 예외처리
public class GlobalExceptionHandler {

	@ExceptionHandler({IllegalArgumentException.class})
	public String handleIllegalArgument(IllegalArgumentException e, Model model) {
		model.addAttribute("exception", e.getMessage());
		return "error";
	}
	
	/*
	 * 예외 발생
	 * 	-> try ~ catch
	 * 	-> 컨트롤러의 @ExceptionHanlder
	 * 	-> Advice의 @ExceptionHanlder
	 * 	-> Spring 기본 예외 처리(BasicErrorController)
	 */
}
