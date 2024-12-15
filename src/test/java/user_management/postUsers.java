package user_management;

import core.statusCode;
import io.opentelemetry.api.trace.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.cityRequest;
import pojo.postBodyRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.testng.AssertJUnit.assertEquals;

public class postUsers {

    private static FileInputStream fileInputStreamMethod(String requestBodyFileName) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/resources/TestData/" + requestBodyFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ;
        return fileInputStream;
    }

    @Test
    public void getValidatePostWithString() {

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("https://reqres.in/api/users");

        assertEquals(response.statusCode(), statusCode.CREATED.code);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void getValidatePutWithString() {

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"Hemay\",\"job\":\"SDET\"}")
                .when()
                .put("https://reqres.in/api/users/2");

        assertEquals(response.statusCode(), statusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void getValidatePostWithJsonFile() throws IOException {

        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(
                        new FileInputStream(
                                new File(System.getProperty("user.dir") + "/resources/TestData/postRequestBody.json"))))
                .when()
                .post("https://reqres.in/api/users");

        assertEquals(response.statusCode(), statusCode.CREATED.code);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void getValidatePutWithJsonFile() throws IOException {

        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(fileInputStreamMethod("putRequestBody.json")))
                .when()
                .put("https://reqres.in/api/users/2");

        assertEquals(response.statusCode(), statusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithPojo(){

        postBodyRequest postBody = new postBodyRequest();
        postBody.setJob("leader");
        postBody.setName("morpheus");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postBody)
                .when()
                .put("https://reqres.in/api/users/2");

        assertEquals(response.statusCode(), statusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithPojoList(){

        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Pyhon");

        postBodyRequest postBody = new postBodyRequest();
        postBody.setJob("leader");
        postBody.setName("morpheus");
        postBody.setLanguages(list);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postBody)
                .when()
                .post("https://reqres.in/api/users/2");

        assertEquals(response.statusCode(), statusCode.CREATED.code);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithPojoComplexList(){

        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Pyhon");

        cityRequest cityRequests = new cityRequest();
        cityRequests.setName("Banglore");
        cityRequests.setTemperature("30");

        cityRequest cityRequests2 = new cityRequest();
        cityRequests2.setName("Delhi");
        cityRequests2.setTemperature("40");
        List<cityRequest> cityRequestsList = new ArrayList<>();
        cityRequestsList.add(cityRequests);
        cityRequestsList.add(cityRequests2);

        postBodyRequest postBody = new postBodyRequest();
        postBody.setJob("leader");
        postBody.setName("morpheus");
        postBody.setLanguages(list);
        postBody.setCityRequestBody(cityRequestsList);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postBody)
                .when()
                .post("https://reqres.in/api/users/2");

        assertEquals(response.statusCode(), statusCode.CREATED.code);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithResponsePojo(){

        String str ="Hemay";
        postBodyRequest postBody = new postBodyRequest();
        postBody.setJob(str);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postBody)
                .when()
                .put("https://reqres.in/api/users/2");

        postBodyRequest responseBody = response.as(postBodyRequest.class);
        System.out.println(responseBody.getJob());
        assertEquals(responseBody.getJob(), str);
        assertEquals(response.statusCode(), statusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithResponsePojoComplexList(){

        String name = "Banglore";
        String temperature = "30";
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Pyhon");

        cityRequest cityRequests = new cityRequest();
        cityRequests.setName(name);
        cityRequests.setTemperature(temperature);

        cityRequest cityRequests2 = new cityRequest();
        cityRequests2.setName("Delhi");
        cityRequests2.setTemperature("40");
        List<cityRequest> cityRequestsList = new ArrayList<>();
        cityRequestsList.add(cityRequests);
        cityRequestsList.add(cityRequests2);

        postBodyRequest postBody = new postBodyRequest();
        postBody.setJob("leader");
        postBody.setName("morpheus");
        postBody.setLanguages(list);
        postBody.setCityRequestBody(cityRequestsList);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postBody)
                .when()
                .post("https://reqres.in/api/users/2");

        postBodyRequest responseBody = response.as(postBodyRequest.class);
        System.out.println(responseBody.getCityRequestBody().get(0).getName());
        System.out.println(responseBody.getCityRequestBody().get(0).getTemperature());
        assertEquals (responseBody.getCityRequestBody().get(0).getName(), name);
        assertEquals(responseBody.getCityRequestBody().get(0).getTemperature(), temperature);
        assertEquals(response.statusCode(), statusCode.CREATED.code);
        System.out.println(response.getBody().asString());
    }

}
