package user_management;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class builderPatternImplementation {
    @Test
    public void testRestAssuredNormalApproach() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .contentType(ContentType.JSON)
                .queryParam("userId", "1")
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
