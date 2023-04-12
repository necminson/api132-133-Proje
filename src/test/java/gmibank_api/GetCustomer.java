package gmibank_api;

import base_urls.GmiBankBaseUrl;
import gmibank_api.Pojos.AccountsPojo;
import gmibank_api.Pojos.CountryPojo;
import gmibank_api.Pojos.CustomerPojo;
import gmibank_api.Pojos.UserPojo;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static util.AuthenticationGmiBank.generateToken;

public class GetCustomer extends GmiBankBaseUrl {
     /*
    Given
        https://www.gmibank.com/api/tp-customers/110452
    When
        User sends Get request
    Then
        Status code should be 200
     And
        Response body should be like:
{
    "id": 110452,
    "firstName": "Jasmine",
    "lastName": "Stehr",
    "middleInitial": "V",
    "email": "marni.zboncak@yahoo.com",
    "mobilePhoneNumber": "463-609-2097",
    "phoneNumber": "1-112-497-0270",
    "zipCode": "16525",
    "address": "14387 Al Ridge5343 Bert Burgs",
    "city": "Waltermouth",
    "ssn": "761-59-2911",
    "createDate": "2021-11-28T21:00:00Z",
    "zelleEnrolled": false,
    "country": {
        "id": 3,
        "name": "USA",
        "states": null
    },
    "state": "California",
    "user": {
        "id": 110016,
        "login": "leopoldo.reinger",
        "firstName": "Jasmine",
        "lastName": "Stehr",
        "email": "marni.zboncak@yahoo.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
    },
    "accounts": []
}
     */

    @Test
    public void getCustomer() {

        // Set the URL
        spec.pathParams("1st", "tp-customers", "2nd", 110452);

        // Set the expected data
        CountryPojo country = new CountryPojo(3,"USA",null);
        UserPojo user = new UserPojo(110016, "leopoldo.reinger", "Jasmine", "Stehr",
                "marni.zboncak@yahoo.com", true, "en", null, null);
        // System.out.println("user = " + user);


        CustomerPojo expectedData = new CustomerPojo(110452, "Jasmine", "Stehr", "V", "marni.zboncak@yahoo.com", "463-609-2097",
                "1-112-497-0270", "16525", "14387 Al Ridge5343 Bert Burgs", "Waltermouth", "761-59-2911", "2021-11-28T21:00:00Z",
                false, country, "California", user, null);
        System.out.println("expectedData = " + expectedData);


        // Send The GET request and get the response

        Response response = given(spec).get("{1st}/{2nd}");
        //response.prettyPrint();


        CustomerPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), CustomerPojo.class);
        System.out.println("actualData = " + actualData);

        // Do assertions
        assertEquals(200,response.statusCode());

        assertEquals(expectedData.getId(),actualData.getId());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getMiddleInitial(),actualData.getMiddleInitial());
        assertEquals(expectedData.getEmail(),actualData.getEmail());
        assertEquals(expectedData.getMobilePhoneNumber(),actualData.getMobilePhoneNumber());
        assertEquals(expectedData.getPhoneNumber(),actualData.getPhoneNumber());
        assertEquals(expectedData.getZipCode(),actualData.getZipCode());
        assertEquals(expectedData.getAddress(),actualData.getAddress());
        assertEquals(expectedData.getCity(),actualData.getCity());
        assertEquals(expectedData.getSsn(),actualData.getSsn());
        assertEquals(expectedData.getCreateDate(),actualData.getCreateDate());
        assertEquals(expectedData.getZelleEnrolled(),actualData.getZelleEnrolled());

        assertEquals(expectedData.getCountry().getId(),actualData.getCountry().getId());
        assertEquals(expectedData.getCountry().getName(),actualData.getCountry().getName());
        assertEquals(expectedData.getCountry().getStates(),actualData.getCountry().getStates());

        assertEquals(expectedData.getAccount(),actualData.getAccount());

        assertEquals(user.getId(),actualData.getUser().getId());
        assertEquals(user.getLogin(),actualData.getUser().getLogin());
        assertEquals(user.getFirstName(),actualData.getUser().getFirstName());
        assertEquals(user.getEmail(),actualData.getUser().getEmail());
        assertEquals(user.getActivated(),actualData.getUser().getActivated());
        assertEquals(user.getLangKey(),actualData.getUser().getLangKey());
        assertEquals(user.getImageUrl(),actualData.getUser().getImageUrl());
        assertEquals(user.getResetDate(),actualData.getUser().getResetDate());


    }


}
