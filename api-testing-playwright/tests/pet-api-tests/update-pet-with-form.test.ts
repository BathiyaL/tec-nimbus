import { test, expect } from '@playwright/test';

test('POST /pet/{petId} - Update pet with form data', async ({ request }) => {
  const petId = 12345;

  const response = await request.post(`https://petstore.swagger.io/v2/pet/${petId}`, {
    form: {
      name: 'FormUpdatedDog',
      status: 'pending'
    }
  });

  expect(response.status()).toBe(200);

  // Verify the update by fetching the pet
  const getResponse = await request.get(`https://petstore.swagger.io/v2/pet/${petId}`);
  expect(getResponse.status()).toBe(200);

  const petData = await getResponse.json();
  expect(petData.name).toBe('FormUpdatedDog');
  expect(petData.status).toBe('pending');
});