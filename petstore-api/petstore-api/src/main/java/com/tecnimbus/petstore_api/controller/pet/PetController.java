package com.tecnimbus.petstore_api.controller.pet;

import com.tecnimbus.petstore_api.Model.Pet;
import com.tecnimbus.petstore_api.controller.BaseController;
import com.tecnimbus.petstore_api.service.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetController extends BaseController {

    @Autowired
    PetService petService;

    @RequestMapping(method = RequestMethod.GET, value = "/pet/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> findPetById(@PathVariable Long petId) {
        return petService.findPetById(petId);
    }
}