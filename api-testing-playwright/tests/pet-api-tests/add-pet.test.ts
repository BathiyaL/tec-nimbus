import { test, expect } from '@playwright/test';

test('POST /pet - Add a new pet', async ({ request }) => {
  const newPet = {
    id: 12345,
    category: {
      id: 1,
      name: 'Dogs'
    },
    name: 'TestDog',
    photoUrls: ['https://example.com/photo1.jpg'],
    tags: [
      {
        id: 1,
        name: 'friendly'
      }
    ],
    status: 'available'
  };

  const response = await request.post('https://petstore.swagger.io/v2/pet', {
    data: newPet,
    headers: {
      'Content-Type': 'application/json'
    }
  });

  expect(response.status()).toBe(200);

  const responseData = await response.json();
  expect(responseData).toHaveProperty('id');
  expect(responseData).toHaveProperty('name', 'TestDog');
  expect(responseData).toHaveProperty('status', 'available');
  expect(responseData).toHaveProperty('photoUrls');
  expect(Array.isArray(responseData.photoUrls)).toBe(true);
});