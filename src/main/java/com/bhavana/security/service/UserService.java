package com.bhavana.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bhavana.security.model.User;
import com.bhavana.security.model.UserPrincipal;
import com.bhavana.security.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

	
	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user) ;		
	}


	public String login(User user) {
		
		UserDetails userDetails = new UserPrincipal(repo.findByUsername(user.getUsername()));
		Authentication authentication = 
				authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		return authentication.isAuthenticated()? jwtService.generateToken(userDetails) : "Failure";
	}
}
