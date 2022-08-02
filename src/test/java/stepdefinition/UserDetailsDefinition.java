package stepdefinition;

import assertions.CustomAssertions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import models.responsemodels.userDetails.UserDetails;
import org.testng.Assert;
import utils.EndPoints;
import utils.RequestMethods;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static io.restassured.RestAssured.given;


public class UserDetailsDefinition {

    Scenario scenario;
    Response response;
    Gson gson = new Gson();
    EndPoints endpoint = new EndPoints();
    CustomAssertions customAssertions = new CustomAssertions();
    FilterableRequestSpecification requestSpecification;
    RequestMethods requestMethods = new RequestMethods();


    /**
     * Before feature.
     *
     * @param scenario the scenario
     */
    @Before
    public void beforeEachScenario(Scenario scenario) {
        RestAssured.reset();
        this.scenario = scenario;
    }

    /**
     * A user with user id.
     */
    @Given("A user on social media platform")
    public void a_user_on_social_media_platform() {
        scenario.log("Build RequestSpecification");
        requestSpecification = (FilterableRequestSpecification) given();
    }


    @When("hits GET  call to users api")
    public void hits_get_call_to_users_api() {
        requestSpecification.basePath(endpoint.getUsersEndpoint());
        response = requestMethods.getResponse(requestSpecification, scenario);
    }

    /**
     * Validate status code of response.
     */
    @Then("validate status code of response")
    public void validate_status_code_of_response() {
        scenario.log(response.getStatusLine());
        customAssertions.assertTrue(response.getStatusCode(), 200, scenario);
    }

    /**
     * Validate schema of users api.
     */
    @Then("validate schema of users api")
    public void validate_schema_of_users_api() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/schemas/usersList.json");
        scenario.log("Validating response schema with " + file.getName());
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));
    }

    /**
     * Validate user details in response body.
     */
    @Then("validate user details in response body")
    public void validate_user_details_in_response_body() {
        List<UserDetails> userList = gson.fromJson(response.getBody().asString(), new TypeToken<List<UserDetails>>() {}.getType());
        scenario.log("Json Response Array size: " + userList.size());
        customAssertions.assertTrue(userList.size(), 10,scenario);
    }

    /**
     * A user with a specific user id.
     *
     * @param userId the user id
     */
    @Given("A user with a specific userId {string}")
    public void a_user_with_a_specific_user_id(String userId) {
        scenario.log("Build RequestSpecification");
        requestSpecification = (FilterableRequestSpecification) given()
                .pathParam("userId", userId);
    }

    /**
     * Hits get call to users api with path params.
     *
     * @throws IOException the io exception
     */
    @When("hits GET  call to users api with path params")
    public void hits_get_call_to_users_api_with_path_params() {
        requestSpecification.basePath(endpoint.getUsersEndpointWithPathParam());
        response = requestMethods.getResponse(requestSpecification, scenario);
    }

    /**
     * Validate schema of the user demographic details.
     */
    @Then("validate schema of the user demographic details")
    public void validate_schema_of_the_user_demographic_details() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/schemas/userSchema.json");
        scenario.log("Validating response schema with " + file.getName());
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));
    }


    /**
     * Validate name user name email address street phone website company name.
     *
     * @param name           the name
     * @param userName       the user name
     * @param email          the email
     * @param address_street the address street
     * @param phone          the phone
     * @param website        the website
     * @param company_name   the company name
     */
    @And("validate name {string},userName {string},email {string},address_street {string}, phone {string}, website {string},company_name {string}")
    public void validateNameUserNameEmailAddress_streetPhoneWebsiteCompany_name(String name, String userName, String email, String address_street, String phone, String website, String company_name) {
        UserDetails userObj = response.getBody().as(UserDetails.class, ObjectMapperType.GSON);
        customAssertions.assertEquals(name, userObj.getName(), scenario);
    }
}
