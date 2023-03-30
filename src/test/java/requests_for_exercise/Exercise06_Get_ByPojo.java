package requests_for_exercise;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerOkuAppPojo.BookingDatesPojo;
import pojos.HerOkuAppPojo.BookingPojo;


import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Exercise06_Get_ByPojo extends HerOkuAppBaseUrl {
    /*
      Given
          https://restful-booker.herokuapp.com/booking/391
     When
            I send GET Request to the URL
        Then
            status code is 200
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
    public void getExercise06(){
        //Set the URL
        spec.pathParams("1st","booking","2nd",886);
        // Set The expected Data
        BookingDatesPojo bookingDatesPojo =new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("John","Smith",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);
        // Send request and get response
           Response response = given(spec).when().get("{1st}/{2nd}");
          response.prettyPrint();
        // Set the Actual Data
             BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        // Do assertion

            assertEquals(expectedData.getFirstname(),actualData.getFirstname());
            assertEquals(expectedData.getLastname(),actualData.getLastname());
            assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
            assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
            assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
            assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
            assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());



    }



}
