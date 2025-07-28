import { test, expect } from './api-fixtures';

test('GET posts using custom fixture context', async ({ apiContext }) => {
  const response = await apiContext.get('/posts/2');

  expect(response.ok()).toBeTruthy();
  const data = await response.json();

  expect(data.id).toBe(2);
  console.log(data);
  console.log("Fixtures: TEST PASSED with status code : " + response.status());
});
