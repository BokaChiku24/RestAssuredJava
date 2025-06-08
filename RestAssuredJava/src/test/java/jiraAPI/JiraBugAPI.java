package jiraAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.File;

public class JiraBugAPI {

	String id = null;
	
	@Test
	public void createBug() {
		RestAssured.baseURI = "https://kunal24chavan.atlassian.net/";
		String response = given().header("Accept","application/json").header("Content-Type","application/json").header("Authorization","Basic a3VuYWwyNGNoYXZhbkBnbWFpbC5jb206QVRBVFQzeEZmR0Ywc3FYV3R3WTAyVWdNeWIwOHExVDVnS2E3TG9ZOHR6V1liYmNaYjdYZklKelV4TUlTd1Q1bXNJdzV6ME5wc3lmNWpKNEF2WEtGSGlBUWs5VGFQVjc2WUswaHUtQ1hjdzYzTkE4ak1yTGpJNTZXd3ZGaTAzaDVadU1sa2RYX3ZIY0h4dDFlemlGMDVrVUh5RGU4U21PWFFHVGlSQzBvY1U0SUFTdlZLYm13RFZRPTE0REQ3NEZC")
		.body("{\n"
				+ "    \"fields\": {\n"
				+ "       \"project\":\n"
				+ "       {\n"
				+ "          \"key\": \"SCRUM\"\n"
				+ "       },\n"
				+ "       \"summary\": \"Write your summary here\",\n"
				+ "       \"issuetype\": {\n"
				+ "          \"name\": \"Bug\"\n"
				+ "       }\n"
				+ "   }\n"
				+ "}")
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		id = js.getString("id");
		System.out.println(id);
	}
	
	@Test(dependsOnMethods = {"createBug"})
	public void addAttachment() {
		RestAssured.baseURI = "https://kunal24chavan.atlassian.net/";
		given().header("X-Atlassian-Token","no-check").pathParam("key", id)
		.header("Authorization","Basic a3VuYWwyNGNoYXZhbkBnbWFpbC5jb206QVRBVFQzeEZmR0Ywc3FYV3R3WTAyVWdNeWIwOHExVDVnS2E3TG9ZOHR6V1liYmNaYjdYZklKelV4TUlTd1Q1bXNJdzV6ME5wc3lmNWpKNEF2WEtGSGlBUWs5VGFQVjc2WUswaHUtQ1hjdzYzTkE4ak1yTGpJNTZXd3ZGaTAzaDVadU1sa2RYX3ZIY0h4dDFlemlGMDVrVUh5RGU4U21PWFFHVGlSQzBvY1U0SUFTdlZLYm13RFZRPTE0REQ3NEZC")
		.multiPart("file",new File("/Users/kunalchavan/Desktop/KC/KC 2024 Framework/Latest Select/Select/Screenshots/FIREFOX/AddBankTest_addBank.png"))
		.when().post("rest/api/3/issue/{key}/attachments")
		.then().log().all().statusCode(200);
	}
	
	@Test(dependsOnMethods = {"addAttachment"})
	public void getIssueDetails() {
		RestAssured.baseURI = "https://kunal24chavan.atlassian.net/";
		given().header("Accept","application/json")
		.header("Authorization","Basic a3VuYWwyNGNoYXZhbkBnbWFpbC5jb206QVRBVFQzeEZmR0Ywc3FYV3R3WTAyVWdNeWIwOHExVDVnS2E3TG9ZOHR6V1liYmNaYjdYZklKelV4TUlTd1Q1bXNJdzV6ME5wc3lmNWpKNEF2WEtGSGlBUWs5VGFQVjc2WUswaHUtQ1hjdzYzTkE4ak1yTGpJNTZXd3ZGaTAzaDVadU1sa2RYX3ZIY0h4dDFlemlGMDVrVUh5RGU4U21PWFFHVGlSQzBvY1U0SUFTdlZLYm13RFZRPTE0REQ3NEZC")
		.pathParam("key", id)
		.when().get("rest/api/2/issue/{key}")
		.then().log().all().assertThat().statusCode(200);
	}
}
