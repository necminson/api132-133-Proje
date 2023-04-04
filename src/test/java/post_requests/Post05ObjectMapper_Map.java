package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import util.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static util.ObjectMapperUtils.convertJsonToJava;

public class Post05ObjectMapper_Map extends JsonPlaceHolderBaseUrl {
     /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2) {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
          }


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
    public void post05(){
     // Set the url
     spec.pathParam("1st","todos");
     // Set the expected data

    Map<String,Object> expectedData = new JsonPlaceHolderTestData().expectedDataMapMethod(55,"Tidy your room",false);
     System.out.println("expectedData = " + expectedData);

     // Send the POST request and get the response
     Response response = given(spec).body(expectedData).post("{1st}");
     response.prettyPrint();

     // set the actual data
      Map<String,Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
     // Do assertion

     assertEquals(201,response.statusCode());
     assertEquals(expectedData.get("userId"),actualData.get("userId"));
     assertEquals(expectedData.get("title"),actualData.get("title"));
     assertEquals(expectedData.get("completed"),actualData.get("completed"));

 }




}
