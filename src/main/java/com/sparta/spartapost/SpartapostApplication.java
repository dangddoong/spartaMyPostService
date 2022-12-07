package com.sparta.spartapost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpartapostApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpartapostApplication.class, args);
	}

}
