package com.springboot.web;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseTest {

    public static String localUri;

    @BeforeSuite
    public static void beforeSuite() {
        localUri = ApplicationProperties.INSTANCE.getLocalUri();

        System.out.println("before suit ");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("after suit");
    }
}
