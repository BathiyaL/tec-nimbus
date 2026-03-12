import { test, expect } from '@playwright/test';

test('User can login successfully', async ({ page }) => {
  
  // Navigate to the website
  await page.goto('https://example.com/login');

  // Enter username
  await page.fill('#username', 'testuser');

  // Enter password
  await page.fill('#password', 'password123');

  // Click login button
  await page.click('button[type="submit"]');

  // Verify successful login
  await expect(page).toHaveURL('https://example.com/dashboard');

  // Verify dashboard element is visible
  await expect(page.locator('h1')).toHaveText('Dashboard');
});
