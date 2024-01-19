package com.springboot.web;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseTest {

    public static String localUri;
@BeforeSuite
//    @BeforeAll
    public static void beforeSuite() {
        localUri = ApplicationProperties.INSTANCE.getLocalUri();

        System.out.println("before suit ");
    }
//    @AfterAll
    @AfterSuite
    public static void afterSuit(){
        System.out.println("after suit");
    }
}
