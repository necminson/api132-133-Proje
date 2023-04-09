package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.lang.annotation.Target;

import static herokuapp_smoketest.S1_Post.id;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S4_Get extends HerOkuAppBaseUrl {
    /*
            Given
                    https://restful-booker.herokuapp.com/booking/2488

            When
                    user sent GET request and get the response
            Then
                    status code should be 404
            and
                    body should be "Not Found

     */
    @Test
    public void getTest(){
        // Set the URL
        spec.pathParams("1st","booking","2nd",id);

        //Set the expected data
        String expectedData = "Not Found";
        //System.out.println("expectedData = " + expectedData);

        // Send the GET request and get the response
       Response response = given(spec).get("{1st}/{2nd}");
       // response.prettyPrint();

        // Set the actual data
       String actualData = response.asString();
        //System.out.println("actualData = " + actualData);

        // Do assertion

        assertEquals(404,response.statusCode());
        assertEquals(expectedData,actualData);
    }
}
