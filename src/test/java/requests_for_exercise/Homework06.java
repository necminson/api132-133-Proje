package requests_for_exercise;

import base_urls.RegresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Homework06 extends RegresInBaseUrl {
     /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void getHm06(){
       // Set the url
      spec.pathParam("1st","unknown");

      // Send request and get response
       Response response = given(spec).when().get("{1st}");
       response.
                       then().
                      statusCode(200).
                      body("data",hasSize(6));

       response.prettyPrint();

       // assertion
        //  1)Status code is 200
        Assert.assertEquals(200,response.statusCode());
        //  2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        List<Objects> pantone_valueList = jsonPath.getList("data.pantone_value");
        System.out.println("pantone_valueList = " + pantone_valueList);
        //3)Print all ids greater than 3 on the console
        //              Assert that there are 3 ids greater than 3
       List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("ids = " + ids);
       int ids_size = ids.size();

        Assert.assertEquals(3,ids_size);

        // 4)Print all names whose ids are less than 3 on the console
        //              Assert that the number of names whose ids are less than 3 is 2

        List<String> nameList =  jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("nameList = " + nameList);
        int numOfNames = nameList.size();
        Assert.assertEquals(2,numOfNames);
    }

}
