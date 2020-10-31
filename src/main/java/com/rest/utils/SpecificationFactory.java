package com.rest.utils;

import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecificationFactory {


    public static RequestSpecification getDefaultRequestSpecication() {
        BasicAuthScheme auth = new BasicAuthScheme();
        auth.setPassword("password");
        auth.setUserName("admin");

        return new RequestSpecBuilder()
                .setBaseUri(PropertyLoader.getUri())
                .setPort(PropertyLoader.getPort())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setAuth(auth)
                .log(LogDetail.ALL)
                .build();
    }
}
