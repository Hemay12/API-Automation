package user_management;

import com.google.gson.stream.JsonReader;
import core.statusCode;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.PropertyReader;
import utils.SoftAssertionUtil;
import utils.jsonReader;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static utils.jsonReader.getJsonArray;

import org.testng.annotations.DataProvider;


public class getUsers {

    //SoftAssertionUtil softAssertion = new  SoftAssertionUtil();

    @Test
    public void getUserData() {

                given().
                when().get("https://regres.in/api/users/page/1").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void getValidationData(){

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and validate the response body using 'then'
        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("title",equalTo("delectus aut autem"))
                .body("userId",equalTo(1));
    }

    @Test
    public void validateResponseHasItems() {
        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/posts")
                .then()
                .extract()
                .response();

        // Use Hamcrest to check that the response body contains specific items
        assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse"));
    }

    @Test
    public void validateResponseHasSize() {
        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/comments")
                .then()
                .extract()
                .response();

        // Use Hamcrest to check that the response body has a specific size
        assertThat(response.jsonPath().getList(""), hasSize(500));
    }

    @Test
    public void validateListContainsInOrder() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response = given()
                .when()
                .get("/comments?postId=1")
                .then()
                .extract()
                .response();

        List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"), contains(expectedEmails.toArray(new String[0])));
    }

    @Test
    public void testGetUsersWithQueryParameters(){

        RestAssured.baseURI = "https://reqres.in/api";
        Response response = given()
                .queryParam("page", "2")
                .when()
                .get("/users")
                .then()
                .extract()
                .response();

        response.then().body("data[0].id", is(7));
        response.then().body("data[0].email", equalTo("michael.lawson@reqres.in"));
        response.then().body("data[0].first_name", is("Michael"));
        response.then().body("data[0].last_name", is("Lawson"));
        response.then().body("data[0].avatar", is("https://reqres.in/img/faces/7-image.jpg"));
    }


    @Test
    public void testCreateUserWithFormParam() {
        Response response = given()
                .config(RestAssured.config()
                        .encoderConfig(encoderConfig().defaultContentCharset("UTF-8"))) // Ensure UTF-8 encoding
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", "John Doe")
                .formParam("job", "Developer")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201) // Assert 201 Created
                .extract()
                .response();

        // Assert that the response contains the correct name and job values
        response.then().body("name", equalTo("John Doe"));
        response.then().body("job", equalTo("Developer"));
    }

    @Test
    public void testGetUserListWithHeader(){
        given()
                .header("Content-Type","application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testWithTwoHeader(){
        given()
                .header("Authorization","bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx")
                .header("Content-Type","application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testHeaderUsingMap(){
        Map<String, String> headerz = new HashMap<>();
        headerz.put("Content-Type","application/json");
        headerz.put("Authorization","bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx");

        given()
                .headers(headerz)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFetchHeader(){
        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response();

        Headers header = response.headers();
        for( Header h : header){
            if(h.getName().equals("Server")){
                //System.out.println(h.getName() + " : " + h.getValue());
                assertEquals(h.getValue(), "cloudflare");

            }

        }
    }

    @Test
    public void testUseCookies(){
        Cookie sookie = new Cookie.Builder("cookieKey","cookieValue1")
                .setComment("Using cookie key")
                .build();

        given()
                .cookie(sookie)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFetchCookie(){
        Response response =  given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response();

        Map<String, String> sookie = response.cookies();
        assertThat(sookie,hasKey("JSESSIONID"));
        assertThat(sookie,hasValue("ABCDEFG"));
    }

    @Test
    public void verifyStatusCodeDelete(){
        Response resp = given()
                .delete("https://reqres.in/api/users?page=2");

        assertEquals(resp.statusCode(), statusCode.NO_CONTENT.code);
    }

    @Test
    public void validateWithDataFromPropertiesFile(){

        String serverAddress = PropertyReader.propertyReader("config.property","serverAddress");
        System.out.println(serverAddress);

        Response response = given()
                .queryParam("page", "2")
                .when()
                .get(serverAddress);

        assertEquals(response.statusCode(),200);
    }

    @Test
    public void validatFromPropertiesFile_TestData(){

        String serverAddress = PropertyReader.propertyReader("config.property","server");
        String endpoint = jsonReader.getTestData("endpoint");
        String URL = serverAddress + endpoint;
        System.out.println(URL);
        Response response = given()
                .queryParam("page", "2")
                .when()
                .get(URL);

        assertEquals(response.statusCode(),200);
    }

    @Test
    public void softAssertion(){

        SoftAssertionUtil.assertTrue(true,"");
        SoftAssertionUtil.assertAll();
    }

    @Test
    public void validateWithSoftAssertionUtil(){

        RestAssured.baseURI = "https://reqres.in/api";
        Response response = given()
                .queryParam("page", "2")
                .when()
                .get("/users")
                .then()
                .extract()
                .response();
        SoftAssertionUtil.assertEquals(response.statusCode(), statusCode.SUCCESS.code,"Status code is not 200");
        SoftAssertionUtil.assertAll();
        System.out.println("validateWithSoftAssertionUtil executed successfully");
    }

    @DataProvider(name = "testdata")
    public Object[][] testData() {
        return new Object[][] {
                {"1", "John"},
                {"2", "Jane"},
                {"3", "Bob"}
        };
    }

    @Test(dataProvider = "testdata")
    @Parameters({"id", "name"})
    public void testEndpoint(String id, String name) {
        given()
                .queryParam("id", id)
                .queryParam("name", name)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void Test() throws IOException, ParseException{
        jsonReader.getJsonArrayData("languages",0);
        JSONArray jsonArray = getJsonArray("contact");
        Iterator<String> iterator = jsonArray.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
