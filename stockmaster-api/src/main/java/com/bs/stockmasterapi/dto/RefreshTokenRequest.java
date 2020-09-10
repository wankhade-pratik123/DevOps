package com.bs.stockmasterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RefreshTokenRequest {

	@NotBlank
	private String refreshToken;
	private String username;
}
