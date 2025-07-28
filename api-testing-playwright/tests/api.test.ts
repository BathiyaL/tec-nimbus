import { test, expect, request } from '@playwright/test';

test('GET post by ID', async ({ request }) => {
  const response = await request.get('https://jsonplaceholder.typicode.com/posts/1');

  expect(response.status()).toBe(200);
  const data = await response.json();

//   expect(data).toHaveProperty('id', 1);
//   expect(data.title).toBeDefined();
console.log(data);
  console.log("TEST PASSED with status code : " + response.status());
});
