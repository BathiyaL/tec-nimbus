package com.tecnimbus.petstore_api.service.pet;

import com.tecnimbus.petstore_api.entity.Pet;
import com.tecnimbus.petstore_api.mapper.PetMapper;
import com.tecnimbus.petstore_api.model.PetDTO;
import com.tecnimbus.petstore_api.model.TagDTO;
import com.tecnimbus.petstore_api.repository.pet.PetRepository;
import com.tecnimbus.petstore_api.service.BaseService;
import com.tecnimbus.petstore_api.service.external.PetStoreExternalService;
import com.tecnimbus.petstore_api.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PetService extends BaseService {

    @Autowired
    PetStoreExternalService petStoreExternalService;

    @Autowired
    PetMapper petMapper;

    @Autowired
    TagService tagService;

    @Autowired
    PetRepository petRepository;

    public PetDTO findPetById(Long petId) {
        if (dataMode.equals("Remote")) {
            return petStoreExternalService.findPetById(petId);
        }
        return null;
    }

    public PetDTO AddNewPetToTheStore(PetDTO petDTO) {
        if (dataMode.equals("Remote")) {
            return petStoreExternalService.addNewPetToTheStore(petDTO);
        }else if (dataMode.equals("Local")) {
            Pet pet = petMapper.toEntity(petDTO);
            pet.setId(null);
            Pet savedPet = petRepository.save(pet);

            ArrayList<TagDTO> savedTags = new ArrayList<>();
            for (TagDTO tagDTO : petDTO.getTags()) {
                savedTags.add(tagService.findOrCreateByName(tagDTO.getName()));
            }
            PetDTO petDTOResponse = petMapper.toDto(savedPet);
            petDTOResponse.setTags(savedTags);
            return petDTOResponse;
        }

        return null;
    }
}