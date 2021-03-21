package util.base_tests;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(PactConsumerTestExt.class)
public class BasedSwapiTest {

    @BeforeEach
    public void setUp(MockServer mockServer) {
        assertThat("Mock server shouldn't be null !!", mockServer, is(notNullValue()));
    }
}