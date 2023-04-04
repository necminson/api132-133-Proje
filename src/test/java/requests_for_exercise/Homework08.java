package requests_for_exercise;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Homework08 extends PetStoreBaseUrl {
    //Homework8:


   /*
    Type automation code to create a 'user' by using "https://petstore.swagger.io/"" documantation.
    */
     /*
        Given
            1) https://petstore.swagger.io/v2/user
            2) {
                  "username": "JohnDoe",
                  "firstName": "John",
                  "lastName": "Doe",
                  "email": "john@doe.com",
                  "password": "1234",
                  "phone": "1234",
                  "userStatus": 123
                }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "code": 200,
                                                "type": "unknown",
                                                "message": "6874988058"
                                             }
     */
   @Test
   public void postHm08() {
       spec.pathParam("first", "user");
       Map<String, Object> expectedData = new HashMap<>();//You can create payload by using pojo class as well.
       expectedData.put("username", "JohnDoe");
       expectedData.put("firstName", "John");
       expectedData.put("lastName", "Doe");
       expectedData.put("email", "john@doe.com");
       expectedData.put("password", "1234");
       expectedData.put("phone", "1234");
       expectedData.put("userStatus", 123);

       Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
       response.prettyPrint();

       Map<String ,Object> actualData =response.as(HashMap.class);
       assertEquals(200,response.statusCode());
       assertEquals(200,actualData.get("code"));
       assertEquals("unknown",actualData.get("type"));
   }
}
