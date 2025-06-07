package advanceDynamicPayload;

import org.testng.annotations.Test;

import files.ReUsablemethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticJSON {
	
	/*
	Approach: 
	1. body() accept the String
	2. Send the file to the body() and now there is method that is read file contain and change to bytes and then change bytes to 
	   String
	*/
	@Test
	public void staticJSONTest() throws IOException {
		RestAssured.baseURI = "http://216.10.245.166/";
		String response = given().header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("/Users/kunalchavan/git/RestAssuredJava/RestAssuredJava/src/main/resources/files/AddBook.json"))))
				.when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).header("Server","Apache").extract().response().asString();
		System.out.println(response);
        JsonPath js = ReUsablemethods.rawToJSON(response);
        String bookID = js.get("ID");
        System.out.println(bookID);
		
	}

}
