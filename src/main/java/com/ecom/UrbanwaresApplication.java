package com.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UrbanwaresApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanwaresApplication.class, args);
		System.out.println("Hello world");
	}

}
