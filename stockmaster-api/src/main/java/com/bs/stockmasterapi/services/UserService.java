package com.bs.stockmasterapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bs.stockmasterapi.dto.UserDto;
import com.bs.stockmasterapi.exceptions.StockMasterException;
import com.bs.stockmasterapi.mapper.UserMapper;
import com.bs.stockmasterapi.model.User;
import com.bs.stockmasterapi.model.VerificationToken;
import com.bs.stockmasterapi.repositories.UserRepository;
import com.bs.stockmasterapi.repositories.VarificationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final VarificationTokenRepository varificationTokenRepository;
	private final UserMapper userMapper;

	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(userMapper::mapToUserDto).collect(Collectors.toList());
	}

	public UserDto getUser(long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new StockMasterException("No user foud with Id" + userId));
		return userMapper.mapToUserDto(user);
	}

	public void deleteUser(long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new StockMasterException("No user foud with Id " + userId));
		
		
		VerificationToken varificationToken = varificationTokenRepository.findByUser(user)
				.orElseThrow(() -> new StockMasterException("Tokenr not foud for user " + user.getName()));
		
		varificationTokenRepository.delete(varificationToken);
		userRepository.delete(user);
	}
}
