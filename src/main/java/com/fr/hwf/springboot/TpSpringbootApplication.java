package com.fr.hwf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TpSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpSpringbootApplication.class, args);
	}

}
