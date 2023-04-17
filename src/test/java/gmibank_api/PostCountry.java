package gmibank_api;

import base_urls.GmiBankBaseUrl;
import com.google.gson.Gson;
import gmibank_api.Pojos.Country;
import gmibank_api.Pojos.States;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.Test;
import util.ObjectMapperUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
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
                      "name": "Algebra"

                    },
                    {
                      "id": 2,
                      "name": "Geometry"
                    },
                    {
                      "id": 3,
                      "name": "Number Theory"

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
    public void postCountry() {
        // Set the URL
        spec.pathParams("1st", "api", "2nd", "tp-countries");

        // Set the expected data

        States state1 = new States(1, "Algebra");
        States state2 = new States(2, "Geometry");
        States state3 = new States(3, "Number Theory");
        List<States> stateLists = new ArrayList<>();
        stateLists.add(state1);
        stateLists.add(state2);

        stateLists.add(state3);
        // System.out.println("stateLists = " + stateLists);
        Country expectedData = new Country("Mathematics Republic", stateLists);

        //System.out.println("expectedData = " + expectedData);

        // Send The POST request and get the response
        Response response = given(spec).body(expectedData).post("{1st}/{2nd}");
        //response.prettyPrint();

        // Do assertion


        // Assertion 1

        response.
                then().
                statusCode(201).
                body("name", equalTo(expectedData.getName()),

                        "states.id[0]", equalTo(expectedData.getStates().get(0).getId()),
                        "states.name[0]", equalTo(expectedData.getStates().get(0).getName()),
                        "states.id[1]", equalTo(expectedData.getStates().get(1).getId()),
                        "states.name[1]", equalTo(expectedData.getStates().get(1).getName()),
                        "states.id[2]", equalTo(expectedData.getStates().get(2).getId()),
                        "states.name[2]", equalTo(expectedData.getStates().get(2).getName())
                        );


                        // Assertion 2

        JsonPath jsonPath = response.jsonPath();

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getName(), jsonPath.getString("name") );
        assertEquals(expectedData.getStates().get(0).getId(), jsonPath.getList("states.id").get(0));
        assertEquals(expectedData.getStates().get(0).getName(), jsonPath.getList("states.name").get(0));
        assertEquals(expectedData.getStates().get(1).getId(), jsonPath.getList("states.id").get(1));
        assertEquals(expectedData.getStates().get(1).getName(), jsonPath.getList("states.name").get(1));
        assertEquals(expectedData.getStates().get(2).getId(), jsonPath.getList("states.id").get(2));
        assertEquals(expectedData.getStates().get(2).getName(), jsonPath.getList("states.name").get(2));



        // Assertion 3

        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(expectedData.getName(), actualDataMap.get("name"));
        assertEquals(expectedData.getStates().get(0).getId(), ((Map) ((List<Object>) actualDataMap.get("states")).get(0)).get("id"));
        assertEquals(expectedData.getStates().get(0).getName(), ((Map) ((List<Object>) actualDataMap.get("states")).get(0)).get("name"));
        assertEquals(expectedData.getStates().get(1).getId(), ((Map) ((List<Object>) actualDataMap.get("states")).get(1)).get("id"));
        assertEquals(expectedData.getStates().get(1).getName(), ((Map) ((List<Object>) actualDataMap.get("states")).get(1)).get("name"));
        assertEquals(expectedData.getStates().get(2).getId(), ((Map) ((List<Object>) actualDataMap.get("states")).get(2)).get("id"));
        assertEquals(expectedData.getStates().get(2).getName(), ((Map) ((List<Object>) actualDataMap.get("states")).get(2)).get("name"));

        // Assertion 4

         Country actualDataAsPojo = response.as(Country.class);

        assertEquals(expectedData.getName(), actualDataAsPojo.getName());
        assertEquals(expectedData.getStates().get(0).getId(), actualDataAsPojo.getStates().get(0).getId());
        assertEquals(expectedData.getStates().get(0).getName(), actualDataAsPojo.getStates().get(0).getName());
        assertEquals(expectedData.getStates().get(1).getId(), actualDataAsPojo.getStates().get(1).getId());
        assertEquals(expectedData.getStates().get(1).getName(), actualDataAsPojo.getStates().get(1).getName());
        assertEquals(expectedData.getStates().get(2).getId(), actualDataAsPojo.getStates().get(2).getId());
        assertEquals(expectedData.getStates().get(2).getName(), actualDataAsPojo.getStates().get(2).getName());


        // Assertion 5

       Country actualDataPojo = ObjectMapperUtils.convertJsonToJava(response.asString(), Country.class);

        assertEquals(expectedData.getName(), actualDataPojo.getName());
        assertEquals(expectedData.getStates().get(0).getId(), actualDataPojo.getStates().get(0).getId());
        assertEquals(expectedData.getStates().get(0).getName(), actualDataPojo.getStates().get(0).getName());
        assertEquals(expectedData.getStates().get(1).getId(), actualDataPojo.getStates().get(1).getId());
        assertEquals(expectedData.getStates().get(1).getName(), actualDataPojo.getStates().get(1).getName());
        assertEquals(expectedData.getStates().get(2).getId(), actualDataPojo.getStates().get(2).getId());
        assertEquals(expectedData.getStates().get(2).getName(), actualDataPojo.getStates().get(2).getName());


        // Assertion 6

        Country actualDataGson = new Gson().fromJson(response.asString(), Country.class);
        System.out.println("actualDataGson = " + actualDataGson);

        assertEquals(expectedData.getName(), actualDataGson.getName());
        assertEquals(expectedData.getStates().get(0).getId(), actualDataGson.getStates().get(0).getId());
        assertEquals(expectedData.getStates().get(0).getName(), actualDataGson.getStates().get(0).getName());
        assertEquals(expectedData.getStates().get(1).getId(), actualDataGson.getStates().get(1).getId());
        assertEquals(expectedData.getStates().get(1).getName(), actualDataGson.getStates().get(1).getName());
        assertEquals(expectedData.getStates().get(2).getId(), actualDataGson.getStates().get(2).getId());
        assertEquals(expectedData.getStates().get(2).getName(), actualDataGson.getStates().get(2).getName());

    }
}
