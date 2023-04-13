package gmibank_api;

import base_urls.GmiBankBaseUrl;
import gmibank_api.Pojos.CountryPojo;
import gmibank_api.Pojos.Country_Without_IdPojo;
import gmibank_api.Pojos.CustomerPojo;
import gmibank_api.Pojos.StatePojo;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostCountry extends GmiBankBaseUrl {
    //Type an automation test that creates a "country" which includes at least 3 "states" using the document
    // https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1.
    /*
    Given
        https://gmibank.com/api/tp-countries
    And
               {
                  "name": "Mathematics Republic",
                  "states": [
                    {
                      "id": 1,
                      "name": "Algebra",
                      "tpcountry": null
                    },
                    {
                      "id": 2,
                      "name": "Geometry",
                      "tpcountry": null
                    },
                    {
                      "id": 3,
                      "name": "Number Theory",
                      "tpcountry": null
                    }
                  ]
                }
    When
        User send Post request
    Then
        Status code should be 201
    And
        Body should be
                            {
                                "id": 177861,
                                "name": "Mathematics Republic",
                                "states": [
                                    {
                                        "id": 1,
                                        "name": "Algebra",
                                        "tpcountry": null
                                    },
                                    {
                                        "id": 2,
                                        "name": "Geometry",
                                        "tpcountry": null
                                    },
                                    {
                                        "id": 3,
                                        "name": "Number Theory",
                                        "tpcountry": null
                                    }
                                ]
                            }
     */

    @Test
    public void postCountry(){
    // Set the URL
        spec.pathParams("1st","api","2nd","tp-countries");

    // Set the expected data

        StatePojo state1 = new StatePojo(1,"Algebra",null);
        StatePojo state2 = new StatePojo(2,"Geometry",null);
        StatePojo state3 = new StatePojo(3,"Number Theory",null);
        List<StatePojo> stateList = new ArrayList<>();
        stateList.add(state1);
        stateList.add(state2);
        stateList.add(state3);
        // System.out.println("stateList = " + stateList);
        Country_Without_IdPojo expectedData = new Country_Without_IdPojo("Mathematics Republic",stateList);

        System.out.println("expectedData = " + expectedData);

        // Send The POST request and get the response
       Response response = given(spec).body(expectedData).post("{1st}/{2nd}");
       // response.prettyPrint();

        // Set the actual data
        CountryPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), CountryPojo.class);
        System.out.println("actualData = " + actualData);

        // Do assertion

        assertEquals(201,response.statusCode());

        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getStates().getClass(),actualData.getStates().getClass());




    }
}
