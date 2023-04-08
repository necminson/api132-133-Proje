package util;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.lang.management.ManagementPermission;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {

        public static String generateToken(){

            String url = "https://restful-booker.herokuapp.com/auth";
            Map<String,String> user = new HashMap<>();
            user.put("username","admin");
            user.put("password","password123");
           Response response = given().contentType(ContentType.JSON).body(user).when().post(url);
            return response.jsonPath().getString("token");


        }


}
