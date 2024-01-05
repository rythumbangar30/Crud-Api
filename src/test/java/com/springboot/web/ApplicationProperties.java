package com.springboot.web;

import java.util.Properties;

public enum ApplicationProperties {
    INSTANCE;
    private final Properties properties;
    ApplicationProperties(){
        properties=new Properties();
        try{
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        }catch (Exception e){

        }
    }
    public String getLocalUri(){
         return properties.getProperty("url");
    }
}
