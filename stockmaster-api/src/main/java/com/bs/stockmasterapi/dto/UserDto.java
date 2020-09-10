package com.bs.stockmasterapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	@ApiModelProperty(notes = "Id of user")
	private Long userId;
	@ApiModelProperty(notes = "Name of user")
	private String name;
	@ApiModelProperty(notes = "Mobile of user")
	private String mobile;
	@ApiModelProperty(notes = "Email of user")
	private String email;
}
