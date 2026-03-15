import { test, expect } from '@playwright/test';

test('DELETE /pet/{petId} - Delete a pet', async ({ request }) => {
  const petId = 12345;

  const response = await request.delete(`https://petstore.swagger.io/v2/pet/${petId}`);

  expect(response.status()).toBe(200);

  // Verify the pet is deleted
  const getResponse = await request.get(`https://petstore.swagger.io/v2/pet/${petId}`);
  expect(getResponse.status()).toBe(404);
});