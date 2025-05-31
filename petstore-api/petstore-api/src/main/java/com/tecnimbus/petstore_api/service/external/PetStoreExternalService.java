package com.tecnimbus.petstore_api.service.external;

import com.tecnimbus.petstore_api.Model.Pet;
import com.tecnimbus.petstore_api.constants.ExternalEndpoints;
import com.tecnimbus.petstore_api.exception.ResourceNotFoundException;
import com.tecnimbus.petstore_api.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class PetStoreExternalService extends BaseService {

    @Autowired
    protected RestTemplate restTemplate;

    @Value("${external.pet-store.url}")
    private String remotePetStore;

    public ResponseEntity<Pet> findPetById(Long petId) {
        String remoteUrl = remotePetStore + ExternalEndpoints.FIND_PET_BY_ID_URL + petId;
        HttpEntity<Void> entity = new HttpEntity<>(getHeaders());

        try {
            return restTemplate.exchange(
                    remoteUrl, HttpMethod.GET, entity, Pet.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("Pet not found in external API with id: " + petId);
        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("Client error: " + ex.getMessage());
        } catch (HttpServerErrorException ex) {
            throw new RuntimeException("Server error: " + ex.getMessage());
        }

    }
}
