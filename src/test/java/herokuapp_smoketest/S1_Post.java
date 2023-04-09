package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerOkuAppPojo.BookingDatesPojo;
import pojos.HerOkuAppPojo.BookingPojo;
import pojos.HerOkuAppPojo.HerOkuAppResponsePojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S1_Post extends HerOkuAppBaseUrl {
/*
       Given
                             "https://restful-booker.herokuapp.com/booking
                    And
                             {
                                        "firstname" : "Veli",
                                        "lastname" : "Yılmaz",
                                        "totalprice" : 111,
                                        "depositpaid" : true,
                                        "bookingdates" : {
                                            "checkin" : "2022-01-01",
                                            "checkout" : "2023-01-01"
                                        },
                                        "additionalneeds" : "Breakfast"
                                    }
                    When
                            User send POST request to the URL
                    Then
                            Status code should be 200
                    And
                            Response body should be like
                                {
                                    "bookingid": 3539,
                                    "booking": {
                                        "firstname": "Veli",
                                        "lastname": "Yılmaz",
                                        "totalprice": 111,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2022-01-01",
                                            "checkout": "2023-01-01"
                                        },
                                        "additionalneeds": "Breakfast"
                                    }

                                   }

 */
       public static int id;
    @Test
    public void postTest() {
        // Set the URL
        spec.pathParam("1st", "booking");
        // Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2022-01-01", "2023-01-01");
        BookingPojo expectedData = new BookingPojo("Veli", "Yılmaz", 111, true, bookingDates, "Breakfast");

       // System.out.println("expectedData = " + expectedData);
        // Send the POST request and get the response
        Response response = given(spec).body(expectedData).post("{1st}");
         //response.prettyPrint();

        // Set the actual data
        HerOkuAppResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HerOkuAppResponsePojo.class);
       // System.out.println("actualData = " + actualData);

        // do assertion

        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());

        id= actualData.getBookingid();
    }
}
