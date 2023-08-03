package fr.eni.pizzaHub.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Value("classpath:certs/private.pem")
	private RSAPrivateKey privateKey;
	
	@Value("classpath:certs/public.pem")
	private RSAPublicKey publicKey;
	//private final RsaKeyProperties rsaKeys;

//	public SecurityConfig(RsaKeyProperties rsaKeys) {
//	this.rsaKeys = rsaKeys;
//}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
					.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(auth -> auth
							.requestMatchers("/menuItem/**").permitAll()
							.anyRequest().authenticated()
					)
					.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.httpBasic(Customizer.withDefaults())
					.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager users() {
		return new InMemoryUserDetailsManager(
				User
				.withUsername("waiter")
				.password("{noop}password")
				.authorities("read") // => a explorer
				.build()
				);
	}
	
	@Bean
	public JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<SecurityContext>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(publicKey).build();

	}
	
	
}
