package com.tecnimbus.petstore_api.external;

import com.tecnimbus.petstore_api.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PetStoreRemoteCalls {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.pet-store.url}")
    private String remotePetStore;

    public ResponseEntity<Pet> findPetById(Long petId){
        ResponseEntity<Pet> response
                = restTemplate.getForEntity(remotePetStore + "/pet/" + petId, Pet.class);
        return response;
    }
}