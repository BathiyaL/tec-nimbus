import { request, test as base } from '@playwright/test';

type Fixtures = {
  apiContext: Awaited<ReturnType<typeof request.newContext>>;
};

export const test = base.extend<Fixtures>({
  apiContext: async ({}, use) => {
    const apiContext = await request.newContext({
      baseURL: 'https://jsonplaceholder.typicode.com',
      extraHTTPHeaders: {
        // Example header; update with your real token if needed
        Authorization: 'Bearer dummy-token',
        Accept: 'application/json'
      }
    });

    await use(apiContext);
    await apiContext.dispose();
  },
});

export { expect } from '@playwright/test';
