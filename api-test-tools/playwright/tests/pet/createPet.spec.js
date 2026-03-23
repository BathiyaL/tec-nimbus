import { test, expect } from '@playwright/test';
import { PetAPI } from '../../api/pet.api.js';
import { createPetPayload } from '../../utils/dataGenerator.js';

test('@smoke Create a pet', async ({ request }) => {
  const petApi = new PetAPI(request);
  const payload = createPetPayload();
  // console.log('Request URL:', 'https://petstore.swagger.io/v2/pet');
  console.log('Request Payload:', JSON.stringify(payload, null, 2));
  const response = await petApi.createPet(payload);
  console.log('Request URL:', response.url());

  console.log('Create Pet Response:', response.json());
  expect(response.status()).toBe(200);
});