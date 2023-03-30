package requests_for_exercise;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerOkuAppPojo.BookingDatesPojo;
import pojos.HerOkuAppPojo.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise07_Get_ByPojo extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/2332
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                    "firstname": "John",
                    "lastname": "Smith",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
}
     */

    @Test
    public void getExercise07(){

            // Set the URL
        spec.pathParams("1st","booking","2nd",886);
            // Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("John","Smith",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);
        // Send the request and get the response
         Response response= given(spec).contentType(ContentType.JSON).when().get("{1st}/{2nd}");
            //response.prettyPrint();

        // Set the actual data
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);
            // D0 assertion
                assertEquals(expectedData.getFirstname(),actualData.getFirstname());
                assertEquals(expectedData.getLastname(),actualData.getLastname());
                assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
                assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
                assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
                assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());

                assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());




    }
}
