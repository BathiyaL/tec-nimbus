import { ENDPOINTS } from '../constants/endpoints.js';

export class PetAPI {
  constructor(request, baseURL) {
    this.request = request;
    this.baseURL = baseURL;
  }

  async createPet(payload) {
    console.log(`#####################`);
    console.log(`POST ${this.baseURL}${ENDPOINTS.PET}`);
    return await this.request.post(`${this.baseURL}${ENDPOINTS.PET}`, {
      data: payload
    });
  }

  async getPetById(id) {
    return await this.request.get(`${ENDPOINTS.PET}/${id}`);
  }

  async deletePet(id) {
    return await this.request.delete(`${ENDPOINTS.PET}/${id}`);
  }
}