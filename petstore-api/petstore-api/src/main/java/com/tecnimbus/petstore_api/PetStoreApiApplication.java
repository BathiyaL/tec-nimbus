package com.tecnimbus.petstore_api;

import com.tecnimbus.petstore_api.external.PetStoreRemoteCalls;
import com.tecnimbus.petstore_api.service.external.PetStoreExternalService;
import com.tecnimbus.petstore_api.service.pet.PetService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PetStoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetStoreApiApplication.class, args);
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
    public PetService petService() {
        return new PetService();
    }

    @Bean
    public PetStoreExternalService petStoreExternalService() {
        return new PetStoreExternalService();
    }
}
