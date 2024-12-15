package user_management;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class getErgest {

    @Test(groups = "RegressionSuite")
    public void validateResponseBodyGetPathParam() {

        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        createTest("validateResponseBodyGetPathParam", "Validate 200 Status Code for GET Ergast");

        String raceSeasonValue = "2017";
        Response resp =
                given()
                        .pathParam("raceSeason", raceSeasonValue)
                        .when()
                        .get("http://ergast.com/api/f1/{raceSeason}/circuits.json"); //RestAssured

        int actualStatusCode = resp.statusCode();  //RestAssured
        assertEquals(actualStatusCode, 200); //Testng
        System.out.println(resp.body().asString());

    }
}
