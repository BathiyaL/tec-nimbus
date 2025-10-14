package com.tecnimbus.petstore_api.controller.store;

import com.tecnimbus.petstore_api.controller.BaseController;
import com.tecnimbus.petstore_api.service.store.StoreServiceRouter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController extends BaseController {

    @Autowired
    StoreServiceRouter storeServiceRouter;

    @Operation(summary = "Find pet by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returns pet inventories by status")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/store/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPetInventory() {
        return storeServiceRouter.getPetInventory();
    }
}
