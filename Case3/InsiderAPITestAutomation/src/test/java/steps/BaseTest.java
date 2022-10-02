package steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class BaseTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}