import { test, expect } from '@playwright/test';

test('PUT /pet - Update an existing pet', async ({ request }) => {
  const updatedPet = {
    id: 12345,
    category: {
      id: 1,
      name: 'Dogs'
    },
    name: 'UpdatedTestDog',
    photoUrls: ['https://example.com/photo1.jpg', 'https://example.com/photo2.jpg'],
    tags: [
      {
        id: 1,
        name: 'friendly'
      },
      {
        id: 2,
        name: 'playful'
      }
    ],
    status: 'sold'
  };

  const response = await request.put('https://petstore.swagger.io/v2/pet', {
    data: updatedPet,
    headers: {
      'Content-Type': 'application/json'
    }
  });

  expect(response.status()).toBe(200);

  const responseData = await response.json();
  expect(responseData).toHaveProperty('id', 12345);
  expect(responseData).toHaveProperty('name', 'UpdatedTestDog');
  expect(responseData).toHaveProperty('status', 'sold');
  expect(responseData.photoUrls).toContain('https://example.com/photo1.jpg');
  expect(responseData.photoUrls).toContain('https://example.com/photo2.jpg');
});