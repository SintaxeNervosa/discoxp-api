package com.github.sintaxenervosa.discoxp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class DiscoxpApplication {

	private static final Logger logger = LoggerFactory.getLogger(DiscoxpApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DiscoxpApplication.class, args);
		logger.info("\nBackend rodando com exito");
	}

}
