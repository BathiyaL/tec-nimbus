import { test as base } from '@playwright/test';

export const test = base.extend({
  apiClient: async ({ request }, use) => {
    await use(request);
  }
});
