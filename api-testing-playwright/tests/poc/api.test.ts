import { test, expect, request } from '@playwright/test';

test('GET pet by ID', async ({ request }) => {
  const response = await request.get('https://petstore.swagger.io/v2/pet/10');

  expect(response.status()).toBe(200);
  const data = await response.json();
  console.log(data);
  expect(data).toHaveProperty('id', 10);
  expect(data.name).toBeDefined();
});