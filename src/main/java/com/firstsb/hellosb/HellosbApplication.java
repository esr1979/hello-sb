package com.firstsb.hellosb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan
@SpringBootApplication
public class HellosbApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellosbApplication.class, args);
	}



}
