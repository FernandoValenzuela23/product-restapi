package net.fva.product_restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.fva.product_restapi.entity.User;
import net.fva.product_restapi.repository.IUserRepository;
import net.fva.product_restapi.security.JwtUtil;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/signin")
	public String authenticationUser(@RequestBody User user)
	{
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
		);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		return jwtUtil.generateToken(userDetails.getUsername());
	}
	
	@PostMapping("/signup")
	public String registerUser(@RequestBody User user)
	{
		if(userRepository.existsByUsername(user.getUsername()))
		{
			return "Error: Username is already taken";
		}
		
		// Create new user's account
		User newUser = new User(
				null, 
				user.getUsername(), 
				encoder.encode(user.getPassword())
		);
		
		userRepository.save(newUser);
		
		return "User registered sucessfully";
	}

}

