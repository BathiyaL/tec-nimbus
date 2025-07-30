import { test, expect } from './api-fixtures';

test('POST add a new pet to the store', async ({ apiContext }) => {
  const requestBody = {
  id: 0,
  category: {
    id: 0,
    name: "string"
  },
  name: "doggie",
  photoUrls: [
    "string"
  ],
  tags: [
    {
      id: 0,
      name: "string"
    }
  ],
  "status": "available"
};
  const response = await apiContext.post('/v2/pet', {
    data: requestBody
  });

  expect(response.ok()).toBeTruthy();
  const data = await response.json();

  console.log(data);
  console.log("Fixtures: TEST PASSED with status code : " + response.status());
});
