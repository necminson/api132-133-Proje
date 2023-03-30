package requests_for_exercise;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise02_Post_ByMethod extends JsonPlaceHolderBaseUrl {
/*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url

        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void postExercise02(){
        // Set the url
        spec.pathParam("1st","todos");
        // set the expected data
        Map<String,Object> expectedData = new JsonPlaceHolderTestData().expectedDataMapMethod(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);
        // Send the request get the response
        Response response = given(spec).when().body(expectedData).post("{1st}");
       // response.prettyPrint();

        // set the actual data
       Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        // Do assertion

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));




    }

}
