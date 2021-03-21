package swapi;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import util.base_tests.BasedSwapiTest;

import static api.clients.PlanetsClient.getPlanets;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;

@PactTestFor(pactMethod = "planets")
public class PlanetsTest extends BasedSwapiTest {

    @Test
    public void testPlanets(final MockServer mockServer) {
        getPlanets(3, mockServer)
                .then().assertThat().statusCode(SC_OK)
                .body("rotation_period", is("24"));
    }

    @Pact(provider = "PlanetsProvider", consumer = "aqa")
    public RequestResponsePact planets(final PactDslWithProvider builder) {
        return builder
                .given("Test GET /api/planets/3")
                .uponReceiving("Starships test interaction")
                .path("/api/planets/3")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(new PactDslJsonBody()
                        .stringValue("name", "Yavin IV")
                        .stringValue("rotation_period", "24")
                        .stringValue("orbital_period", "4818")
                        .stringValue("diameter", "150000000")
                        .stringValue("climate", "temperate, tropical")
                        .stringValue("gravity", "1 standard")
                        .stringValue("terrain", "jungle, rainforests")
                        .stringValue("surface_water", "8")
                        .stringValue("population", "1000")
                        .array("residents")
                        .array()
                        .stringType()
                        .stringType()
                        .stringType())
                .toPact();
    }
}
