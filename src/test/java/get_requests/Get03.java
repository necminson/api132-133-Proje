package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {
       /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
      And
          "completed" is false
      And
          "userId" is 2
     */

    @Test
    public void get03() {
        //Set the URL
        //String url = "https://jsonplaceholder.typicode.com/todos/23";//This is not the recommended way --> We will create base_urls package
        spec.pathParams("first", "todos", "second", 23);

        //Set the expected data


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));//Hard Assertion

        //2nd Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));//Soft Assertion

        /*
        Note 1: When you run code, Java stops execution after first failure.
              So, assertion after the failure were not executed.
              But assertion before the failure were passed because all of them were executed and there were no failure.
        Note 2: If you type your code as execution will stop in the first failure. This is called "Hard Assertion"
        Note 3: If you type your code as execution will NOT stop in failures. This is called "Soft Assertion"
        Note 4: If you use multiple "body()" method it will work like "Hard Assertion"
        Note 5: If you use multiple assertion in one single body() method it will work like "Soft Assertion"
         */

    }
}