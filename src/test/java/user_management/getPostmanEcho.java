package user_management;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.jsonReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class getPostmanEcho {

    @Test
    public void validateResponseBodyGetBasicAuth(){
        Response response= given()
                .auth()
                .basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth");

        int actualStatusCode = response.statusCode();
        assertEquals(actualStatusCode, 200);
        //System.out.println(response.body().asString());

    }

    @Test
    public void validateResponseBodyGetDigestAuth(){
        Response response= given()
                .auth()
                .digest("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth");

        int actualStatusCode = response.statusCode();
        assertEquals(actualStatusCode, 200);
        System.out.println(response.body().asString());
    }

    @Test
    public void validateWithTestDataFromJson() throws IOException, ParseException {
        String username = jsonReader.getTestData("username");
        String password = jsonReader.getTestData("password");
        //System.out.println(username + " : " + password);
        Response response= given()
                .auth()
                .digest(username,jsonReader.getTestData("password"))
                .when()
                .get("https://postman-echo.com/basic-auth");

        int actualStatusCode = response.statusCode();
        assertEquals(actualStatusCode, 200);
        //System.out.println(response.body().asString());
    }
}
