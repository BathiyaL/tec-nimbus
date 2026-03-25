import dev from './dev.env.js';

const ENV = process.env.ENV || 'dev';

const envMap = {
  dev: dev
};

const selectedEnv = envMap[ENV];

if (!selectedEnv) {
  throw new Error(`Invalid ENV: ${ENV}`);
}

console.log(`Running tests in: ${selectedEnv.name || 'unknown'}`);
console.log(`Base URL: ${selectedEnv.baseURL || 'missing'}`);

export default selectedEnv;