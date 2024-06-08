package com.BadaBazaar.BadaBazaar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BadaBazaarApplication
{

	public static void main(String[] args) {
		SpringApplication.run(BadaBazaarApplication.class, args);
	}

}
