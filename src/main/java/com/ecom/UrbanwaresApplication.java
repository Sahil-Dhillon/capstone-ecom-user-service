package com.ecom;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@EnableFeignClients
@SpringBootApplication
public class UrbanwaresApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanwaresApplication.class, args);
		System.out.println("Hello world");
	}

}
