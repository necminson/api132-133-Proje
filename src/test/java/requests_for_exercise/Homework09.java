package requests_for_exercise;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework09 extends PetStoreBaseUrl {
    //Homework9:

//Using the https://petstore.swagger.io/ document,
// write an automation test that finds the number of "pets" with a status of "available"
// and asserts that those are more than 100.

        /*
            Given https://petstore.swagger.io/api/v2/pet
                When
                        User send GET request to the URL
                Then
                        Find the number of "pets" with a status of "available"
                and
                        asserts that those are more than 100.

         */

    @Test
    public void getHm09() {
        // Set the URL
        String url = "https://petstore.swagger.io/v2/pet/findByStatus?status=available";

        // Send the GET request and get the response
        Response response = given().get(url);
        response.prettyPrint();

        int numOfElements = response.jsonPath().getList("findAll{it.status=='available'}.status").size();
        System.out.println("numOfElements = " + numOfElements);
        assertEquals(200, response.statusCode());
        assertTrue(numOfElements > 100);
    }

}
