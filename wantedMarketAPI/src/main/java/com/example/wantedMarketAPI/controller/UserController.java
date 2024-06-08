package com.example.wantedMarketAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.wantedMarketAPI.dto.PreUserDTO;
import com.example.wantedMarketAPI.service.PreUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	public final PreUserService preUserService;
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/joinProc")
	public String joinProc(@ModelAttribute PreUserDTO preUserDTO) {
		
		preUserService.insertUser(preUserDTO);
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
