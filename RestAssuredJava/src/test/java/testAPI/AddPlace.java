package testAPI;
import io.restassured.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.PayLoad;

public class AddPlace {
	public static void main(String[] args) {
		// Validate if the add place API is working as expected
		/*
		 * 1. RestAssured works on three principles 
		 * a) given() - All information provided to API wraps under given method or all input details 
		 * b) when() - Submit the API and Hit, Resource and HTTP method goes in when() method. 
		 * c) then() - Validate the response 
		 * 2. Before starting first use the BaseURI.
		 */
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(PayLoad.AddPlacePayLoad()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)"));
	}

}
