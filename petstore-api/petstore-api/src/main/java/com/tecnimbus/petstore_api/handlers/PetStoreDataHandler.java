package com.tecnimbus.petstore_api.handlers;

import com.tecnimbus.petstore_api.rest.Model.Pet;
import com.tecnimbus.petstore_api.rest.external.PetStoreRemoteCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PetStoreDataHandler {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected PetStoreRemoteCalls petStoreRemoteCalls;

    @Value("${app.data-mode}")
    private String dataMode;

    @Value("${external.pet-store.url}")
    private String remotePetStore;

    public ResponseEntity<Pet> findPetById(Long petId){
        if(dataMode.equals("Remote")){
            ResponseEntity<Pet> response
                    = restTemplate.getForEntity(remotePetStore + "/pet/" + petId, Pet.class);
            return response;
        }
        return null; // TODO : need to handle with Local data
    }
}
