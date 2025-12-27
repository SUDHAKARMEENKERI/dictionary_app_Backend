package com.anitechie.dictionaryApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
@ComponentScan(basePackages = "service")
@ComponentScan(basePackages = "errorHandle")
@EnableJpaRepositories(basePackages = "dao")
@EntityScan(basePackages = "model")
@EnableCaching
public class DictionaryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DictionaryAppApplication.class, args);
	}

}
