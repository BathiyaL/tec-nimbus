package com.tecnimbus.petstore_api.rest;

import com.tecnimbus.petstore_api.rest.Model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/pet/{petId}")
public class PetController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.pet-store.url}")
    private String remotePetStore;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet>  findPetById(){
        ResponseEntity<Pet> response
                = restTemplate.getForEntity(remotePetStore + "/pet/1", Pet.class);
        return response;

    }
}
