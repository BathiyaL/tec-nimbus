package com.tecnimbus.petstore_api.service.store;

import com.tecnimbus.petstore_api.model.external.ExternalInventoryDTO;
import com.tecnimbus.petstore_api.service.external.PetStoreExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteStoreService {
    @Autowired
    PetStoreExternalService petStoreExternalService;

    public ExternalInventoryDTO getPetInventory() {
        return petStoreExternalService.getPetInventory();
    }
}