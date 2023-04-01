package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {
 /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/

    @Test
    public void post02(){
        //Set teh URL
        spec.pathParam("1st","booking");
        // Set the excepted data
        Map<String,String> bookingdatesMap = new HerOkuAppTestData().bookingdatesMapMethod("2021-09-09","2021-09-21");
        Map<String,Object> expectedData = new HerOkuAppTestData().expectedDataMethod("John","Doe",11111,true,bookingdatesMap,null);
        System.out.println("expectedData = " + expectedData);
        // Send the POST request and get the response
       Response response = given(spec).when().body(expectedData).post("{1st}");
        response.prettyPrint();
        // Set the actualData
       Map<String,Object> actualData =  response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        // Do assertions
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));


    }

}
