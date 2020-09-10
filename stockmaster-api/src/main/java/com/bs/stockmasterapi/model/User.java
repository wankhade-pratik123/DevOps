package com.bs.stockmasterapi.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long userId;
	private String name;
	private String mobile;
	private String email;
	private String username;
	private String password;
    private Instant created;
    private boolean enabled; 
}
