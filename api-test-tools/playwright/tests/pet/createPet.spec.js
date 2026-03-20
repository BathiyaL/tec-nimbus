import { test, expect } from '@playwright/test';
import { PetAPI } from '../../api/pet.api.js';
import { createPetPayload } from '../../utils/dataGenerator.js';

test('@smoke Create a pet', async ({ request }) => {
  const petApi = new PetAPI(request);
  const payload = createPetPayload();
  const response = await petApi.createPet(payload);
  expect(response.status()).toBe(200);
});