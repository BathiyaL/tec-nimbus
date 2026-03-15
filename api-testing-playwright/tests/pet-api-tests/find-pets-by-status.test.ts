import { test, expect } from '@playwright/test';

test('GET /pet/findByStatus - Find pets by status', async ({ request }) => {
  const response = await request.get('https://petstore.swagger.io/v2/pet/findByStatus?status=available');

  expect(response.status()).toBe(200);

  const responseData = await response.json();
  expect(Array.isArray(responseData)).toBe(true);

  if (responseData.length > 0) {
    const pet = responseData[0];
    expect(pet).toHaveProperty('id');
    expect(pet).toHaveProperty('name');
    expect(pet).toHaveProperty('status');
    expect(['available', 'pending', 'sold']).toContain(pet.status);
    expect(pet).toHaveProperty('photoUrls');
    expect(Array.isArray(pet.photoUrls)).toBe(true);
  }
});