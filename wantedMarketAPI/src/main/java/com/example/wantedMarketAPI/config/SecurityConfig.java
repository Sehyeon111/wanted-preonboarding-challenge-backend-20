package com.example.wantedMarketAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.wantedMarketAPI.handler.CustomFailureHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomFailureHandler customFailureHandler;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests((auth) -> auth.requestMatchers(
					"/"
					).permitAll()
					.anyRequest().authenticated());
		
	// Login 설정
		http
		.formLogin((auth) -> auth
				.loginPage("/user/login")
				.failureHandler(customFailureHandler)
				.usernameParameter("userId")
				.passwordParameter("userPw")
				.loginProcessingUrl("/user/loginProc"));
		
	// Logout 설정
		http
		.logout((auth) -> auth
				.logoutUrl("/user/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID"));
		
		// csrf : 사이트간 요청 위조
		http
		.csrf((auth) -> auth.disable());
		
		return http.build();
	};
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
