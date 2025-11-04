package com.petstore.rest_assured.api_tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetStoreApiTests {

    private static Long petId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }

    // Add a new pet
    @Test(priority = 1)
    public void testAddNewPet() {
        String requestBody = """
        {
          "id": 4677,
          "name": "fluffy",
          "status": "New",
          "photoUrls": [
            "Photo1",
            "Photo1"
          ],
          "category": {
            "id": 9342,
            "name": "ABC"
          },
          "tags": [
            {
              "name": "PET",
              "id": 2875
            },
            {
              "name": "STORE",
              "id": 7063
            }
          ]
        }
        """;
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("fluffy"))
                .extract().response();

        petId = response.jsonPath().getLong("id");
        System.out.println("Created Pet ID: " + petId);
    }

    // Update an existing pet
    @Test(priority = 2, dependsOnMethods = "testAddNewPet")
    public void testUpdatePet() {
        String requestBody = String.format("""
        {
          "id": %d,
          "name": "fluffy-updated",
          "status": "SOLD",
          "photoUrls": [
            "Photo1",
            "Photo1"
          ],
          "category": {
            "id": 9342,
            "name": "ABC"
          },
          "tags": [
            {
              "name": "PET",
              "id": 2875
            },
            {
              "name": "STORE",
              "id": 7063
            }
          ]
        }
        """, petId);

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("fluffy-updated"))
                .body("status", equalTo("SOLD"));
    }

    // Find pet by ID
    @Test(priority = 3, dependsOnMethods = "testUpdatePet")
    public void testFindPetById() {
        given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(petId.intValue()));
    }

    // Delete Pet
    @Test(priority = 4, dependsOnMethods = "testFindPetById")
    public void testDeletePet() {
        given()
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(200);
    }

    // Pet not found
    @Test(priority = 5)
    public void testPetNotFound() {
        given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(404)
                .body("message", equalTo("Pet not found with id: " + petId));
    }
}
