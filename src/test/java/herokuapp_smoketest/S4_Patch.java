package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static herokuapp_smoketest.S1_Post.id;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S4_Patch extends HerOkuAppBaseUrl {
             /*
    Given
        https://restful-booker.herokuapp.com/booking/{id}
    And
        {
        "firstname": "Necminson",
        "lastname": "Blackmad"
        }
   When
        Send the patch request
   Then
        Status code should be 200
   And
        Body should be like:
        {
                                        "firstname": "Necminson",
                                        "lastname": "Blackmad",
                                        "totalprice": 222,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2022-01-01",
                                            "checkout": "2023-01-01"
                                        },
                                        "additionalneeds": "Lunch"
                                    }
     */

    @Test
    public void patchTest() {
        //Set the URL
        spec.pathParams("1st", "booking", "2nd", id);

        // Set the expected data
        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("firstname", "Necminson");
        expectedData.put("lastname", "Blackmad");

        // Send the PATCH request and get the response
        Response response = given(spec).body(expectedData).patch("{1st}/{2nd}");
        //response.prettyPrint();

        // Do assertion
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), response.jsonPath().getString("firstname"));
        assertEquals(expectedData.get("lastname"), response.jsonPath().getString("lastname"));

    }
}
