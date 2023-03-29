package requests_for_exercise;

import base_urls.RegresInBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Homework02 extends RegresInBaseUrl {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */
    @Test
    public void getHm02(){
        // set the url
        spec.pathParams("pp1","users","pp2",23);
        // Set the request and get the response
        Response response = given(spec).when().get("{pp1}/{pp2}");


        Assert.assertEquals(404,response.statusCode());
        Assert.assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
        Assert.assertEquals("cloudflare",response.header("server"));
        Assert.assertEquals("{}",response.getBody().asString());



    }
}
