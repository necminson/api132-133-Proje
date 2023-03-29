package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/32
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }
     */
    @Test
    public void get06() {
        //Set the URL
        spec.pathParams("first", "booking", "second", 15);

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st Way:
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Jane"),
                        "lastname", equalTo("Doe"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Extra pillows please"));

        //2nd Way: We will use JsonPath class
        JsonPath jsonPath = response.jsonPath();//We can extract the data from body to outside with JsonPath

        //Hard Assertion
        assertEquals("Jane", jsonPath.getString("firstname"));
        assertEquals("Doe", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Extra pillows please", jsonPath.getString("additionalneeds"));

        //Soft Assertion
        //To do Soft Assertion follow these 3 steps:
        //1st Step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();

        //2nd Step: Do assertion by using SoftAssert object

        softAssert.assertEquals(jsonPath.getString("firstname"), "Jane", "firstname did NOT match");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Doe", "lastname did NOT match");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111, "totalprice did NOT match");
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"), true, "depositpaid did NOT match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "checkin did NOT match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01", "checkout did NOT match");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "Extra pillows please", "additionalneeds did NOT match");

        //3rd Step: Use assertAll() method.
        softAssert.assertAll();

    }


}