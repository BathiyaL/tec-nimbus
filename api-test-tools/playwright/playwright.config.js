import dev from './config/dev.env.js';

export default {
  testDir: './tests',
  timeout: 30000,
  use: {
    baseURL: dev.baseURL,
    extraHTTPHeaders: {
      'Content-Type': 'application/json'
    }
  },
  reporter: [['html', { open: 'never' }]],
};