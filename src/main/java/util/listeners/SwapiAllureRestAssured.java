package util.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static io.qameta.allure.Allure.getLifecycle;
import static io.qameta.allure.model.Status.PASSED;

@NoArgsConstructor
public class SwapiAllureRestAssured extends AllureRestAssured {

    public Response filter(final FilterableRequestSpecification requestSpec,
                           final FilterableResponseSpecification responseSpec,
                           final FilterContext filterContext) {

        var lifecycle = getLifecycle();

        lifecycle.startStep(UUID.randomUUID().toString(), (new StepResult())
                .setStatus(PASSED)
                .setName(String.format("%s: %s", requestSpec.getMethod(), requestSpec.getURI())));

        Response response;

        try {
            response = super.filter(requestSpec, responseSpec, filterContext);
        } finally {
            lifecycle.stopStep();
        }

        return response;
    }
}
