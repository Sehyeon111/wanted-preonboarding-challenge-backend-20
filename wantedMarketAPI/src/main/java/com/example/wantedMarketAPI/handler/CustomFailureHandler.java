package com.example.wantedMarketAPI.handler;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errMessage = "";
		if(exception instanceof BadCredentialsException) {
			errMessage = exception.getMessage() + "\n아이디나 비밀번호가 잘못되었습니다.";
		} else {
			errMessage = exception.getMessage() + "\n알 수 없는 오류가 발생했습니다.";
		}
		
		errMessage = URLEncoder.encode(errMessage, "UTF-8");
		setDefaultFailureUrl("");
		
		super.onAuthenticationFailure(request, response, exception);
	}
}
