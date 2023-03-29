package requests_for_exercise;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework04 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Josh&lastname=Allen
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Josh" and lastname is "Allen"

 */

    @Test
    public void get04(){
        // Set the url
       spec.pathParam("1st","booking").queryParams("firstname","Josh","lastname","Allen");
        // Send request and get response
        Response response = given(spec).when().get("{1st}");
        //response.prettyPrint();
        // Assertion
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("bookingid"));


    }
}
