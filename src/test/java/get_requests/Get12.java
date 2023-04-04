package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get12 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        "Gov. Vrinda Panicker", "Sen. Devika Embranthiri" and "Rev. Jay Shukla" are among the users
    And
        The female users are less than or equals to male users
*/

    @Test
    public void get12() {
        // Set the URL
        spec.pathParam("1st", "users");
        // Set the expected data

        // send the GET request and get the response
        Response response = given(spec).get("{1st}");

      //response.prettyPrint();

        // Do assertions

        response.then().body("meta.pagination.limit", equalTo(10),
                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data", hasSize(10), "data.status", hasItem("active"), "data.name", hasItems("Chandak Saini", "Fr. Rajinder Patel", "Chakor Verma"));


        int numOfFemales = response.jsonPath().getList("data.findAll {it.gender=='female'}.gender").size();
        //System.out.println("numOfFemales = " + numOfFemales);
        int numOfMales = response.jsonPath().getList("data.findAll {it.gender=='male'}.gender").size();
        //System.out.println("numOfMales = " + numOfMales);


        assertTrue(numOfFemales<=numOfMales);


    }

}
