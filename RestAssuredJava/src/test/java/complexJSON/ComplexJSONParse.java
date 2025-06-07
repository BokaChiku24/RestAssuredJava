package complexJSON;

import org.testng.Assert;

import files.PayLoad;
import files.ReUsablemethods;
import io.restassured.path.json.JsonPath;

public class ComplexJSONParse {
	public static void main(String[] args) {
        JsonPath path = ReUsablemethods.rawToJSON(PayLoad.coursePrice());
        /*
        PayLoad.coursePrice(): It is mocked response. It is not provided by any API. 
        It is just dummy response to complete our automation test. It is also called as mocked JASON.
        To process automation without API we should now at least contract / or how API response will come.
         */
        String coursePrice = path.getString("dashboard.purchaseAmount");
        System.out.println(coursePrice);

        System.out.println("Test Case 1: Print number of courses return by API");
        int courseCount = path.getInt("courses.size()"); // size() is a method in JSON path to get the count
        // size() method only applicable for arrays
        System.out.println(courseCount);

        System.out.println("*********************************");
        System.out.println("Test Case 2: Print purchase amount");
        int courseTotalAmount = path.getInt("dashboard.purchaseAmount");
        System.out.println(courseTotalAmount);

        System.out.println("*********************************");
        System.out.println("Test Case 3: Print Title of the First Course");
        String firstTitle = path.getString("courses[0].title"); // To select the index of the array use arrayName[]
        System.out.println(firstTitle);

        System.out.println("*********************************");
        System.out.println("Test Case 4: Print Title of the Last Course");
        String lastTitle = path.getString("courses[4].title");
        /*
        If the index number is invalid then it will print 'null' e.g. "courses[4].title"
         */
        System.out.println(lastTitle);

        String lastTitle2 = path.getString("courses[3].title");
        System.out.println(lastTitle2);

        System.out.println("*********************************");
        System.out.println("Test Case 5: Print all course name and their prices");
        // There is an dynamic array in the response i.e. course name will change / value will change
        // Use the for loop to handle this
        String courseName = null;
        int coursePriceFinal = 0;
        int courseCopies = 0;
        int totalAdditionCourse = 0;
        int sum = 0;
        for (int i = 0; i < courseCount; i++) {
            courseName = path.get("courses[" + i + "].title");
            coursePriceFinal = path.get("courses[" + i + "].price");
            System.out.println("Course Name is -> " + courseName + " : " + "Course Price is -> " + coursePriceFinal);
        }

        System.out.println("*********************************");
        System.out.println("Test Case 6: Print number of copies sold by course RPA");
        for (int i = 0; i < courseCount; i++) {
            courseName = path.get("courses[" + i + "].title");
            if (courseName.equalsIgnoreCase("RPA")) {
                courseCopies = path.get("courses[" + i + "].copies");
                System.out.println("Course copies sold by RPA: " + courseCopies);
                break;
            }
        }

        System.out.println("*********************************");
        System.out.println("Test Case 7: Verify sum of all course price matches with purchase amount");
        for (int i = 0; i < courseCount; i++) {
            courseName = path.get("courses[" + i + "].title");
            coursePriceFinal = path.get("courses[" + i + "].price");
            courseCopies = path.get("courses[" + i + "].copies");
            totalAdditionCourse = coursePriceFinal * courseCopies;
            System.out.println("Addition of the course: " + totalAdditionCourse);
            sum = totalAdditionCourse + sum;
        }
        System.out.println("Final Addition of the course: " + sum);
        Assert.assertEquals(path.getInt("dashboard.purchaseAmount"),sum);

    }
}
