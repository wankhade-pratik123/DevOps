package com.bs.stockmasterapi.security;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bs.stockmasterapi.exceptions.StockMasterException;

import org.springframework.security.core.userdetails.User;

import static io.jsonwebtoken.Jwts.parser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {

	private KeyStore keyStore;
	@Value("${jwt.expiration.time}")
	private Long jwtExpirationInMillis;

	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder().setSubject(principal.getUsername()).signWith(getPrivateKey())
				.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis))).compact();
	}

	public String generateTokenWithUsername(String username) {
		return Jwts.builder().setSubject(username).signWith(getPrivateKey())
				.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis))).compact();
	}

	public boolean validateToken(String token) {
		try {
			parser().setSigningKey(getPublickey()).parseClaimsJws(token);
		} catch (Exception e) {
			throw new StockMasterException("Invalid json token");
		}
		return true;
	}

	public String getUsernamefromJwt(String token) {
		Claims claims = parser().setSigningKey(getPublickey()).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	@PostConstruct
	public void init() {
		try {
			keyStore = KeyStore.getInstance("JKS");
			InputStream resourceAsStreamtStream = getClass().getResourceAsStream("/stockmaster.jks");
			keyStore.load(resourceAsStreamtStream, "vinitpratik".toCharArray());
		} catch (Exception e) {
			throw new StockMasterException("exception while loading keystore");
		}
	}

	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("alias", "vinitpratik".toCharArray());
		} catch (Exception e) {
			throw new StockMasterException("exception while getting private key");
		}
	}

	private PublicKey getPublickey() {
		try {
			return keyStore.getCertificate("alias").getPublicKey();
		} catch (Exception e) {
			throw new StockMasterException("exception while getting public key");
		}
	}

	public long getExpirationTime() {
		return jwtExpirationInMillis;
	}
}
