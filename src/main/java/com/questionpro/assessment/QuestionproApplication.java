package com.questionpro.assessment;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import ch.qos.logback.classic.Logger;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableCaching
@EnableScheduling
public class QuestionproApplication {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(QuestionproApplication.class);
	
	public static void main(String[] args) {
		logger.info("Starting Application Meesage");
		SpringApplication.run(QuestionproApplication.class, args);
	}

}
