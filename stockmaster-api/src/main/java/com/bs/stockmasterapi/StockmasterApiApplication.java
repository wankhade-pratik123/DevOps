package com.bs.stockmasterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StockmasterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockmasterApiApplication.class, args);
	}

}
