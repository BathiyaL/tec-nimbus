import { test, expect } from '../../fixtures/apiClient.js';
import { PetAPI } from '../../api/pet.api.js';
import { createPetPayload } from '../../utils/dataGenerator.js';

test('@smoke Create a pet via apiClient fixture', async ({ apiClient }) => {
  const petApi = new PetAPI(apiClient);
  const payload = createPetPayload();

  const response = await petApi.createPet(payload);

  expect(response.status()).toBe(200);
  const body = await response.json();
  expect(body.name).toBe(payload.name);
});