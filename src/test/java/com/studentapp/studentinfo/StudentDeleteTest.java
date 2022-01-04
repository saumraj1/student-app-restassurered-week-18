package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest  extends TestBase {
   @Test
    public void deleteStudentInfo(){
        Response response = given()
                .pathParam("id",2)
                .when()
                .delete("/{id}");
        response.then().statusCode(204);
        response.prettyPrint();

   }
   // @Test
   // public  void deleteStudent

}
/*  @Test
    public void getSingleStudentInfo(){
        Response response = given()
                .pathParam("id",7)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }*/
