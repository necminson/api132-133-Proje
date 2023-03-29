package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "userId": 21,
             "title": "Wash the dishes",
             "completed": false
           }
    When
I send PUT Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 21,
                   "title": "Wash the dishes",
                   "completed": false
                  }
 */
   @Test
   public void put01(){
       // set the url
       spec.pathParams("first","todos","second",198);
       // set the expected method
    Map<String,Object> expectedData = new JsonPlaceHolderTestData().
            expectedDataMapMethod(21,"Wash the dishes",false);

       System.out.println("expectedData = " + expectedData);

       // send the request and get the response

       Response response = given(spec).body(expectedData).put("{first}/{second}");
       response.prettyPrint();
        // Do assertion
       Map<String,Object> actualData = response.as(HashMap.class);
       System.out.println("actualData = " + actualData);
       assertEquals(expectedData.get("completed"),actualData.get("completed"));
       assertEquals(expectedData.get("title"),actualData.get("title"));
       assertEquals(expectedData.get("userId"),actualData.get("userId"));

   }


}
