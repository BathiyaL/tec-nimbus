package com.tecnimbus.petstore_api.rest;

import com.tecnimbus.petstore_api.rest.Model.Pet;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetController extends BaseController{

    @RequestMapping(method = RequestMethod.GET,value="/pet/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet>  findPetById(@PathVariable Long petId){
        return petStoreDataHandler.findPetById(petId);
    }
}