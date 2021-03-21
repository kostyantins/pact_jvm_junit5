package api.clients;

import au.com.dius.pact.consumer.MockServer;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static java.lang.String.format;

public class PlanetsClient extends BasedAPIClient {

    public static Response getPlanets(final int limitOfPlanets, final MockServer mockServer) {
        return basedAPIClient
                .get(new RequestSpecBuilder()
                        .setBaseUri(mockServer.getUrl())
                        .setBasePath(format("api/planets/%s", limitOfPlanets))
                        .build());
    }
}
