import { ENDPOINTS } from '../constants/endpoints.js';

export class PetAPI {
  constructor(request) {
    this.request = request;
  }

  async createPet(payload) {
    return await this.request.post(ENDPOINTS.PET, { data: payload });
  }

  async getPetById(id) {
    return await this.request.get(`${ENDPOINTS.PET}/${id}`);
  }

  async deletePet(id) {
    return await this.request.delete(`${ENDPOINTS.PET}/${id}`);
  }
}