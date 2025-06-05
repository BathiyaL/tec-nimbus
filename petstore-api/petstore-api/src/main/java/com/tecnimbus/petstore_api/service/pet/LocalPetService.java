package com.tecnimbus.petstore_api.service.pet;

import com.tecnimbus.petstore_api.entity.Pet;
import com.tecnimbus.petstore_api.exception.ResourceNotFoundException;
import com.tecnimbus.petstore_api.mapper.PetMapper;
import com.tecnimbus.petstore_api.model.PetDTO;
import com.tecnimbus.petstore_api.model.TagDTO;
import com.tecnimbus.petstore_api.repository.pet.PetRepository;
import com.tecnimbus.petstore_api.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LocalPetService implements PetServiceStrategy {

    @Autowired
    PetMapper petMapper;
    @Autowired
    TagService tagService;
    @Autowired
    PetRepository petRepository;

    @Override
    public PetDTO addNewPetToTheStore(PetDTO petDTO) {
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

    @Override
    public PetDTO findPetById(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));
        return petMapper.toDto(pet);
    }
}
