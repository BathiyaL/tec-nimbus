package com.tecnimbus.petstore_api;

import com.tecnimbus.petstore_api.handlers.PetStoreDataHandler;
import com.tecnimbus.petstore_api.rest.external.PetStoreRemoteCalls;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PetstoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetstoreApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public PetStoreRemoteCalls petStoreRemoteCalls() {
		return new PetStoreRemoteCalls();
	}

	@Bean
	public PetStoreDataHandler petStoreDataHandler() {
		return new PetStoreDataHandler();
	}
}
