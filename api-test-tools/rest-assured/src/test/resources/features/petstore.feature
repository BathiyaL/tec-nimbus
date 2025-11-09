Feature: PetStore API - basic CRUD scenarios

Background:
    Given the PetStore API base URL is "http://localhost:8080/api/v1"

Scenario: Add a new pet to the store
    When I create a new pet with name "fluffy" and status "available"
    Then the response status code should be 200
    And I save the returned pet id as "petId"

Scenario: Update an existing pet
    Given I have a stored "petId"
    When I update the pet name to "fluffy-updated" and status to "sold"
    Then the response status code should be 200
    And the response body name should be "fluffy-updated"