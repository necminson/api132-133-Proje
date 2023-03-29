package requests_for_exercise;

import base_urls.RegresInBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.RegresInTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class Homework07 extends RegresInBaseUrl {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/
    @Test
    public void postHm01(){
        spec.pathParam("first", "users");

        Map<String,String > expectedData = new HashMap<>();
        expectedData.put("name","morpheus");
        expectedData.put("job","leader");

        System.out.println("expectedData = " + expectedData);

        Response response = given(spec).body(expectedData).when().post("/{first}");
        //response.prettyPrint();

        Map<String,String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(expectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(expectedData.get("job"),actualData.get("job"));

    }

    @Test
    public void postHm02() {
        spec.pathParam("first", "users");

        Map<String, String> expectedData = new RegresInTestData().RegresInTestMethod("morpheus","leader");

        System.out.println("expectedData = " + expectedData);

        Response response = given(spec).body(expectedData).when().post("/{first}");
        //response.prettyPrint();

        Map<String, String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(expectedData.get("name"), actualData.get("name"));
        Assert.assertEquals(expectedData.get("job"), actualData.get("job"));
    }
}
