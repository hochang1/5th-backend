package com.spring.di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.di", "com.spring.loc"})	//외부 패키지 컴포넌트 가져올때
public class Step01DiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Step01DiApplication.class, args);
	}

}
