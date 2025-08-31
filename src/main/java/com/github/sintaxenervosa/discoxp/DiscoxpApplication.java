package com.github.sintaxenervosa.discoxp;

import com.github.sintaxenervosa.discoxp.security.DotenvInitializer;
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
		SpringApplication app = new SpringApplication(DiscoxpApplication.class);
		app.addInitializers(new DotenvInitializer());
		app.run(args);
		logger.info("\nAPI rodando na porta 8080");
	}
}
