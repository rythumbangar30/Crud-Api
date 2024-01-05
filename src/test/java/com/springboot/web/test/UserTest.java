package com.springboot.web.test;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.springboot.web.BaseTest;
import com.springboot.web.apiUser.User;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class UserTest extends BaseTest {



    @Test
    public void getUser() throws JSONException {
        String url="2";
        String token="mytoken";
        Response response=given()
                .header("Authorization","Bearer "+token)
                .request(Method.GET,localUri+url);
        response.then().log().all();
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println(jsonObject);
    }
    @Test(priority = 1)
    public void createUser() throws JSONException {
        String payload="{\n" +
                "    \"name\":\"Cream\",\n" +
                "    \"city\":\"bhind\",\n" +
                "    \"status\":\"inactive\"\n" +
                "}";
        Response response=given()
                .contentType(ContentType.JSON)
                .body(payload)
                .request(Method.POST,localUri+"addUser");
        response.then().log().all();
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println(jsonObject);
    }
    @Test(priority = 2)
    public void updateUser() throws JSONException {
        String url="2";
        String data="{\n" +
                "    \"name\":\"andy\",\n" +
                "    \"city\":\"mumbai\",\n" +
                "    \"status\":\"inactive\"\n" +
                "}";
        Response response=given()
                .contentType(ContentType.JSON)
                .body(data)
                .request(Method.PUT,localUri+"updateUser/"+url);
        response.then().log().all();
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println(jsonObject);
    }
    @Test(priority = 3)
    public void deleteUser(){
        String url="2";
        Response response=given()
                .request(Method.DELETE,localUri+"deleteUser/"+url);
        assertThat(response.statusCode(),equalTo(204));

    }
}
