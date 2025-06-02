package com.tecnimbus.petstore_api.service.tag;

import com.tecnimbus.petstore_api.entity.Tag;
import com.tecnimbus.petstore_api.mapper.TagMapper;
import com.tecnimbus.petstore_api.model.TagDTO;
import com.tecnimbus.petstore_api.repository.tag.TagRepository;
import com.tecnimbus.petstore_api.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService extends BaseService {

    @Autowired
    TagMapper tagMapper;

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagDTO findOrCreateByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Tag name cannot be null or empty");
        }
        Tag tag = new Tag();
        tag.setName(name.toUpperCase());
        return tagMapper.toDto(tagRepository.findByName(name)
                .orElseGet(() -> tagRepository.save(tag)));

    }
}