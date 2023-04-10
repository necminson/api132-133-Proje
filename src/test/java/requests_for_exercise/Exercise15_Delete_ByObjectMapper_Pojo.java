package requests_for_exercise;

import base_urls.RestApiExampleBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiExamplePojo.DummyRestApiDeletePojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise15_Delete_ByObjectMapper_Pojo extends RestApiExampleBaseUrl {
    /*
    URL: https://dummy.restapiexample.com/api/v1/delete/2
    HTTP request Method : DELETE request
    TestCase : Type by using Gherkin Language
    Assert:
            i) Status code is 200
            ii) "status" is "success"
            iii) "data" is 2
            iv) "message" is "Successfully! Record has been deleted"

          Given
                https://dummy.restapiexample.com/api/v1/delete/2
          When
                I send DELETE request
          Then
                1) Status code is 200
                2) "status" is "success"
                3) "data" is 2
                4) "message" is "Successfully! Record has been deleted"


     */

    @Test
    public void delete02() {
        // set the URL
        spec.pathParams("1st", "delete", "2nd", 2);

        // Set the expectedData
        DummyRestApiDeletePojo expectedData = new DummyRestApiDeletePojo("success", "2", "Successfully! Record has been deleted");

        // Send the DELETE request And get the response
        Response response = given(spec).delete("{1st}/{2nd}");

        // Set the actual data
        DummyRestApiDeletePojo actualData =  ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiDeletePojo.class);

        // Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getData(),actualData.getData());
        assertEquals(expectedData.getMessage(),actualData.getMessage());

    }


}
