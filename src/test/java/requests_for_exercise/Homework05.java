package requests_for_exercise;

import base_urls.RegresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.assertEquals;

public class Homework05 extends RegresInBaseUrl {
    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */
    @Test
    public void getHm05(){

       // Set the url
        spec.pathParams("1st","unknown","2nd",3);

       // send request and get response

       Response response =  given(spec).when().get("{1st}/{2nd}");
        response.prettyPrint();

        // Assertion:
        SoftAssert softAssert = new SoftAssert();
        JsonPath jsonPath = response.jsonPath();
         //   HTTP Status Code should be 200
        softAssert.assertEquals(response.statusCode(),200,"statusCode did NOT match");

        //    Response content type is “application/json”
         softAssert.assertEquals(response.getContentType(),"application/json; charset=utf-8","ContentType did NOT match");


          //  Response body should be like;(Soft Assertion)
              softAssert.assertEquals(jsonPath.getString("data.id"),3,"id did Not match");
              softAssert.assertEquals(jsonPath.getString("data.name"),"true red","name did Not match");
              softAssert.assertEquals(jsonPath.getString("data.year"),2002,"year did Not match");
              softAssert.assertEquals(jsonPath.getString("data.color"),"#BF1932","color did Not match");
              softAssert.assertEquals(jsonPath.getString("data.pantone_value"),"19-1664","pantone_value did Not match");
              softAssert.assertEquals(jsonPath.getString("support.url"),"https://reqres.in/#support-heading","url did Not match");
              softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","text did Not match");
              softAssert.assertAll();


}


    }


