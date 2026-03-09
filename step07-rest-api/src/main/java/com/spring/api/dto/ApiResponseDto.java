package com.spring.api.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)		//null이면 JSON에서 제외
//공통의 양식으로 만들어주는 dto
public class ApiResponseDto<T> {
	
	private Integer statusCode;
	private HttpStatus httpStatus;	//string 아님 정해져 있는 값
	private String message;
	private T data;		//T는 타입변수 타입을 모를때
}
