package com.tecnimbus.petstore_api.controller.pet;

import com.tecnimbus.petstore_api.entity.Pet;
import com.tecnimbus.petstore_api.controller.BaseController;
import com.tecnimbus.petstore_api.model.PetDTO;
import com.tecnimbus.petstore_api.service.pet.PetService;
import jakarta.validation.Valid;
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

    @RequestMapping(method = RequestMethod.POST, value = "/pet")
    public PetDTO addNewPet(@Valid @RequestBody PetDTO pet) {
        return petService.AddNewPetToTheStore(pet);
    }
}