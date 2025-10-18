package com.tecnimbus.petstore_api.service.store;

import com.tecnimbus.petstore_api.model.external.ExternalInventoryDTO;
import com.tecnimbus.petstore_api.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceRouter extends BaseService {
    @Autowired
    RemoteStoreService remoteStoreService;

    public ExternalInventoryDTO getPetInventory() {
        return remoteStoreService.getPetInventory();
    }
}
