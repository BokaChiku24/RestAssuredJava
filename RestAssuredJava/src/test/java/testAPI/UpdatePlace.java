package testAPI;
import io.restassured.*;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.PayLoad;
public class UpdatePlace {
	public static void main(String[] args) {
        String response = null;
        String placeID = null;
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(PayLoad.AddPlacePayLoad()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response); // For parsing JSON
        placeID = js.getString("place_id");
        System.out.println(placeID);

        // Update the place
        String newAddress = "New York 24 Street..";
        given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

        // Get the place
        String newAddressResponse = null;
        newAddressResponse = given().queryParam("key", "qaclick123").queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json").then().log().all().statusCode(200).extract().response()
                .asString();
                //.body("address",equalTo(newAddress));
        JsonPath js1 = new JsonPath(newAddressResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);
    }
}
