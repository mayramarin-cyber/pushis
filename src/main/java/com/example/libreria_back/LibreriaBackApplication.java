package com.example.libreria_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.libreria_back"})
public class LibreriaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaBackApplication.class, args);
	}

}
