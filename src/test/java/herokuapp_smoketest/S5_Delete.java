package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S1_Post.id;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S5_Delete extends HerOkuAppBaseUrl {
    /*
                   Given
                             "https://restful-booker.herokuapp.com/booking/{id}


                    When
                            User send DELETE request to the URL
                    Then
                            Status code should be 201
                    And
                            Body should be "created"



    */

    @Test
    public void deleteTest() {
        // Set the URL
        spec.pathParams("1st", "booking", "2nd", id);

        // Set the expected data
        String expectedData = "Created";

        // Send the DELETE request and get the response
        Response response = given(spec).when().delete("{1st}/{2nd}");
        //response.prettyPrint();

        // Set the actual data
        String actualData = response.asString();

        // Do assertion
        assertEquals(201, response.statusCode());
        assertEquals(expectedData, actualData);

    }


}
