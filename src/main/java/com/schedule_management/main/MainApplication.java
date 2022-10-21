package com.schedule_management.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        var cal = SpringApplication.run(MainApplication.class, args);
//		Arrays.stream(cal.getBeanDefinitionNames()).sorted().forEach(System.out::println);
    }

}
