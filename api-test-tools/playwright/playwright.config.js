import dev from './config/dev.env.js';

export default {
  testDir: './tests',

  use: {
    baseURL: dev.baseURL,
    extraHTTPHeaders: {
      'Content-Type': 'application/json'
    }
  }
};
