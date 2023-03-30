package requests_for_exercise;

import base_urls.GoRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GorestDataPojo;
import pojos.GorestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise08_Get_ByPojo extends GoRestBaseUrl {
            /*
    Given
       https://gorest.co.in/public/v1/users/3259
    When
         User send a GET request to the URL
    Then
         Status Code should be 200

    And
        Response body should be like;
     {
    "meta": null,
    "data": {
        "id": 3259,
        "name": "Miss Ekaaksh Sinha",
        "email": "ekaaksh_sinha_miss@abernathy.io",
        "gender": "male",
        "status": "inactive"
    }
}
    */
    @Test
    public void getExercise08(){
        //Set the URL
        spec.pathParams("1st","users","2nd",3259);
        // Set the expectedData
        GorestDataPojo gorestDataPojo= new GorestDataPojo("Miss Ekaaksh Sinha","ekaaksh_sinha_miss@abernathy.io","male","inactive");
        GorestPojo expectedData = new GorestPojo(null,gorestDataPojo);
        System.out.println("expectedData = " + expectedData);
        //Send the request and get the response
        Response response = given(spec).contentType(ContentType.JSON).when().get("{1st}/{2nd}");
        response.prettyPrint();
        // Set the actual data
       GorestPojo actualData = response.as(GorestPojo.class);
        System.out.println("actualData = " + actualData);
        // Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(gorestDataPojo.getName(),actualData.getData().getName());
        assertEquals(gorestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(gorestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(gorestDataPojo.getStatus(),actualData.getData().getStatus());
        assertEquals(expectedData.getMeta(),actualData.getMeta());

    }




}
