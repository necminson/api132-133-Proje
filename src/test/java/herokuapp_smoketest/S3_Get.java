package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerOkuAppPojo.BookingDatesPojo;
import pojos.HerOkuAppPojo.BookingPojo;
import util.ObjectMapperUtils;

import static herokuapp_smoketest.S1_Post.id;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S3_Get extends HerOkuAppBaseUrl {
    /*
   Given
       https://restful-booker.herokuapp.com/booking/{id}
   When
       Send the get request
   Then
       Status Code should be 200
   And
       Response body should be

                  Given
                            "https://restful-booker.herokuapp.com/booking/{id}
                  And

                                    {
                                       "firstname": "Ali",
                                       "lastname": "Can",
                                       "totalprice": 222,
                                       "depositpaid": true,
                                       "bookingdates": {
                                           "checkin": "2022-01-01",
                                           "checkout": "2023-01-01"
                                       },
                                       "additionalneeds": "Lunch"
                                   }

                   When
                           User send PUT request to the URL
                   Then
                           Status code should be 200
                   And
                           Response body should be like
                              {
                                       "firstname": "Ali",
                                       "lastname": "Can",
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
    public void getTest() {
        // Set the URL
        spec.pathParams("1st", "booking", "2nd", id);

        // Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2022-01-01", "2023-01-01");
        BookingPojo expectedData = new BookingPojo("Ali", "Can", 222, true, bookingDates, "Lunch");
        // System.out.println("expectedData = " + expectedData);

        // Send the GET request and get the response
        Response response = given(spec).get("{1st}/{2nd}");
        // response.prettyPrint();

        // Set the actual data
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);
        // System.out.println("actualData = " + actualData);

        // Do the assertion
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }


}


