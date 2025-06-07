package advanceDynamicPayload;
import io.restassured.*;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.BookAPIPayload;
import files.ReUsablemethods;

public class DataProviderExample {
	 @Test(dataProvider = "BooksData")
	    public void addBook(String parameter1, String parameter2) {
	        RestAssured.baseURI = "http://216.10.245.166/";
	        String response = given().header("Content-Type", "application/json").body(BookAPIPayload.addBookJSON(parameter1,parameter2))
	                .when().post("Library/Addbook.php")
	                .then().log().all().assertThat().statusCode(200).header("Server","Apache").extract().response().asString();
	        System.out.println(response);
	        JsonPath js = ReUsablemethods.rawToJSON(response);
	        String bookID = js.get("ID");
	        System.out.println(bookID);

	        // Best practice to continue automation flow add the book and then delete it at that time before execution complete.
	        // This will clean the book and add it next time without fail
	    }

	    @DataProvider(name="BooksData")
	    public Object[][] getData(){
	        // Array -> Collection of elements
	        // Multi-Dimentionsal Array -> Collection of Arrays
	        Object array [][] = new Object[][]{{"Kunal","Chavan"},{"KC","CK"},{"Apple","iPhone"}};
	        return array;
	    }
}
