package stepdefs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddLocation;
import pojo.Location;
import resorces.APIResources;
import resorces.TestDataBuild;
import resorces.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class StepDefinations extends Utils{
	RequestSpecification request;
	ResponseSpecification res;
	Response placeAdded;
	TestDataBuild addplace=new TestDataBuild();
	static String placeId;
	
	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException 
	{				
		request = given().spec(requestSpecification())
				.body(addplace.addPlacePayload(name,address,language));  //body takes object	
		
	}

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" https request$")
    public void user_calls_something_with_something_https_request(String resource, String method)
	{
		APIResources resouceValue = APIResources.valueOf(resource);
		resouceValue.getResourceValue();  //Get the resource URL from ENUM class
		
		res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
		
		if(method.equalsIgnoreCase("POST")) {
			placeAdded = request.when().post(resouceValue.getResourceValue()) //in post method give the resource value
			.then().log().all().spec(res).extract().response();
		}
		else if(method.equalsIgnoreCase("Get"))
		{	placeAdded = request.when().get(resouceValue.getResourceValue()) //in post method give the resource value
			.then().log().all().spec(res).extract().response();
		}
		else if(method.equalsIgnoreCase("Delete"))
		{	placeAdded = request.when().delete(resouceValue.getResourceValue()) //in post method give the resource value
			.then().log().all().spec(res).extract().response();
		}
		else if(method.equalsIgnoreCase("Put"))
		{	placeAdded = request.when().put(resouceValue.getResourceValue()) //in post method give the resource value
			.then().log().all().spec(res).extract().response();
		}		
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		System.out.println("Response code : "+ placeAdded.getStatusCode());
		assertEquals(placeAdded.getStatusCode(),200);
		
	  
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String value, String string2) {
		
	    System.out.println(value + " has value "+string2);
	    assertEquals(getJsonPath(placeAdded,value),string2);
	}
	
	@Then("Verify the place added in maps belongs to {string} using {string}")
	public void verify_the_place_added_in_maps_belongs_to_using(String name, String resource) throws IOException {
		
		placeId= getJsonPath(placeAdded,"place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_something_with_something_https_request(resource, "GET");
		String placeName = getJsonPath(placeAdded,"name");
		assertEquals(placeName,name);
	}


	@Given("Delete place pay load")
	public void delete_place_pay_load() throws IOException {
	    request = given().spec(requestSpecification()).body(addplace.deletePlacePayload(placeId));
	}





}