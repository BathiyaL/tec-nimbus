import { expect } from '@playwright/test';
import { test } from '../../fixtures/apiClient.js';
import { PetAPI } from '../../api/pet.api.js';
import { createPetPayload } from '../../utils/dataGenerator.js';
import env from '../../config/envManager.js';

test('@smoke Create a pet', async ({ request }) => {
  const petApi = new PetAPI(request, env.baseURL);
  const payload = createPetPayload();
  const response = await petApi.createPet(payload);
  expect(response.status()).toBe(200);
});

test('@smoke Create a pet via apiClient fixture', async ({ apiClient }) => {
  const petApi = new PetAPI(apiClient);
  const payload = createPetPayload();

  const response = await petApi.createPet(payload);

  expect(response.status()).toBe(200);
  const body = await response.json();
  expect(body.name).toBe(payload.name);
});