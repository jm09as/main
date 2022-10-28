package com.schedule_management.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        var cal = SpringApplication.run(MainApplication.class, args);
//		Arrays.stream(cal.getBeanDefinitionNames()).sorted().forEach(System.out::println);
    }

//    @Bean
//    public DataSource dataSource() {
//        return new DataSource().createConnectionBuilder().password("12345").user("root").build();
//        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
//    }

}
