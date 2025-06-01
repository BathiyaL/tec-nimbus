package com.tecnimbus.petstore_api;

import com.tecnimbus.petstore_api.external.PetStoreRemoteCalls;
import com.tecnimbus.petstore_api.service.external.PetStoreExternalService;
import com.tecnimbus.petstore_api.service.pet.PetService;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;


@SpringBootApplication
public class PetStoreApiApplication {

    public static void main(String[] args) throws SQLException {

        // Starts H2 server to allow external clients like DBeaver
        Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-tcpPort", "9092",
                "-ifNotExists"
        ).start();

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
