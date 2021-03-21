package swapi;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import util.base_tests.BasedSwapiTest;

import static api.clients.StarshipClient.getStarships;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;

@PactTestFor(pactMethod = "starships")
public class StarshipTest extends BasedSwapiTest {

    @Test
    public void testStarships(final MockServer mockServer) {
        getStarships(3, mockServer)
                .then().assertThat().statusCode(SC_OK)
                .body("name", is("Star Destroyer"));
    }

    @Pact(provider = "StarshipsProvider", consumer = "aqa")
    public RequestResponsePact starships(final PactDslWithProvider builder) {
        return builder
                .given("Test GET /api/starships/3")
                .uponReceiving("Starships test interaction")
                .path("/api/starships/3")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(new PactDslJsonBody()
                        .stringValue("name", "Star Destroyer")
                        .stringValue("model", "Imperial I-class Star Destroyer")
                        .stringValue("manufacturer", "Kuat Drive Yards")
                        .stringValue("cost_in_credits", "150000000")
                        .stringValue("length", "1,600")
                        .stringValue("max_atmosphering_speed", "975")
                        .stringValue("crew", "47,060")
                        .stringValue("passengers", "n/a")
                        .stringValue("cargo_capacity", "36000000")
                        .stringValue("consumables", "2 years")
                        .stringValue("hyperdrive_rating", "2.0")
                        .stringValue("MGLT", "60")
                        .stringValue("starship_class", "Star Destroyer")
                        .array("pilots")
                        .array()
                        .stringType()
                        .stringType()
                        .stringType())
                .toPact();
    }
}
