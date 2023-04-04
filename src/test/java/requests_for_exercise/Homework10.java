package requests_for_exercise;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Homework10 extends AutomationExerciseBaseUrl {
    //Homework10:

    /*
        Given
            https://automationexercise.com/api/productsList
        When
            User sends Get request
            Note: use prettyPrint like: response.jsonPath().prettyPrint()
        Then
            Assert that number of "Women" usertype is 12

    */
    @Test
    public void getHm10() {
        // Set the URL
        spec.pathParam("1st", "productsList");

        // Send the GET request and get the response

        Response response = given(spec).when().get("{1st}");
        response.jsonPath().prettyPrint();

        //  Do assertion
        List<String> ListOfWomen = response.jsonPath().getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        System.out.println("ListOfWomen = " + ListOfWomen);
        int numOfWomen = response.jsonPath().getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype").size();
        System.out.println("numOfWomen = " + numOfWomen);

        assertEquals(200, response.statusCode());
        assertEquals(12, numOfWomen);
    }

}
