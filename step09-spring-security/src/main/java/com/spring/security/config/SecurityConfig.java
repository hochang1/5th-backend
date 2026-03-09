package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//2)
// 또다른 인가방법
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	// 필터 체인
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable());
		

		//3. 로그인 인증(security에서 해준다)  
		
		http
			.formLogin(form -> 
								form
									.loginPage("/login")
									.loginProcessingUrl("/login")		//가로채기
									.defaultSuccessUrl("/"));		//성공하고나면 어디로 이동시킬건가
		
		http
			.logout(logout ->
								logout
									.logoutUrl("/logout")
									.logoutSuccessUrl("/")
									.invalidateHttpSession(true) 		//세션 무효화
									.deleteCookies("JSESSIONID") 		//쿠키
									.clearAuthentication(true)		//security context 초기화
									);
		// url기반으로 인가작업
		http
			.authorizeHttpRequests(authorize -> 
												authorize
													.requestMatchers("/user/**").hasAnyRole("USER", "MANAGER", "ADMIN")
													.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
													.requestMatchers("/admin/**").hasAnyRole("ADMIN")
													.anyRequest().permitAll()
					);
		
		
		
		return http.build();
	}
    
    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}