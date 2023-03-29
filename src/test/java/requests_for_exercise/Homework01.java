package requests_for_exercise;

import base_urls.RegresInBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Homework01 extends RegresInBaseUrl {
    /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void getHm01(){
        //  Set the Url
        spec.pathParams("first","users","second",3);


        // set the request and get the response

        Response response = given(spec).when().get("{first}/{second}");

        response.then().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType("application/json");

        // Do assertion

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());


    }


}

