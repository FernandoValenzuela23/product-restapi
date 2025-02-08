package net.fva.product_restapi.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.expiration}")
	private String jwtExpirationMs;
	
	private SecretKey key;
	
	
	@PostConstruct
	public void init()
	{
		this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(String username)
	{
		// for 3.2.x versions
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000*60*24))
				.signWith(key)
				.compact();
	}
	
	public String getUsernameFromToken(String token)
	{
		return Jwts.parser()
				.verifyWith(key).build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public boolean validateJwtToken(String token)
	{
		try {
			Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token);
			return true;
		} catch (SecurityException e) {
			System.out.println("Invalid JWT signature: " + e.getMessage());
		}
		catch (MalformedJwtException e) {
			System.out.println("Invalid JWT token: " + e.getMessage());
		}
		catch (ExpiredJwtException e) {
			System.out.println("JWT token expired: " + e.getMessage());
		}
		catch (UnsupportedJwtException e) {
			System.out.println("JWT token is unsupported: " + e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.out.println("JWT claims is empty: " + e.getMessage());
		}
		
		return false;
		
	}
	

}

