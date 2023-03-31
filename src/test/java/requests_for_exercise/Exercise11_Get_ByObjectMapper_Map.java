package requests_for_exercise;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import util.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise11_Get_ByObjectMapper_Map extends JsonPlaceHolderBaseUrl {
/*
         Given
           1) https://jsonplaceholder.typicode.com/todos/198

        When
            I send GET Request to the Url

        Then
            Status code is 200
        And{
                                    "userId": 10,
                                    "id": 198,
                                    "title": "quis eius est sint explicabo",
                                    "completed": true
                                    }
            response body is like
     */
    @Test
    public void getExercise11(){
        // Set the URL
        spec.pathParams("1st","todos","2nd",198);
        // Set the expected data
        String json = JsonPlaceHolderTestData.expectedDataInString(10,"quis eius est sint explicabo",true);
       Map<String,Object> expectedData = ObjectMapperUtils.convertJsonToJava(json, HashMap.class);
        System.out.println("expectedData = " + expectedData);
        // Send The GET request and get the response
       Response response = given(spec).get("{1st}/{2nd}");
       response.prettyPrint();

        // Set the actual data
          Map<String,Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        // Do assertion

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));




    }

}
