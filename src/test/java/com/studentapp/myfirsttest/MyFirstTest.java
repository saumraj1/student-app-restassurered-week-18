package com.studentapp.myfirsttest;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class MyFirstTest {
    @Test
    public void getAllStudentInfo(){
        Response response= given()
                .when()
                .get("/list");

        response.then().statusCode(200);
        response.prettyPrint();


    }

}
