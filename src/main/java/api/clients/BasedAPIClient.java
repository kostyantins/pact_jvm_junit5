package api.clients;


import au.com.dius.pact.consumer.MockServer;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import util.listeners.SwapiAllureRestAssured;

import java.util.Objects;

import static io.restassured.RestAssured.*;
import static io.restassured.parsing.Parser.JSON;
import static java.lang.Boolean.parseBoolean;
import static java.util.Objects.requireNonNull;
import static util.PropertiesReader.getProperty;

@Getter
public class BasedAPIClient {

    protected static BasedAPIClient basedAPIClient;
    protected static String baseUrl;
    protected static RequestSpecification baseSpecSpecification;

    static {
        basedAPIClient = new BasedAPIClient();
        defaultParser = JSON;
        //todo uncomment to run tests without mocks
        //baseUrl = getProperty("api.base.url");
        //baseURI = baseUrl;

        filters(new SwapiAllureRestAssured());

        if (parseBoolean(getProperty("api.logs.print"))) {
            filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }
    }

    public final Response get(final RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .get();
    }

    public final Response post(final RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .post();
    }

    public final Response put(final RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .put();
    }

    public final Response delete(final RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .delete();
    }
}