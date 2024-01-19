package com.springboot.web.test;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.springboot.web.BaseTest;
import com.springboot.web.entity.Person;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
//@Configuration
public class PersonTest extends BaseTest {
    Faker faker=new Faker();
    Person person=new Person();

    @Test
    void getAllPersonData(){
        createPerson();
        Response response= getAllPerson();

        response.then().log().all();
    }

    @Test
    void getPersonById() throws JSONException {
        Response createResponse=createPerson();

        String responseBody=createResponse.asString();

        JSONObject jsonObject=new JSONObject(responseBody);
        int userId=jsonObject.getInt("id");

        Response response=getPersonById(userId);

        JSONObject json=new JSONObject(response.asString());

        assertThat(response.statusCode(),equalTo(200));
        assertThat(person.getName(),equalTo(json.get("name")));
        assertThat(person.getDept(),equalTo(json.get("dept")));
        assertThat(person.getSalary(),equalTo(json.get("salary")));

        System.out.println("Get Person By Id:\n"+response.asString());

    }

    @Test
    void addPerson() throws JSONException {
        Response createResponse=createPerson();

        String responseBody=createResponse.getBody().asString();
        JSONObject jsonObject=new JSONObject(responseBody);

        int userId=jsonObject.getInt("id");

        assertThat(person.getName(),equalTo(jsonObject.get("name")));
        assertThat(person.getDept(),equalTo(jsonObject.get("dept")));
        assertThat(person.getSalary(),equalTo(jsonObject.get("salary")));

        System.out.println("ADDED PERSON:\n"+userId);
    }

    @Test
    void updatePerson() throws JSONException{
        Response createResponse=createPerson();
        String responseBody=createResponse.asString();
        JSONObject jsonObject=new JSONObject(responseBody);

        int userId=jsonObject.getInt("id");
        System.out.println("ADDED PERSON:\n"+userId);

        Map<String ,Object>updateData=new HashMap<>();
        updateData.put("name",person.getName());
        updateData.put("dept",person.getDept());
        updateData.put("salary",person.getSalary());

        String payload=new Gson().toJson(updateData);

        Response response=updatePerson(userId,payload);

//        response.then().log().all();
        System.out.println("Updated PERSON:\n"+response.asString());

        String responseBody1=response.asString();

        Gson gson = new Gson();
        Person object = gson.fromJson(responseBody1, Person.class);

        assertThat(object,hasProperty("name",equalTo(object.getName())));
        assertThat(object,hasProperty("dept",equalTo(object.getDept())));
        assertThat(object,hasProperty("salary",equalTo(object.getSalary())));

//        JsonObject to Person Object
//        JSONObject jsonObject1=new JSONObject(responseBody1);
//        PersonVariable person=new Gson().fromJson(String.valueOf(jsonObject1), PersonVariable.class);
//        assertThat(person,hasProperty("name",equalTo(person)));
//        assertThat(person,equalTo(jsonObject1.get("dept")));

    }

    @Test
    void deletePerson()throws JSONException{
        Response createResponse=createPerson();
        String responseBody=createResponse.asString();
        JSONObject jsonObject=new JSONObject(responseBody);

        int userId=jsonObject.getInt("id");
        Response response=deletePerson(userId);
        assertThat(response.statusCode(),equalTo(204));
        System.out.println("Deleted Person:"+userId);
    }

    private Response createPerson(){
        person.setName(faker.name().name());
        person.setDept(faker.commerce().department());
        person.setSalary(faker.number().randomDouble(2,10000,100000));

        Map<String ,Object> payload=new HashMap<>();

        payload.put("name",person.getName());
        payload.put("dept",person.getDept());
        payload.put("salary",person.getSalary());

        String json = new Gson().toJson(payload);
        Response response=createUser(json);

        assertThat(response.statusCode(),equalTo(201));

        System.out.println("Created User:\n"+response.asString());
        return response;
    }


    Response createUser(String payload){
        Response response=given()
                .contentType(ContentType.JSON)
                .body(payload)
                .request(Method.POST,"addPerson");
        return response;
    }

    Response getPersonById(int id){
        Response response=given()
                .request(Method.GET,localUri+"getPerson/"+id);
        return response;
    }
    Response getAllPerson(){
        Response response=given()
                .request(Method.GET,localUri+"getAllPerson");
        return response;
    }
    Response updatePerson(int id,String payload){
        Response response=given()
                .contentType(ContentType.JSON)
                .body(payload)
                .request(Method.PUT,"updatePerson/"+id);
        return response;
    }
    Response deletePerson(int id){
        Response response=given()
                .request(Method.DELETE,"deletePerson/"+id);
        return response;
    }
}
