package utils;

import io.cucumber.java.Scenario;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import io.restassured.specification.FilterableRequestSpecification;

/**
 * The type Request methods.
 */
public class RequestMethods {

    /**
     * @param filterableRequestSpecification the filterable request specification
     *                                       Post json payload response.
     * @param scenario                       the scenario
     * @return the response
     */

    public Response postRequest(FilterableRequestSpecification filterableRequestSpecification, Scenario scenario) {
        Response response = null;
        try {
            response = filterableRequestSpecification
                    .baseUri(PropReader.getInstance().getHost())
                    .contentType(ContentType.JSON)
                    .body(filterableRequestSpecification.getBody())
                    .post()
                    .then().log().all()
                    .extract()
                    .response();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        scenario.log("Method: " + filterableRequestSpecification.getMethod()
                + "     BaseUri: " + filterableRequestSpecification.getBaseUri()
        +"     DerivedPath: "+filterableRequestSpecification.getDerivedPath());
        return response;
    }

    /**
     * Gets response.
     *
     * @param filterableRequestSpecification the filterable request specification
     * @param scenario                       the scenario
     * @return the response
     */
    public Response getResponse(FilterableRequestSpecification filterableRequestSpecification, Scenario scenario) {
        Response response = null;
        try {
            response = filterableRequestSpecification.baseUri(PropReader.getInstance().getHost())
                    .contentType(ContentType.JSON)
                    .get()
                    .then().log().all()
                    .extract()
                    .response();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        scenario.log("Method: " + filterableRequestSpecification.getMethod()
                + "     BaseUri: " + filterableRequestSpecification.getBaseUri()
                +"     DerivedPath: "+filterableRequestSpecification.getDerivedPath());

        return response;
    }

    public Response updateRequest(FilterableRequestSpecification filterableRequestSpecification, Scenario scenario) {
        Response response = null;
        try {
            response = filterableRequestSpecification
                    .baseUri(PropReader.getInstance().getHost())
                    .contentType(ContentType.JSON)
                    .body(filterableRequestSpecification.getBody())
                    .patch()
                    .then().log().all()
                    .extract()
                    .response();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        scenario.log("Method: " + filterableRequestSpecification.getMethod()
                + "     BaseUri: " + filterableRequestSpecification.getBaseUri()
                +"     DerivedPath: "+filterableRequestSpecification.getDerivedPath());
        return response;
    }

    /**
     * Gets env.
     *
     * @return the env
     */
    public static String getEnv() {
        return StringUtils.isBlank(System.getenv().get("env")) ? "qa" : System.getenv().get("env");
    }
}
