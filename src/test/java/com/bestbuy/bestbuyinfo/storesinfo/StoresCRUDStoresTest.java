package com.bestbuy.bestbuyinfo.storesinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.StoresTestBase;
import gherkin.deps.com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StoresCRUDStoresTest extends StoresTestBase {
    static String nameP = "North West London";
    static String name = "North West London";
    static String type = "SupperBig";
    static String address = "104 HeadStone Dr";
    static String address2 = "Main Road";
    static String city = "Harrow";
    static String state = "London";
    static String zip = "51112";
    static double lat = 45.036556;
    static double lng = -93.025986;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeID;

    @Test
    public void test001() {
        StoresPojo bestBuyPojo = new StoresPojo();

        bestBuyPojo.setName(name);
        bestBuyPojo.setType(type);
        bestBuyPojo.setAddress(address);
        bestBuyPojo.setAddress2(address2);
        bestBuyPojo.setCity(city);
        bestBuyPojo.setState(state);
        bestBuyPojo.setZip(zip);
        bestBuyPojo.setLat(lat);
        bestBuyPojo.setLng(lng);
        bestBuyPojo.setHours(hours);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(bestBuyPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void test002() {
        String p1 = "data.findAll{it.name=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> storeList =
                given()
                        .queryParam("type", type)
                        .queryParam("city", city)
                        .when()
                        .get()
                        .then().log().all()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);
        System.out.println(storeList);
        storeID = (int) storeList.get("id");
        System.out.println(storeID);
    }

    @Test
    public void test003() {
        String p1 = "data.findAll{it.name=='";
        String p2 = "'}.get(0)";
        System.out.println(storeID);
        name = "London Wood";
        hours = "Mon: 10-10; Tue: 10-9; Wed: 10-11; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-5";
        name = name + "_Updated";

        StoresPojo bestBuyPojo = new StoresPojo();

        bestBuyPojo.setName(name);
        bestBuyPojo.setType(type);
        bestBuyPojo.setAddress(address);
        bestBuyPojo.setAddress2(address2);
        bestBuyPojo.setCity(city);
        bestBuyPojo.setState(state);
        bestBuyPojo.setZip(zip);
        bestBuyPojo.setLat(lat);
        bestBuyPojo.setLng(lng);
        bestBuyPojo.setHours(hours);

        given()
                .header("Content-Type", "application/json")
                .pathParam("storeId", storeID)
                .body(bestBuyPojo)
                .when()
                .put("/{storeId}")
                .then().log().all().statusCode(200);

        HashMap<String, Object> storeList =
                given()
                        .queryParam("type", type)
                        .queryParam("city", city)
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);
        System.out.println(storeList);
    }

    @Test
    public void test004() {
        given()
                .pathParam("storeID", storeID)
                .when()
                .delete("/{storeID}")
                .then()
                .statusCode(200);
        given()
                .pathParam("storeID", storeID)
                .when()
                .get("/{storeID}")
                .then()
                .statusCode(404);

    }



}
/*static String name = "prime testing"+TestUtils.getRandomValue();
    static String type = "IT Academy";
    static String address = "12 london Road";
    static String address2 = "lamda street";
    static String city ="coventry";
    static String state ="West midland";
    static String zip ="cv3 2np";
    static int lat = 3;
    static int lng = 2;
    static String hours ="mon to fri :9 to 5";
//8928
*/