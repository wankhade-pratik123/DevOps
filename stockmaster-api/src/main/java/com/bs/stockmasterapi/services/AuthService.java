package com.bs.stockmasterapi.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.stockmasterapi.dto.AuthenticationResponse;
import com.bs.stockmasterapi.dto.LoginRequest;
import com.bs.stockmasterapi.dto.RefreshTokenRequest;
import com.bs.stockmasterapi.dto.RegisterRequest;
import com.bs.stockmasterapi.exceptions.StockMasterException;
import com.bs.stockmasterapi.model.NotificationEmail;
import com.bs.stockmasterapi.model.User;
import com.bs.stockmasterapi.model.VerificationToken;
import com.bs.stockmasterapi.repositories.UserRepository;
import com.bs.stockmasterapi.repositories.VarificationTokenRepository;
import com.bs.stockmasterapi.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VarificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final RefreshTokenService refreshTokenService;

	@Transactional
	public void signup(RegisterRequest registerRequest) {

		User user = new User();
		user.setName(registerRequest.getName());
		user.setMobile(registerRequest.getMobile());
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setCreated(Instant.now());
		user.setEnabled(false);

		userRepository.save(user);

		String token = generateVerificationToken(user);

		mailService.sendMail(new NotificationEmail("Please Activate your Account", user.getEmail(),
				"Thank you for signing up to Spring Reddit, "
						+ "please click on the below url to activate your account : "
						+ "http://localhost:8080/api/auth/accountVerification/" + token));

	}

	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);

		verificationTokenRepository.save(verificationToken);
		return token;
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		if (verificationToken.isPresent()) {
			fetchAndEnableUser(verificationToken.get());
		} else {
			throw new StockMasterException("Invalid Toke");
		}
	}

	public void fetchAndEnableUser(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new StockMasterException("User not found with name " + username));
		user.setEnabled(true);
		userRepository.save(user);
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwttoken = jwtProvider.generateToken(authentication);
		return AuthenticationResponse.builder().authenticationToken(jwttoken)
				.refreshToken(refreshTokenService.generateRefreshToken().getToken())
				.expireAt(Instant.now().plusMillis(jwtProvider.getExpirationTime()))
				.username(loginRequest.getUsername()).build();
	}

	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		String jwtToken = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());
		return AuthenticationResponse.builder().authenticationToken(jwtToken)
				.refreshToken(refreshTokenRequest.getRefreshToken())
				.expireAt(Instant.now().plusMillis(jwtProvider.getExpirationTime()))
				.username(refreshTokenRequest.getUsername()).build();
	}
}
