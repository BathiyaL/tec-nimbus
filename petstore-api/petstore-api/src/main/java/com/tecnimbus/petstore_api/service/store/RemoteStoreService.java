package com.tecnimbus.petstore_api.service.store;

import org.springframework.stereotype.Service;

@Service
public class RemoteStoreService {

    public String getPetInventory() {
        return "{ \"dogs\": 20, \"cats\": 25, \"birds\": 10 }";
    }
}