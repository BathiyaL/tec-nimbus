import { test, expect } from '@playwright/test';

test('GET /pet/{petId} - Find pet by ID', async ({ request }) => {
  const petId = 12345; // Assuming this pet exists from previous tests

  const response = await request.get(`https://petstore.swagger.io/v2/pet/${petId}`);

  expect(response.status()).toBe(200);

  const responseData = await response.json();
  expect(responseData).toHaveProperty('id', petId);
  expect(responseData).toHaveProperty('name');
  expect(responseData).toHaveProperty('photoUrls');
  expect(Array.isArray(responseData.photoUrls)).toBe(true);
  expect(responseData).toHaveProperty('status');
  expect(['available', 'pending', 'sold']).toContain(responseData.status);
});