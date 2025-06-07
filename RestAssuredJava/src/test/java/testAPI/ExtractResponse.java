package testAPI;
import io.restassured.*;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.PayLoad;
import files.ReUsablemethods;

public class ExtractResponse {
	  public static void main(String[] args) {
	        String response = null;
	        String placeID = null;
	        RestAssured.baseURI = "https://rahulshettyacademy.com/";
	        response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
	                .body(PayLoad.AddPlacePayLoad()).when().post("maps/api/place/add/json")
	                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
	                .header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();
	        System.out.println(response);
	        JsonPath js = ReUsablemethods.rawToJSON(response); // For parsing JSON
	        placeID = js.getString("place_id");
	        System.out.println(placeID);
	    }
}
