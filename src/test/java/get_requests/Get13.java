package get_requests;

import base_urls.RestApiExampleBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get13 extends RestApiExampleBaseUrl {
            /*
                    Given
                        https://dummy.restapiexample.com/api/v1/employees
                    When
                        User sends Get Request to the Url
                    Then
                        Status code is 200
                    And
                        There are 24 employees
                    And
                        "Tiger Nixon" and "Garrett Winters" are among the employees
                    And
                        The greatest age is 66
                    And
                        The name of the lowest age is "[Tatyana Fitzpatrick]"
                    And
                        Total salary of all employees is 6,644,770
 */
    @Test
    public void get(){
       // Set the URL
       spec.pathParam("1st","employees");
       // send GET request and get the response
       Response response = given(spec).get("{1st}");
       //response.prettyPrint();

       // Do the assertion


        response.then().statusCode(200).
                body("data",hasSize(24),
                "data.employee_name",hasItems("Tiger Nixon","Garrett Winters") );

       JsonPath jsonPath = response.jsonPath();
       List<Integer> ages =  jsonPath.getList("data.employee_age");
        Collections.sort(ages);
        int greatestAge = ages.get(ages.size()-1);
        assertEquals(66,greatestAge);

       String name = jsonPath.getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name");
        //System.out.println("name = " + name);
        assertEquals("[Tatyana Fitzpatrick]",name);

       List<Integer> salaries = jsonPath.getList("data.employee_salary");
       int sumOfSalaries = salaries.stream().reduce(0,Math::addExact);
        System.out.println("sumOfSalaries = " + sumOfSalaries);
        assertEquals(6644770,sumOfSalaries);


    }



    }


