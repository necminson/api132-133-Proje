package requests_for_exercise;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise01_Get_ByMap extends GoRestBaseUrl {
        /*
    Given
       https://gorest.co.in/public/v1/users/3879
    When
         User send a GET request to the URL
    Then
        HTTP Status Code should be 200

    And
        Response body should be like;
     {
            "id": 3879,
            "name": "Jyotis Nehru",
            "email": "jyotis_nehru@watsica.co",
            "gender": "male",
            "status": "inactive"
        },
    */
    @Test
    public void getExercise01(){
        // Set the url
        spec.pathParams("1st","users","2nd",3879);
        // Set the Expected Data

        Map<String,String> expectedData = new GoRestTestData().dataMapMethod("Jyotis Nehru","jyotis_nehru@watsica.co","male","inactive");

        System.out.println("expectedData = " + expectedData);

        // send request and get the response
       Response response = given(spec).get("{1st}/{2nd}");
      //  response.prettyPrint();





        // set the actual data

       Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);


        // Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("name"),((Map)actualData.get("data")).get("name").toString());
        assertEquals(expectedData.get("email"),((Map)actualData.get("data")).get("email").toString());
        assertEquals(expectedData.get("gender"),((Map)actualData.get("data")).get("gender").toString());
        assertEquals(expectedData.get("status"),((Map)actualData.get("data")).get("status").toString());




    }



    }








