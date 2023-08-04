package fr.eni.pizzaHub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.pizzaHub.bll.TokenService;
import fr.eni.pizzaHub.dto.AuthToken;
import fr.eni.pizzaHub.dto.ConnectionRequestDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final TokenService tokenService;
	private final AuthenticationManager authenticationManager;
	
	public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
	}

	@RequestMapping("/token")
	public AuthToken token(@RequestBody ConnectionRequestDto request) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		return new AuthToken(tokenService.generateToken(authentication));
		//return new ResponseEntity<String>(, HttpStatus.OK);
	}
	
	@RequestMapping("/checkToken")
	public void checkToken() {
		
	}
}
