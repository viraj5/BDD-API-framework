package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefination extends Utils {
    RequestSpecification res;
    Response response;
    static String strplace_id;
    TestDataBuild testdata = new TestDataBuild();

    @Given("Add Place Payload with {string}{string}{string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
         res = given().spec(requestSpecification()).body(testdata.addPlacePayload(name,language,address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource,String method) {
        APIresources resourceapi=APIresources.valueOf(resource);
        if(method.equalsIgnoreCase("POST"))
        response = res.when().post(resourceapi.getResource());
        else if(method.equalsIgnoreCase("GET"))
        response = res.when().get(resourceapi.getResource());

    }
    @Then("the api call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(long expectedvalue) {
        Assert.assertEquals(response.getStatusCode(),expectedvalue);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyvalue, String expectedvalue) {
         Assert.assertEquals(getJsonPath(response,keyvalue),expectedvalue);

    }
    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String expectedname, String resource) throws IOException {
        strplace_id = getJsonPath(response,"place_id");
        res = given().spec(requestSpecification().queryParam("place_id",strplace_id));
        user_calls_with_post_http_request(resource,"GET");
        String actualname = getJsonPath(response,"name");
        Assert.assertEquals(actualname,expectedname);
    }
    @Given("DeletePlace payload")
    public void deleteplace_payload() throws IOException {
        res=given().spec(requestSpecification()).body(testdata.getDeletePayload(strplace_id));

    }

}
