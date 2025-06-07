package complexJSON;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJSONTestNG {
	@Test
    public void sumOfAllCourses() {
        int countArraySize = 0;
        String courseName = null;
        int coursePrice = 0;
        int courseCopies = 0;
        int courseAddition = 0;
        int courseFinalAddition = 0;
        JsonPath js = new JsonPath(PayLoad.coursePrice());
        countArraySize = js.get("courses.size()");
        System.out.println(countArraySize);

        for (int i = 0; i < countArraySize; i++){
            courseName = js.get("courses["+i+"].title");
            coursePrice = js.get("courses["+i+"].price");
            courseCopies = js.get("courses["+i+"].copies");
            courseAddition = coursePrice * courseCopies;
            System.out.println("Course Name: " + courseName + "  :  " + "Course Price: " + coursePrice + "  :  "
                    + "Course Copies: " + courseCopies + " : " + "Course Total: " + courseAddition);
            courseFinalAddition = courseFinalAddition + courseAddition;
        }
        System.out.println("Final Addition---");
        System.out.println("Total: " + courseFinalAddition);
        Assert.assertEquals(courseFinalAddition,js.getInt("dashboard.purchaseAmount"));
    }
}
