package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RestApiExampleBaseUrl {
    protected RequestSpecification spec;

    @Before//This method will run before each @Test methods.
    public void setUp() {

        spec = new RequestSpecBuilder().
                    setContentType(ContentType.JSON).
                    setBaseUri("https://dummy.restapiexample.com/api/v1").
                    build();

    }
}
