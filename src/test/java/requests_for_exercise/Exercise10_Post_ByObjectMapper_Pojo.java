package requests_for_exercise;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import util.ObjectMapperUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise10_Post_ByObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
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
    public void postExercise10ObjectMapper() throws IOException {
        // Set the URL
        spec.pathParam("1st","todos");

        // Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response

       Response response = given(spec).when().contentType(ContentType.JSON).body(expectedData).post("{1st}");
       response.prettyPrint();

       // Set the actual data

        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);
        // Do assertion

            assertEquals(201,response.statusCode());
            assertEquals(expectedData.getUserId(),actualData.getUserId());
            assertEquals(expectedData.getTitle(),actualData.getTitle());
            assertEquals(expectedData.getCompleted(),actualData.getCompleted());





    }



}
