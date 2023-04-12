package util;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


    public class AuthenticationGmiBank {


        public static String generateToken() {

            Map<String, Object> postBody = new HashMap<>();
            postBody.put("password", "Mark.123");
            postBody.put("rememberMe", true);
            postBody.put("username", "mark_twain");

            Response response = given().contentType(ContentType.JSON).body(postBody).post("https://gmibank.com/api/authenticate");

            return response.jsonPath().getString("id_token");
        }

}
