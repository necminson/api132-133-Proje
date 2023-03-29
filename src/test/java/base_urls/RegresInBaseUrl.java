package base_urls;

import io.restassured.builder.RequestSpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RegresInBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp(){
       spec= new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://reqres.in/api").build();



    }


}
