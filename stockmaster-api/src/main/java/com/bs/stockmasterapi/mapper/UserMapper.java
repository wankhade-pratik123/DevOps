package com.bs.stockmasterapi.mapper;

import org.mapstruct.Mapper;

import com.bs.stockmasterapi.dto.UserDto;
import com.bs.stockmasterapi.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto mapToUserDto(User user);
}
