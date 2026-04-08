import { test, expect } from '../../fixtures/apiClient.js';
import { loadJson, replacePlaceholders } from '../../utils/dataLoader.js';
import { PetAPI } from '../../api/pet.api.js';

test('Create pet with JSON payload', async ({ apiClient }) => {

  const petApi = new PetAPI(apiClient);

  // Load JSON
  const data = loadJson('./test-data/petstore-api/pet/requests/createPet.json');

  // Replace dynamic values
  const payload = replacePlaceholders(data, {
    id: Date.now()
  });

  const response = await petApi.createPet(payload);

  expect(response.status()).toBe(200);
  const body = await response.json();
  expect(body.name).toBe(payload.name);
});