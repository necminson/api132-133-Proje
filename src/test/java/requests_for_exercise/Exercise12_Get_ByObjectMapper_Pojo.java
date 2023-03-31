package requests_for_exercise;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import util.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise12_Get_ByObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
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
    public void getExercise12(){
        // Set the URL
        spec.pathParams("1st","todos","2nd",198);
        // Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);
       // System.out.println("expectedData = " + expectedData);
        // Send the GET request and get the response
        Response response = given(spec).get("{1st}/{2nd}");
       // response.prettyPrint();
        // Set the actual data
        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
      //  System.out.println("actualData = " + actualData);
        // Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());




    }



}
