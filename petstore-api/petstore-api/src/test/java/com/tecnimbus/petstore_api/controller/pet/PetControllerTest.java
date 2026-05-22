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
        //Arrange
        Long id = 1L;
        PetDTO pet = new PetDTO();
        pet.setId(id);

        // Act
        when(petServiceRouter.findPetById(id)).thenReturn(pet);

        PetDTO result = petController.findPetById(id);

        //assert
        assertSame(pet, result);
        verify(petServiceRouter).findPetById(id);
    }

    @Test
    void findPetById_shouldReturnNullWhenServiceReturnsNull() {
        Long id = 99L;
        when(petServiceRouter.findPetById(id)).thenReturn(null);

        PetDTO result = petController.findPetById(id);

        assertNull(result);
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
    void addNewPet_shouldHandleNullSavedPet() {
        PetDTO pet = new PetDTO();
        pet.setName("Ghost");

        when(petServiceRouter.addNewPetToTheStore(pet)).thenReturn(null);

        ResponseEntity<PetDTO> response = petController.addNewPet(pet);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(petServiceRouter).addNewPetToTheStore(pet);
    }

    @Test
    void addNewPet_withNullInput_shouldPropagateIllegalArgumentException() {
        when(petServiceRouter.addNewPetToTheStore(null)).thenThrow(new IllegalArgumentException("pet is null"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> petController.addNewPet(null));
        assertEquals("pet is null", ex.getMessage());
        verify(petServiceRouter).addNewPetToTheStore(null);
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
    void updatePet_whenServiceThrowsRuntimeException_shouldPropagate() {
        PetDTO pet = new PetDTO();
        pet.setId(5L);
        pet.setName("Broken");

        when(petServiceRouter.updateAnExistingPet(pet)).thenThrow(new RuntimeException("update failed"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> petController.updatePet(pet));
        assertEquals("update failed", ex.getMessage());
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

    @Test
    void deletePet_whenServiceReturnsNull_shouldReturnResponseWithNullBody() {
        Long id = 777L;
        when(petServiceRouter.deleteAnExistingPet(id)).thenReturn(null);

        ResponseEntity<ApiResponse> response = petController.deletePet(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(petServiceRouter).deleteAnExistingPet(id);
    }
}
