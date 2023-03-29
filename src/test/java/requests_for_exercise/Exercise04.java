package requests_for_exercise;

import base_urls.HerOkuAppBaseUrl;

public class Exercise04 extends HerOkuAppBaseUrl {
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

}
