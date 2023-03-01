package com.company.project.demowebshop.utils;


import com.company.project.demowebshop.model.User;
import io.restassured.response.Response;

import static com.company.project.demowebshop.tests.RegisterTests.client;
import static com.company.project.demowebshop.specs.SpecBase.responseSpec;
import static com.company.project.demowebshop.tests.RegisterTests.requestVerificationToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RegisterClient {
    public Response getVerifyToken() {
        return given()
                .when()
                .get("/register");
    }

    public Response createNewUser(User user) {
        return given()
                .cookies(client.getVerifyToken().cookies())
                .formParam("__RequestVerificationToken", requestVerificationToken)
                .formParam("Gender", user.getGender())
                .formParam("FirstName", user.getFirstName())
                .formParam("LastName", user.getLastName())
                .formParam("Email", user.getEmail())
                .formParam("Password", user.getPassword())
                .formParam("ConfirmPassword", user.getPassword())
                .formParam("register-button", "Register")
                .when()
                .post("/register")
                .then()
                .log().all()
                .statusCode(302)
                .extract().response();
    }

    public Response successRedirect(User user) {
        return given()
                .cookies(client.createNewUser(user).cookies())
                .when()
                .get("/registerresult/1")
                .then()
                .statusCode(200)
                .spec(responseSpec)
                .body("html.head.title", is("Demo Web Shop. Register"))
                .extract()
                .response();
    }
}
