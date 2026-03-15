import { test, expect } from '@playwright/test';
import { readFileSync } from 'fs';
import { join } from 'path';

test('POST /pet/{petId}/uploadImage - Upload an image for a pet', async ({ request }) => {
  const petId = 12345;

  // Create a simple test image (or use a placeholder)
  // For this test, we'll assume the endpoint accepts form data
  const response = await request.post(`https://petstore.swagger.io/v2/pet/${petId}/uploadImage`, {
    multipart: {
      additionalMetadata: 'Test image upload',
      file: {
        name: 'test-image.jpg',
        mimeType: 'image/jpeg',
        buffer: Buffer.from('fake-image-data') // In real scenario, use actual file buffer
      }
    }
  });

  expect(response.status()).toBe(200);

  const responseData = await response.json();
  expect(responseData).toHaveProperty('code');
  expect(responseData).toHaveProperty('type');
  expect(responseData).toHaveProperty('message');
});