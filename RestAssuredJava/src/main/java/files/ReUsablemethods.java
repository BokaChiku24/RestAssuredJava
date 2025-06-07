package files;

import io.restassured.path.json.JsonPath;

public class ReUsablemethods {
	
	  public static JsonPath rawToJSON(String response){
	        JsonPath js = new JsonPath(response);
	        return js;
	    }

}
