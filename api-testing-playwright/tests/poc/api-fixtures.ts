import { request, test as base } from '@playwright/test';

type Fixtures = {
  apiContext: Awaited<ReturnType<typeof request.newContext>>;
};

export const test = base.extend<Fixtures>({
  apiContext: async ({}, use) => {
    const apiContext = await request.newContext({
      baseURL: 'https://petstore.swagger.io',
      extraHTTPHeaders: {
        Authorization: 'Bearer dummy-token',
        Accept: 'application/json'
      }
    });

    await use(apiContext);
    await apiContext.dispose();
  },
});

export { expect } from '@playwright/test';
