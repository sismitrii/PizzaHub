package fr.eni.pizzaHub.controller;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.pizzaHub.bll.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final TokenService tokenService;
	
	public AuthController(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@RequestMapping("/token")
	public String token(Authentication authentication) {
		return tokenService.generateToken(authentication);
	}
	
	@RequestMapping("/checkToken")
	public void checkToken() {
		
	}
}
