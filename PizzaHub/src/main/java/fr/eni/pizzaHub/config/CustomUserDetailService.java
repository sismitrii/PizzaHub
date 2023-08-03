//package fr.eni.pizzaHub.config;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.stereotype.Service;
//
//import fr.eni.pizzaHub.bo.Employee;
//import fr.eni.pizzaHub.bo.Role;
//import fr.eni.pizzaHub.dal.EmployeeRepository;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService{
//	@Autowired
//	private EmployeeRepository employeeRepository;
//
//	
//	public CustomUserDetailService(EmployeeRepository employeeRepository, JwtEncoder jwtEncoder) {
//		this.employeeRepository = employeeRepository;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		final Employee employee = employeeRepository.findByUsername(username);
//		if (employee == null) {
//			throw new UsernameNotFoundException(username);
//		}
//		UserDetails user = new User(employee.getPuid(), employee.getPassword()/*encoder*/, getAuthorities(employee.getRole()));
//				//User.withUsername(employee.getPuid()).password("{noop}" + employee.getPassword()).authorities("USER").build();
//		return user;
//	}
//	
//	public Collection<? extends GrantedAuthority> getAuthorities(Role role) {
//		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
//		return authList;
//	}
//	
//	public List<String> getRoles(Role role) {
//
//		List<String> roles = new ArrayList<String>();
//
//		if (role == Role.WAITER) {
//			roles.add("ROLE_WAITER");
//		} else if (role == Role.COOK) {
//			roles.add("ROLE_COOK");
//		} else if (role == Role.MANAGER) {
//			roles.add("ROLE_MANAGER");
//		}
//		
//		return roles;
//	}
//	
//	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		
//		for (String role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role));
//		}
//		return authorities;
//	}
//}
