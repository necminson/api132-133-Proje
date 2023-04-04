package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
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
    public void post05() {
        // Set the url
        spec.pathParam("1st", "todos");
        // Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55, "Tidy your room", true);
        System.out.println("expectedData = " + expectedData);

        // Send the POST request and get the response

        Response response = given(spec).body(expectedData).post("{1st}");
        response.prettyPrint();

        // Set the actual Data
        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);

        // Do assertion
        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());

    }
}
