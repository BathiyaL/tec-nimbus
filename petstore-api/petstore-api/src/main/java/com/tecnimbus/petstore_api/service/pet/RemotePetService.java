package com.tecnimbus.petstore_api.service.pet;

import com.tecnimbus.petstore_api.mapper.PetMapper;
import com.tecnimbus.petstore_api.model.PetDTO;
import com.tecnimbus.petstore_api.repository.pet.PetRepository;
import com.tecnimbus.petstore_api.service.external.PetStoreExternalService;
import com.tecnimbus.petstore_api.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemotePetService implements PetServiceStrategy {

    @Autowired
    PetStoreExternalService petStoreExternalService;

    @Override
    public PetDTO addNewPetToTheStore(PetDTO petDTO) {
        return  null;
    }

    @Override
    public PetDTO findPetById(Long petId) {
        return petStoreExternalService.findPetById(petId);
    }
}
