package com.schedule_management.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext cal = SpringApplication.run(MainApplication.class, args);
		Arrays.stream(cal.getBeanDefinitionNames()).sorted().forEach(System.out::println);
	}

}
