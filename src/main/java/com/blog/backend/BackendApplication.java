package com.blog.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
//		SpringApplication.run(BackendApplication.class, args);
		SpringApplication application = new SpringApplication(BackendApplication.class);
		application.setDefaultProperties(Collections.singletonMap("server.port","9999"));
		application.run(args);
	}

}
