export const logRequest = (method, url, payload) => {
  console.log('--- API REQUEST ---');
  console.log('Method:', method);
  console.log('URL:', url);

  if (payload) {
    console.log('Payload:', JSON.stringify(payload, null, 2));
  }

  console.log('--------------------\n');
};

export const logResponse = async (response) => {
  console.log('--- API RESPONSE ---');
  console.log('Status:', response.status());
  console.log('URL:', response.url());

  try {
    const body = await response.json();
    console.log('Body:', JSON.stringify(body, null, 2));
  } catch (e) {
    console.log('Body: Unable to parse JSON');
  }

  console.log('---------------------\n');
};

export const logError = (error) => {
  console.log('--- API ERROR ---');
  console.error(error);
  console.log('-----------------\n');
};