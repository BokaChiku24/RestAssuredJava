package oAuth;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class OAuthTest {
	String accessToken = null;
	@Test
	public void getToken() {
		//RestAssured.baseURI = "";
	String response =	given().log().all().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
	System.out.println(response);
	
	JsonPath path = new JsonPath(response);
	accessToken = path.getString("access_token");
	
	}
	
	@Test(dependsOnMethods = {"getToken"})
	public void getCourseDetails() {
		String response = given().log().all().queryParam("access_token", accessToken)
				.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		System.out.println("");
		System.out.println(response);

	}

}
