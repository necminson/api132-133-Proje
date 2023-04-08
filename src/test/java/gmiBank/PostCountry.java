package gmiBank;

import base_urls.GmiBankBaseUrl;
import org.junit.Test;
import util.AuthenticationGmiBank;

public class PostCountry extends GmiBankBaseUrl {
    //https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1 dokümanını kullanarak
    // en az 3 "state" içeren bir "country" oluşturan bir otomasyon testi yazınız.
    /*
    Given
        https://gmibank.com/api/tp-countries
    And
        {
          "name": "Banana Republic",
          "states": [
            {
              "id": 1,
              "name": "Apple"
            },
            {
              "id": 2,
              "name": "Orange"
            },
            {
              "id": 3,
              "name": "Pear"
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
            "id": 176677,
            "name": "Banana Republic",
            "states": [
                {
                    "id": 1,
                    "name": "Apple",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Orange",
                    "tpcountry": null
                },
                {
                    "id": 3,
                    "name": "Pear",
                    "tpcountry": null
                }
            ]
        }
     */

    @Test
    public void postCountry(){

    }
}
