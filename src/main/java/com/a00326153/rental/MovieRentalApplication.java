package com.a00326153.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MovieRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRentalApplication.class, args);
	}

}
