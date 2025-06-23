package com.tecnimbus.petstore_api.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test-wiremock")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Pet API Integration Test with WireMock")
public class PetControllerIntegrationTest {

    private WireMockServer wireMockServer;

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeAll
    void startWireMock() {
        wireMockServer = new WireMockServer(9561); // Port must match test YAML
        wireMockServer.start();

        configureFor("localhost", 9561);
        stubFor(get(urlEqualTo("/pet/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                    {
                                      "id": 1,
                                      "category": {
                                        "id": 1,
                                        "name": "string"
                                      },
                                      "name": "doggie",
                                      "photoUrls": [
                                        "string"
                                      ],
                                      "tags": [
                                        {
                                          "id": 1,
                                          "name": "string"
                                        }
                                      ],
                                      "status": "available"
                                    }
                        """)
                        .withStatus(200)));
    }

    @AfterAll
    void stopWireMock() {
        wireMockServer.stop();
    }

    @Test
    void testGetPetById_shouldReturnMockedPet() {
        String url = "http://localhost:" + port + "/api/v1/pet/1";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("doggie");
    }
}
