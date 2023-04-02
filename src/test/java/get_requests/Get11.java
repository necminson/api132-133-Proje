package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerOkuAppPojo.BookingDatesPojo;
import pojos.HerOkuAppPojo.BookingPojo;
import pojos.HerOkuAppPojo.HerOkuAppResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get11 extends HerOkuAppBaseUrl {
/*
    Given
        https://restful-booker.herokuapp.com/booking/49
    When
        I send GET Request to the url
    Then
        Response body should be like that;
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
    public void get11(){
        // Set the URL
        spec.pathParams("1st","booking","2nd",77);
        // Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jane","Doe",111,true,bookingDatesPojo,"Extra pillows please");
        System.out.println("expectedData = " + expectedData);
        // Send The GET request and get the response
           Response response = given(spec).get("{1st}/{2nd}");
        //   response.prettyPrint();

        // Set the actual data
        BookingPojo actualData  = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);
        // Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());





    }


}
