package com.company.project.demowebshop.utils;

import com.company.project.demowebshop.model.User;
import com.company.project.demowebshop.tests.TestBase;
import io.restassured.response.Response;

import static com.company.project.demowebshop.tests.RegisterTests.client;
import static com.company.project.demowebshop.specs.Spec.responseSpec;
import static com.company.project.demowebshop.tests.RegisterTests.requestVerificationToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RegisterClient extends TestBase{

    public Response getVerifyToken() {

        return given()
                .when()
                .get(BASE_URI + "/register");
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
                .post(BASE_URI + "/register")
                .then()
                .log().all()
                .statusCode(302)
                .extract().response();
    }

    public Response successRedirect(User user) {

        return given()
                .cookies(client.createNewUser(user).cookies())
                .when()
                .get(BASE_URI + "/registerresult/1")
                .then()
                .statusCode(200)
                .spec(responseSpec)
                .body("html.head.title", is("Demo Web Shop. Register"))
                .extract()
                .response();
    }
}
