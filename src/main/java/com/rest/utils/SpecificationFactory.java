package com.rest.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecificationFactory {




    public static RequestSpecification getDefaultRequestSpecication() {
        return new RequestSpecBuilder()
                .setBaseUri(PropertyLoader.getUri())
                .setPort(PropertyLoader.getPort())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
