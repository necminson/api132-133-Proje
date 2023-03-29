package requests_for_exercise;

import base_urls.RegresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.given;

public class Homework03 extends RegresInBaseUrl {
      /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */
    @Test
    public void getHm03(){
        spec.pathParams("1st","users","2nd",2);

        Response response = given(spec).when().get("{1st}/{2nd}");

        JSONObject expectedData = new JSONObject();
        JsonPath jsonPath = response.jsonPath();

        expectedData.put("statusCode",200);
        expectedData.put("contentType","application/json; charset=utf-8");
        expectedData.put("email","janet.weaver@reqres.in");
        expectedData.put("first_name","Janet");
        expectedData.put("last_name","Weaver");
        expectedData.put("text","To keep ReqRes free, contributions towards server costs are appreciated!");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.statusCode(),expectedData.get("statusCode"),"statusCode did NOT match");
        softAssert.assertEquals(response.contentType(),expectedData.get("contentType"),"contentType did NOT match");
        softAssert.assertEquals(response.jsonPath().getString("data.email"),expectedData.get("email"),"email did NOT match");
        softAssert.assertEquals(response.jsonPath().getString("data.first_name"),expectedData.get("first_name"),"first_name did NOT match");
        softAssert.assertEquals(response.jsonPath().getString("data.last_name"),expectedData.get("last_name"),"last_name did NOT match");
        softAssert.assertEquals(response.jsonPath().getString("support.text"),expectedData.get("text"),"text did NOT match");


        softAssert.assertAll();




    }

}
