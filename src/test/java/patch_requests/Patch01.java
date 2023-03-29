package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
/*
      Given
          1) https://jsonplaceholder.typicode.com/todos/198
          2) {
               "title": "Read the books"
             }
      When
           I send PATCH Request to the Url
      Then
            Status code is 200
            And response body is like   {
                                      "userId": 10,
                                      "title": "Read the books",
                                      "completed": true,
                                      "id": 198
                                     }
   */

    @Test
    public void patch01(){
        // Set the Url
        spec.pathParams("1st","todos","2nd",198);
        // Set expected data
        Map<String,Object> expectedData = new JsonPlaceHolderTestData().expectedDataMapMethod(null,"Read the books",null);
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
       Response response =  given(spec).body(expectedData).patch("{1st}/{2nd}");
       // response.prettyPrint();

       // D0 assertion
       Map<String,Object> actualData =  response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("title"),actualData.get("title"));
        // if you want to assert whole body
        assertEquals(10,actualData.get("userId"));
        assertEquals(true,actualData.get("completed"));
        assertEquals(198,actualData.get("id"));

    }


}
