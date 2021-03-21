package eximple;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//@ExtendWith can be used with method as well
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "ArticlesProvider")
public class ExampleJavaConsumerPactTest {

    @BeforeEach
    public void setUp(MockServer mockServer) {
        assertThat(mockServer, is(notNullValue()));
    }

    @Test
    public void test(final MockServer mockServer) throws IOException {
        final var httpResponse = Request
                .Get(format("%s%s", mockServer.getUrl(), "/articles.json"))
                .execute()
                .returnResponse();

        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));
    }

    @Pact(provider = "ArticlesProvider", consumer = "test_consumer")
    public RequestResponsePact getPact(final PactDslWithProvider builder) {
        return builder
                .given("test state")
                .uponReceiving("ExampleJavaConsumerPactTest test interaction")
                .path("/articles.json")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\"responsetest\": true}")
                .toPact();
    }
}
