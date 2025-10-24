package com.tecnimbus.petstore_api.controller.store;

import com.tecnimbus.petstore_api.controller.BaseController;
import com.tecnimbus.petstore_api.model.external.ExternalInventoryDTO;
import com.tecnimbus.petstore_api.service.store.StoreServiceRouter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "store", description = "Operations related to the store")
public class StoreController extends BaseController {

    @Autowired
    StoreServiceRouter storeServiceRouter;

    @Operation(summary = "Find pet by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returns pet inventories by status")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/store/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExternalInventoryDTO getPetInventory() {
        return storeServiceRouter.getPetInventory();
    }
}
