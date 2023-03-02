package com.company.project.demowebshop.specs;

import com.company.project.specs.SpecBase;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec extends SpecBase {

    public static RequestSpecification request = baseRequestSpec.with()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8");

    public static ResponseSpecification responseSpec = baseResponseSpec.expect()
            .statusCode(200);
}
