package api.clients;

import au.com.dius.pact.consumer.MockServer;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static java.lang.String.format;

public class StarshipClient extends BasedAPIClient {

    public static Response getStarships(final int limitOfStarships, final MockServer mockServer) {
        return basedAPIClient
                .get(new RequestSpecBuilder()
                        .setBaseUri(mockServer.getUrl())
                        .setBasePath(format("api/starships/%s", limitOfStarships))
                        .build());
    }
}
