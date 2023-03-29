package requests_for_exercise;

import base_urls.RegresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Exercise05 extends RegresInBaseUrl {
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
    public void getExercise01(){
     // Set the Url
     spec.pathParam("1st","unknown");

     // Sent request and get response
     Response response = given(spec).get("{1st}");
     response.prettyPrint();

     // 1)Status code is 200
       assertEquals(200,response.statusCode());

      JsonPath jsonPath = response.jsonPath();

      // 2)Print all pantone_values
      List<String> list_pantone_values = jsonPath.getList("data.pantone_value");
      System.out.println("list_pantone_values = " + list_pantone_values);

      //3)Print all ids greater than 3 on the console
      //  Assert that there are 3 ids greater than 3
      List<Integer>list_id= jsonPath.getList("data.findAll{it.id>3}.id");
      System.out.println("list_id = " + list_id);
      int size_id= list_id.size();
      assertEquals(3,size_id);

      // 4)Print all names whose ids are less than 3 on the console
      //   Assert that the number of names whose ids are less than 3 is 2
      List<Integer>list_names= jsonPath.getList("data.findAll{it.id<3}.name");
      System.out.println("list_names = " + list_names);
      int size_name= list_names.size();
      assertEquals(2,size_name);










 }

}
