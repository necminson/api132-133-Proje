package gmiBank;

import base_urls.GmiBankBaseUrl;
import gmiBank.Pojos.AccountsPojo;
import gmiBank.Pojos.CustomerPojo;
import gmiBank.Pojos.UserPojo;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;
import util.AuthenticationGmiBank.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static util.AuthenticationGmiBank.generateToken;

public class GetCustomer extends GmiBankBaseUrl {
     /*
    Given
        https://www.gmibank.com/api/tp-customers/133986
    When
        User sends Get request
    Then
        Status code should be 200
     And
        Response body should be like:
{
    "id": 133986,
    "firstName": "Danika",
    "lastName": "Huel",
    "middleInitial": "S",
    "email": "danikahuel@gmail.com",
    "mobilePhoneNumber": "155-489-7844",
    "phoneNumber": "155-489-7844",
    "zipCode": "32476",
    "address": "3848 Lang Hill",
    "city": "Hsajkhfja",
    "ssn": "725-97-6213",
    "createDate": "2022-01-21T05:00:00Z",
    "zelleEnrolled": false,
    "country": null,
    "state": "",
    "user": {
        "id": 134701,
        "login": "raymundo.moen",
        "firstName": "Danika",
        "lastName": "Huel",
        "email": "danikahuel@gmail.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
    },
    "accounts": [
        {
            "id": 128481,
            "description": "Description",
            "balance": 0,
            "accountType": "CHECKING",
            "accountStatusType": "ACTIVE",
            "createDate": "2022-01-04T21:00:00Z",
            "closedDate": "2022-01-04T21:00:00Z",
            "employee": null,
            "accountlogs": null
        },
        {
            "id": 131776,
            "description": "mfy",
            "balance": 536846,
            "accountType": "CREDIT_CARD",
            "accountStatusType": "ACTIVE",
            "createDate": "2022-01-18T21:00:00Z",
            "closedDate": "2022-01-18T21:00:00Z",
            "employee": null,
            "accountlogs": null
        }
    ]
}
     */

    @Test
    public void getCustomer() {

        // Set the URL
        spec.pathParams("1st", "tp-customers", "2nd", 133986);
        // Set the expected data
        AccountsPojo account01 = new AccountsPojo(128481, "Description", 0, "CHECKING",
                "ACTIVE", "2022-01-04T21:00:00Z",
                "2022-01-04T21:00:00Z", null, null);

        AccountsPojo account02 = new AccountsPojo(131776, "mfy", 536846, "CREDIT_CARD",
                "ACTIVE", "2022-01-18T21:00:00Z",
                "2022-01-18T21:00:00Z", null, null);

        Map<String, Object> account = new HashMap<>();
        account.put("account01", account01);
        account.put("account02", account02);
        // System.out.println("account = " + account);

        UserPojo user = new UserPojo(134701, "raymundo.moen", "Danika", "Huel",
                "danikahuel@gmail.com", true, "en", null, null);
        // System.out.println("user = " + user);

        CustomerPojo expectedData = new CustomerPojo(133986, "Danika", "Huel", "S", "danikahuel@gmail.com", "155-489-7844",
                "155-489-7844", "32476", "3848 Lang Hill", "Hsajkhfja", "725-97-6213", "2022-01-21T05:00:00Z",
                false, null, "", user, account);
        System.out.println("expectedData = " + expectedData);

        // Send The GET request and get the response

        Response response = given(spec).get("{1st}/{2nd}");
        // response.prettyPrint();

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
        assertEquals(expectedData.getCountry(),actualData.getZipCode());

        assertEquals(user.getId(),actualData.getUser().getId());
        assertEquals(user.getLogin(),actualData.getUser().getLogin());
        assertEquals(user.getFirstName(),actualData.getUser().getFirstName());
        assertEquals(user.getEmail(),actualData.getUser().getEmail());
        assertEquals(user.getActivated(),actualData.getUser().getActivated());
        assertEquals(user.getLangKey(),actualData.getUser().getLangKey());
        assertEquals(user.getImageUrl(),actualData.getUser().getImageUrl());
        assertEquals(user.getResetDate(),actualData.getUser().getResetDate());

        assertEquals(account01.getId(),actualData.getAccount().get(account01.getId()));
        assertEquals(account01.getDescription(),actualData.getAccount().get(account01.getDescription()));
        assertEquals(account01.getBalance(),actualData.getAccount().get(account01.getBalance()));
        assertEquals(account01.getAccountType(),actualData.getAccount().get(account01.getAccountType()));
        assertEquals(account01.getAccountStatusType(),actualData.getAccount().get(account01.getAccountStatusType()));
        assertEquals(account01.getCreateDate(),actualData.getAccount().get(account01.getCreateDate()));
        assertEquals(account01.getClosedDate(),actualData.getAccount().get(account01.getClosedDate()));
        assertEquals(account01.getEmployee(),actualData.getAccount().get(account01.getEmployee()));
        assertEquals(account01.getAccountlogs(),actualData.getAccount().get(account01.getAccountlogs()));

        assertEquals(account02.getId(),actualData.getAccount().get(account02.getId()));
        assertEquals(account02.getDescription(),actualData.getAccount().get(account02.getDescription()));
        assertEquals(account02.getBalance(),actualData.getAccount().get(account02.getBalance()));
        assertEquals(account02.getAccountType(),actualData.getAccount().get(account02.getAccountType()));
        assertEquals(account02.getAccountStatusType(),actualData.getAccount().get(account02.getAccountStatusType()));
        assertEquals(account02.getCreateDate(),actualData.getAccount().get(account02.getCreateDate()));
        assertEquals(account02.getClosedDate(),actualData.getAccount().get(account02.getClosedDate()));
        assertEquals(account02.getEmployee(),actualData.getAccount().get(account02.getEmployee()));
        assertEquals(account02.getAccountlogs(),actualData.getAccount().get(account02.getAccountlogs()));

    }


}
