import { test as base, expect, request as playwrightRequest } from '@playwright/test';
import env from '../config/envManager.js';

export const test = base.extend({
  apiClient: async ({}, use) => {
    const apiClient = await playwrightRequest.newContext({
      baseURL: env.baseURL,
      extraHTTPHeaders: {
        'Content-Type': 'application/json',
        ...(env.defaultHeaders || {}),
      }
    });

    await use(apiClient);
    await apiClient.dispose();
  }
});

export { expect };