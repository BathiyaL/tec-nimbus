package com.tecnimbus.petstore_api.service.pettag;

import com.tecnimbus.petstore_api.entity.Pet;
import com.tecnimbus.petstore_api.entity.PetTags;
import com.tecnimbus.petstore_api.entity.Tag;
import com.tecnimbus.petstore_api.model.TagDTO;
import com.tecnimbus.petstore_api.repository.pettag.PetTagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetTagService {

    @Autowired
    PetTagsRepository petTagRepository;

    public PetTagService(PetTagsRepository petTagRepository) {
        this.petTagRepository = petTagRepository;
    }

    public PetTags addTagToPet(Pet pet, Tag tag) {
        return petTagRepository.save(new PetTags(pet, tag));
    }

    public List<Tag> getTagsByPet(Pet pet) {
        return petTagRepository.findByPet(pet)
                .stream()
                .map(PetTags::getTag)
                .toList();
    }

    public List<TagDTO> getTagDTOByPet(Pet pet) {
        List<Tag> tags = getTagsByPet(pet);
        return tags.stream()
                .map(tag -> new TagDTO(tag.getId(), tag.getName()))
                .toList();
    }

    public List<Pet> getPetsByTag(Tag tag) {
        return petTagRepository.findByTag(tag)
                .stream()
                .map(PetTags::getPet)
                .toList();
    }
}
