export const createPetPayload = () => ({
  id: Date.now(),
  name: `Pet_${Date.now()}`,
  status: 'available'
});