import { test, expect } from '@playwright/test';

test('GET /pet/findByTags - Find pets by tags', async ({ request }) => {
  const response = await request.get('https://petstore.swagger.io/v2/pet/findByTags?tags=friendly');

  expect(response.status()).toBe(200);

  const responseData = await response.json();
  expect(Array.isArray(responseData)).toBe(true);

  if (responseData.length > 0) {
    const pet = responseData[0];
    expect(pet).toHaveProperty('id');
    expect(pet).toHaveProperty('name');
    expect(pet).toHaveProperty('tags');
    expect(Array.isArray(pet.tags)).toBe(true);
    expect(pet).toHaveProperty('photoUrls');
    expect(Array.isArray(pet.photoUrls)).toBe(true);
  }
});