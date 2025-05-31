package com.tecnimbus.petstore_api.service.pet;

import com.tecnimbus.petstore_api.Model.Pet;
import com.tecnimbus.petstore_api.service.BaseService;
import com.tecnimbus.petstore_api.service.external.PetStoreExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PetService extends BaseService {

    @Autowired
    PetStoreExternalService petStoreExternalService;

    public ResponseEntity<Pet> findPetById(Long petId) {
        if (dataMode.equals("Remote")) {
            return petStoreExternalService.findPetById(petId);
        }
        return null;
    }
}