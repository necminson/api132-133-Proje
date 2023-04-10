package requests_for_exercise;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Exercise14_Delete_ByObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
    /*
    Given
            https://jsonplaceholder.typicode.com/todos/23
        When
             I send DELETE Request to the URL
        Then
             Status Code should be 200
        And
            Response body is {}
     */
    @Test
    public void delete01() {
        // Set the URL
        spec.pathParams("1st", "todos", "2nd", 23);

        // Set the expectedData
        String expectedData = "{}";
        // System.out.println("expectedData = " + expectedData);

        // Send the delete request and get the response
        Response response = given(spec).delete("{1st}/{2nd}");

        //Set the actual data
        String actualData = response.asString();
        // System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData, actualData);

        // 2.way
        Map<String, String> expectedData02 = new HashMap<>();
        Map<String, String> actualData02 = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(expectedData02,actualData02);

        // 3.way // After creating actualData02 as in 2nn way
        assertTrue(actualData02.isEmpty());


    }


}
