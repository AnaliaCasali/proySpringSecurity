package com.Alkemy.JavaMod21;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaMod21Application {

	public static void main(String[] args) {

		SpringApplication.run(JavaMod21Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx){
		return  args -> {
			System.out.println( "inicio la aplicación");
		};
	}

}
