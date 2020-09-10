package com.bs.stockmasterapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.stockmasterapi.dto.UserDto;
import com.bs.stockmasterapi.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Api(value = "User Rest Endpoint")
public class UserController {

	private final UserService userServie;
 
	@ApiOperation(value = "Returns list of all users")
	@GetMapping("/getUsers")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userServie.getAllUsers());
	}
	
	@ApiOperation(value = "Returns user information with respected userid")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUsers(@PathVariable long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userServie.getUser(userId));
	}
	
	@ApiOperation(value = "Delete user information  with respected userid")
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUsers(@PathVariable long userId) {
		userServie.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
	}
}
