import { ENDPOINTS } from '../constants/endpoints.js';
import { logRequest, logResponse, logError } from '../utils/logger.js';
import env from '../config/envManager.js';

export class PetAPI {
  constructor(request, baseURL) {
    if (!request) throw new Error('PetAPI requires a request object');
    this.request = request;
    this.baseURL = baseURL || env.baseURL;
  }

  async createPet(payload) {
    console.log(`POST ${ENDPOINTS.PET}`);
    try {
      const requestUrl = `${this.baseURL}${ENDPOINTS.PET}`;
      logRequest('POST', requestUrl, payload);
      const response = await this.request.post(requestUrl, {
        data: payload
      });
      await logResponse(response);
      return response;
    } catch (error) {
      logError(error);
      throw error;
    }
  }

  async getPetById(id) {
    try {
      const requestUrl = `${this.baseURL}${ENDPOINTS.PET}/${id}`;
      logRequest('GET', requestUrl);
      const response = await this.request.get(requestUrl);
      await logResponse(response);
      return response;
    } catch (error) {
      logError(error);
      throw error;
    }
  }

  async deletePet(id) {
    try {
      const requestUrl = `${this.baseURL}${ENDPOINTS.PET}/${id}`;
      logRequest('DELETE', requestUrl);
      const response = await this.request.delete(requestUrl);
      await logResponse(response);
      return response;
    } catch (error) {
      logError(error);
      throw error;
    }
  }
}