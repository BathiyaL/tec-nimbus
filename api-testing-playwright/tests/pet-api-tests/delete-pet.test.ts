import { test, expect } from '@playwright/test';

let petId: number;

test.beforeEach(async ({ request }) => {
  const petData = {
    name: "TestPet",
    photoUrls: ["http://example.com/photo.jpg"]
  };

  const response = await request.post('https://petstore.swagger.io/v2/pet', {
    data: petData
  });

  expect(response.status()).toBe(200);

  const pet = await response.json();
  petId = pet.id;
  console.log(`Created pet with ID: ${petId}`);
});

test('DELETE /pet/{petId} - Delete a pet', async ({ request }) => {
  const response = await request.delete(`https://petstore.swagger.io/v2/pet/${petId}`);

  expect(response.status()).toBe(200);

  // Verify the pet is deleted
  const getResponse = await request.get(`https://petstore.swagger.io/v2/pet/${petId}`);
  expect(getResponse.status()).toBe(404);
});