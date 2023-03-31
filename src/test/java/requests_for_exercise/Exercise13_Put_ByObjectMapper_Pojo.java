package requests_for_exercise;

import base_urls.RestApiExampleBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiExamplePojo.DummyRestApiDataPojo;
import pojos.DummyRestApiExamplePojo.DummyRestApiExamplePojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise13_Put_ByObjectMapper_Pojo extends RestApiExampleBaseUrl {
    /*
    URL : https://dummy.restapiexample.com/api/v1/update/21
    HTTP Request Method : PUT request
    request body: {
                    "employee_name": "Ali Can",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image",
                   }
     Test Case : Type by using Gherkin Language
     Assert :
                i)  Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data":{
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            },
                            "message": "Successfully! Record has been updated."
                       }

     */

    @Test
    public void putExample13(){
        // Set the URL
        spec.pathParams("1st","update","2nd",21);
        // set the expected data

        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Ali Can",111111,23,"Perfect image");
        DummyRestApiExamplePojo dummyRestApiExamplePojo = new DummyRestApiExamplePojo("success",expectedData,"Successfully! Record has been updated.");
        System.out.println("expectedData = " + expectedData);
        // Send the PUT request and get the response
         Response response =  given(spec).when().body(expectedData).put("{1st}/{2nd}");
         response.prettyPrint();
         // Set the actual data

        DummyRestApiExamplePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiExamplePojo.class);
             System.out.println("actualData = " + actualData);
         // Do assertion

             assertEquals(200,response.statusCode());
             assertEquals(expectedData.getEmployee_name(),actualData.getData().getEmployee_name());
             assertEquals(expectedData.getEmployee_salary(),actualData.getData().getEmployee_salary());
             assertEquals(expectedData.getEmployee_age(),actualData.getData().getEmployee_age());
             assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());
             assertEquals(dummyRestApiExamplePojo.getStatus(),actualData.getStatus());
             assertEquals(dummyRestApiExamplePojo.getMessage(),actualData.getMessage());

    }

}
