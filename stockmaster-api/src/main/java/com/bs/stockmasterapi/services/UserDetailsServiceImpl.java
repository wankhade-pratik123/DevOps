package com.bs.stockmasterapi.services;

import static java.util.Collections.singletonList;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.stockmasterapi.exceptions.StockMasterException;
import com.bs.stockmasterapi.model.User;
import com.bs.stockmasterapi.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new StockMasterException("User not found with name " + username));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, getAuthorities("USER"));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return singletonList(new SimpleGrantedAuthority(role));
	}

}
