package com.petstore.rest_assured.steps;

import com.petstore.rest_assured.support.ScenarioContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetSteps {

    private final ScenarioContext context;
    private Response lastResponse;

    public PetSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("the PetStore API base URL is {string}")
    public void setBaseUrl(String url) {
        baseURI = url;
    }

    @When("I create a new pet with name {string} and status {string}")
    public void createNewPet(String name, String status) {
        String body = String.format(
                """
                    {
                              "id": 4677,
                              "name": "%s",
                              "status": "%s",
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
                """, name, status);

        lastResponse = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/pet")
                .then()
                .extract().response();
    }

    @Then("the response status code should be {int}")
    public void assertStatus(int code) {
        lastResponse.then().statusCode(code);
    }

    @Then("I save the returned pet id as {string}")
    public void savePetId(String varName) {
        Long id = lastResponse.jsonPath().getLong("id");
        context.set(varName, id);
        assertThat(context.get("petId"), is(notNullValue()));
    }

    @Given("I have a stored {string}")
    public void haveStoredId(String varName) {
        // ensure exists
        assertThat(context.get(varName), is(notNullValue()));
    }


    @When("I update the pet name to {string} and status to {string}")
    public void updatePet(String name, String status) {
        Long petId = context.getLong("petId");
        String body = String.format("""
            {
                      "id": %d,
                      "name": "%s",
                      "status": "%s",
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
            """, petId, name, status);

        lastResponse = given()
                .contentType("application/json")
                .body(body)
                .when()
                .put("/pet")
                .then()
                .extract().response();
    }

    @When("I send a DELETE request to remove the pet by ID")
    public void i_send_a_delete_request_to_remove_the_pet_by_id() {
        Long petId = context.getLong("petId");
        lastResponse = given()
                .contentType("application/json")
                .when()
                .delete("/pet/" + petId)
                .then()
                .extract().response();
    }
    @Then("the response body name should be {string}")
    public void responseNameShould(String expectedName) {
        String name = lastResponse.jsonPath().getString("name");
        assertThat(name, equalTo(expectedName));
    }

    @When("I get the pet by id")
    public void getPetById() {
        Long petId = context.getLong("petId");
        lastResponse = given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .extract().response();
    }

    @Then("the returned id should equal the stored {string}")
    public void returnedIdEquals(String varName) {
        Long expected = context.getLong(varName);
        Long returned = lastResponse.jsonPath().getLong("id");
        assertThat(returned, equalTo(expected));
    }

}