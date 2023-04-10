package requests_for_exercise;

import base_urls.RestApiExampleBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiExamplePojo.DummyRestApiDataPojo;
import pojos.DummyRestApiExamplePojo.DummyRestApiExamplePojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise16_Post_ByObjectMapper_Pojo extends RestApiExampleBaseUrl {
    /*
   URL : https://dummy.restapiexample.com/api/v1/create
   HTTP Request Method : POST request
   request body: {
                   "employee_name": "Tom Hanks",
                   "employee_salary": 111111,
                   "employee_age": 23,
                   "profile_image": "Perfect image",
                   "id":4891
                  }
    Test Case : Type by using Gherkin Language
    Assert :
               i)  Status code is 200
               ii) Response body should be like the following
                   {
                       "status": "success",
                       "data":{
                           "employee_name": "Tom Hanks",
                           "employee_salary": 111111,
                           "employee_age": 23,
                           "profile_image": "Perfect image",
                           "id":4891
                           },
                           "message": "Successfully! Record has been added."
                      }

                      =============================================
           Given
                    https://dummy.restapiexample.com/api/v1/create
           When
                   I send the POST request

           and
                   request body should be like
                                 {
                                       "employee_name": "Tom Hanks",
                                       "employee_salary": 111111,
                                       "employee_age": 23,
                                       "profile_image": "Perfect image",
                                       "id":4891
                                      }
            Then
                      Status code is 200,
            And
                       Response body should be like the following
                   {
                       "status": "success",
                       "data":{
                           "employee_name": "Tom Hanks",
                           "employee_salary": 111111,
                           "employee_age": 23,
                           "profile_image": "Perfect image",
                           "id":4891
                           },
                           "message": "Successfully! Record has been added."
                      }



    */
    @Test
    public void post() {
        // Set the URL
        spec.pathParam("1st", "create");

        // set the expectedData
        DummyRestApiDataPojo data = new DummyRestApiDataPojo("Tom Hanks", 111111, 23, "Perfect image");
        DummyRestApiExamplePojo expectedData = new DummyRestApiExamplePojo("success",data,"Successfully! Record has been added.");
        // Send The POST request And The response
        Response response = given(spec).body(data).post("{1st}");

        // Set the Actual data
        DummyRestApiExamplePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiExamplePojo.class);

        // Do assertion

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(data.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(data.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(data.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(data.getProfile_image(),actualData.getData().getProfile_image());
        assertEquals(expectedData.getMessage(),actualData.getMessage());


    }

}
