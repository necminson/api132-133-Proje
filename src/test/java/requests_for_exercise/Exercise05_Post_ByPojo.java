package requests_for_exercise;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerOkuAppPojo.BookingDatesPojo;
import pojos.HerOkuAppPojo.BookingPojo;
import pojos.HerOkuAppPojo.HerOkuAppResponsePojo;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Exercise05_Post_ByPojo extends HerOkuAppBaseUrl {
    /*
        Given
        1)     https://restful-booker.herokuapp.com/booking/
        2)      {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-01",
                    "checkout": "2021-12-21"
                },
                "additionalneeds": "Breakfast"
            }
        When
            I send POST Request to the URL
        Then
            status code is 200
        Then
            Response body should be like that;
             {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-01",
                    "checkout": "2021-12-21"
                },
                "additionalneeds": "Breakfast"


     */
       @Test  // By Using Method
    public void postExercise04a(){
        // set the URL
        spec.pathParam("1st","booking");
        // Set the expectedData
        Map<String,String> bookingdatesMap = new HerOkuAppTestData().bookingdatesMapMethod("2021-09-01","2021-12-21");
        Map<String,Object> expectedData = new HerOkuAppTestData().expectedDataMethod("Ali","Can",999,true,bookingdatesMap,"Breakfast");
        System.out.println("expectedData = " + expectedData);
        // Post the request and get the response
        Response response = given(spec).when().body(expectedData).post("{1st}");
        //response.prettyPrint();

        // Set actual data

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        // Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"), ((Map) actualData.get("booking")).get("firstname")  );
        assertEquals(expectedData.get("lastname"), ((Map) actualData.get("booking")).get("lastname")  );
        assertEquals(expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice")  );
        assertEquals(expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid")  );
        assertEquals(expectedData.get("additionalneeds"), ((Map) actualData.get("booking")).get("additionalneeds"));
        assertEquals(bookingdatesMap.get("checkin"), ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));


    }

    @Test  // By using Pojo Class
    public void postExercise04b(){
        // set the URL
        spec.pathParam("1st","booking");
        // Set the expectedData
        BookingDatesPojo bookingdates = new BookingDatesPojo("2021-09-01","2021-12-21");
        BookingPojo expectedData = new BookingPojo("Ali","Can",999,true,bookingdates,"Breakfast");
        System.out.println("expectedData = " + expectedData);
        // Post the request and get the response
        Response response = given(spec).when().body(expectedData).post("{1st}");
        //response.prettyPrint();

        // Set actual data

        HerOkuAppResponsePojo actualData = response.as(HerOkuAppResponsePojo.class);
        System.out.println("actualData = " + actualData);

        // Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
        assertEquals(bookingdates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());

    }
}
