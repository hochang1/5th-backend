package com.spring.token.config;

import java.nio.file.AccessDeniedException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.spring.token.config.jwt.JwtAuthenticationFilter;
import com.spring.token.config.jwt.JwtAuthorizationFilter;
import com.spring.token.service.TokenRedisService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor		// 2.
@Configuration
@EnableWebSecurity	//2. 내가 지정한 객체들을 필터에
public class SecurityConfig {
	
	private final CorsConfig corsConfig;		// 2.
	private final TokenRedisService tokenRedisService;		//redis 4.
	
	// 1.인증객체 주입
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	//10. 403 -> rediredct 방식 /access-denied(PageController에  /** 접근 거부 페이지 */)
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return (request, response, AccessDeniedException) -> {
			String requestURI = request.getRequestURI();
			if(requestURI.startsWith("/api")) {
				response.setStatus(403);
				response.setContentType("application/json;charset=UTF-8");
	            response.getWriter().write("{\"message\":\"접근 권한이 없음\"}");
			} else {
				response.sendRedirect("access-denied");
			}
		};
	}
	
	// 필터 체인
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
    												AuthenticationManager authenticationManager) throws Exception {
		
		// 2.필터등록
    	http
    		.addFilter(corsConfig.corsFilter())
    		//redis 4. , redis 7-3
    		.addFilter(new JwtAuthenticationFilter(authenticationManager, tokenRedisService))		
    		.addFilter(new JwtAuthorizationFilter(authenticationManager, tokenRedisService));		//9. 필터등록->JwtAuthorizationFilter
    	
    	
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session
												.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(AbstractHttpConfigurer::disable)		//사용하지 않겠다
			.httpBasic(AbstractHttpConfigurer::disable);	//사용하지 않겠다.
		
		//10. 실습흐름의 SecurityConfig 인가 체크 부분( 인가 실패 -> 헨들러 등록) --부가적인 내용입니다
		http
			.exceptionHandling(ex -> ex
					.accessDeniedHandler(accessDeniedHandler())
					.authenticationEntryPoint((request, response, AccessDeniedException) -> {
						String requestURI = request.getRequestURI();
						if(requestURI.startsWith("/api")) {
							response.setStatus(403);
							response.setContentType("application/json;charset=UTF-8");
				            response.getWriter().write("{\"message\":\"로그인 필수\"}");
						} else {
							response.sendRedirect("/login");
						}
					})
					
					
					
			);
		
		
		//8.권한부여
		http
			.authorizeHttpRequests(authorize -> authorize
					
					// 공개 페이지
					.requestMatchers("/", "/index", "/signup", "/login", "/about", "/access-denied").permitAll()
					// 공개 API
					.requestMatchers("/api/v1/signup", "/api/v1/auth/**").permitAll()
					// 정적 리소스
					.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
					
					// USER 이상
					.requestMatchers("/user", "/api/v1/user/**").hasAnyRole("USER", "MANAGER", "ADMIN")
					// MANAGER 이상
					.requestMatchers("/manager", "/api/v1/manager/**").hasAnyRole("MANAGER", "ADMIN")
					// ADMIN 이상
					.requestMatchers("/admin", "/api/v1/admin/**").hasAnyRole("ADMIN")
					
					.anyRequest().authenticated()
					);
		
		
		return http.build();
	}
    
    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
