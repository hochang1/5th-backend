package board.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import board.dto.security.BoardDetails;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
	@Bean
	AuditorAware<String> auditorAware() {
//		return () -> Optional.of("admin");
		
		return () -> Optional.ofNullable(SecurityContextHolder.getContext())
								.map(SecurityContext::getAuthentication)
								.filter(Authentication::isAuthenticated)
								.map(Authentication::getPrincipal)
								.filter(principal -> !principal.equals("anonymouseUser"))
								.map(principal -> {
									if(principal instanceof BoardDetails boardDetails) {
										return boardDetails.getUid();
									} 
									return null; // anonymouseUser( 인증되지 않는 사용자정보) 리턴
								});
	}
}