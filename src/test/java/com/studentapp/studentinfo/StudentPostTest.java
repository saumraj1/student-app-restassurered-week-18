package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPostTest extends TestBase {
    @Test
    public void createStudent(){
        List<String>courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("selenium");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Prime3");
        studentPojo.setLastName("Testing1");
        studentPojo.setEmail("sumanab12346@gmai.com");
        studentPojo.setProgramme("Selenium Testing");
        studentPojo.setCourses(courseList);

        Response response = given()
                .header("Content-Type","application/json")
                .body(studentPojo)
                .when()
                .post();
         response.then().statusCode(201);
         response.prettyPrint();

    }


}
