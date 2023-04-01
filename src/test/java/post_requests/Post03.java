package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03 extends JsonPlaceHolderBaseUrl {
    /*
     Given
        https://jsonplaceholder.typicode.com/todos
        {
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
    public void post(){
        // Set the URL
        spec.pathParam("1st","todos");
        // Set the excepted data
      JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55,"Tidy your room",false);
       // System.out.println("expectedData = " + expectedData);
        // Send POST request and get response
       Response response = given(spec).when().body(expectedData).post("{1st}");
       // response.prettyPrint();
        // Set the actual data
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
       // System.out.println("actualData = " + actualData);

        // Do assertion
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());



    }

}
