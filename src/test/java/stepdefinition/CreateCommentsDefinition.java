package stepdefinition;

import assertions.SoftAssertions;
import com.google.gson.Gson;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import models.requestmodels.comments.PostComment;
import org.testng.asserts.SoftAssert;
import utils.EndPoints;
import utils.RequestMethods;

import java.io.File;

import static io.restassured.RestAssured.given;


public class CreateCommentsDefinition {

    Scenario scenario;
    Response response;
    Gson gson = new Gson();
    EndPoints endpoint = new EndPoints();
    SoftAssert softAssert = new SoftAssert();
    FilterableRequestSpecification requestSpecification;
    RequestMethods requestMethods = new RequestMethods();
    SoftAssertions customAssertions = new SoftAssertions(softAssert);

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

    @Given("A user with details as postId {string},id {string},name {string},email {string}, body {string}")
    public void a_user_with_a_specific_post(String postId, String id, String name, String email, String body) {
        PostComment postComment = new PostComment();
        postComment.setPostId(postId);
        postComment.setEmail(email);
        postComment.setId(id);
        postComment.setName(name);
        postComment.setBody(body);

        requestSpecification = (FilterableRequestSpecification) given()
                .body(gson.toJson(postComment));
    }

    @When("hits POST call to comments api with request payload")
    public void hits_post_call_to_users_api_with_request_payload() {
        requestSpecification.basePath(endpoint.getCommentsEndpoint());
        scenario.log("Request Payload:  " + requestSpecification.<String>getBody());
        response = requestMethods.postRequest(requestSpecification, scenario);

    }

    @Then("validate status code after posting a comment")
    public void validate_status_code_after_posting_a_comment() {
        scenario.log(response.getStatusLine());
        customAssertions.assertTrue(response.getStatusCode(), 201, scenario);
    }

    @Then("validate schema of the json response after posting a comment")
    public void validate_schema_of_the_json_response_after_posting_a_comment() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/schemas/postCommentSchema.json");
        scenario.log("Validating response schema with " + file.getName());
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));
    }

    @Then("validate postId {string},id {string},name {string},email {string}, body {string}")
    public void validate_postId_id_name_email_body(String postId, String id, String name, String email, String body) {
        PostComment postComment = gson.fromJson(response.getBody().asString(), PostComment.class);
        customAssertions.assertEquals(String.valueOf(postId), String.valueOf(postComment.getPostId()), scenario);
        // This id is autogenerated from List of users and is intentionally failing to demonstrate soft assertion
        // can be fixed and validated using GET api call and fetching max(id)
        customAssertions.assertTrue(Integer.valueOf(id), Integer.valueOf(postComment.getId()), scenario);
        customAssertions.assertEquals(name, postComment.getName(), scenario);
        customAssertions.assertEquals(email, postComment.getEmail(), scenario);
        customAssertions.assertEquals(body, postComment.getBody(), scenario);
    }

    @After
    public void afterEveryScenario() {
        softAssert.assertAll();
    }
}
