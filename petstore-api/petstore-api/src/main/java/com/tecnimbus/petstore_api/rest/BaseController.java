package com.tecnimbus.petstore_api.rest;

import com.tecnimbus.petstore_api.handlers.PetStoreDataHandler;
import com.tecnimbus.petstore_api.rest.external.PetStoreRemoteCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BaseController {

    @Autowired
    protected PetStoreDataHandler petStoreDataHandler;

    @Value("${external.pet-store.url}")
    private String remotePetStore;
}
