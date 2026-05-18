package com.tecnimbus.petstore_api.controller.pet;

import com.tecnimbus.petstore_api.model.ApiResponse;
import com.tecnimbus.petstore_api.model.PetDTO;
import com.tecnimbus.petstore_api.service.pet.PetServiceRouter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    private PetServiceRouter petServiceRouter;

    @InjectMocks
    private PetController petController;

    @Test
    void findPetById_shouldReturnPetDTO() {
        Long id = 1L;
        PetDTO pet = new PetDTO();
        pet.setId(id);

        when(petServiceRouter.findPetById(id)).thenReturn(pet);

        PetDTO result = petController.findPetById(id);

        assertSame(pet, result);
        verify(petServiceRouter).findPetById(id);
    }

    @Test
    void addNewPet_shouldReturnResponseEntityWithSavedPet() {
        PetDTO pet = new PetDTO();
        pet.setName("Fido");
        PetDTO saved = new PetDTO();
        saved.setId(2L);
        saved.setName("Fido");

        when(petServiceRouter.addNewPetToTheStore(pet)).thenReturn(saved);

        ResponseEntity<PetDTO> response = petController.addNewPet(pet);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertSame(saved, response.getBody());
        verify(petServiceRouter).addNewPetToTheStore(pet);
    }

    @Test
    void updatePet_shouldReturnResponseEntityWithSavedPet() {
        PetDTO pet = new PetDTO();
        pet.setId(3L);
        pet.setName("Spot");
        PetDTO saved = new PetDTO();
        saved.setId(3L);
        saved.setName("Spot");

        when(petServiceRouter.updateAnExistingPet(pet)).thenReturn(saved);

        ResponseEntity<PetDTO> response = petController.updatePet(pet);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertSame(saved, response.getBody());
        verify(petServiceRouter).updateAnExistingPet(pet);
    }

    @Test
    void deletePet_shouldReturnApiResponse() {
        Long id = 4L;
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Deleted");

        when(petServiceRouter.deleteAnExistingPet(id)).thenReturn(apiResponse);

        ResponseEntity<ApiResponse> response = petController.deletePet(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertSame(apiResponse, response.getBody());
        verify(petServiceRouter).deleteAnExistingPet(id);
    }
}
