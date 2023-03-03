package com.company.project.demowebshop.tests;

import com.company.project.demowebshop.config.Authorization;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.*;

import java.util.Objects;

import static com.company.project.helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static com.company.project.demowebshop.specs.Spec.*;

@Tag("demowebshop")
@DisplayName("check login on https://demowebshop.tricentis.com")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class LoginTests extends TestBase {

    private static Authorization config;
    private static String login;
    private static String password;
    private String authorizationCookie;

    @BeforeAll
    static void setConfig() {
        config = ConfigFactory.create(Authorization.class, System.getProperties());
        login = config.getLogin();
        password = config.getPass();
    }

    @Feature("JIRAPROJECT-26012")
    @Story("JIRAPROJECT-28000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("check login")
    @Test
    void loginTest() {

        step("fill login form and get request to login/Get authorization cookie", () -> {

            authorizationCookie = given()
                    .spec(request)
                    .filter(withCustomTemplates())
                    .formParam("Email", login)
                    .formParam("Password", password)
                    .when()
                    .post(BASE_URI + "/login")
                    .then()
                    .statusCode(302)
                    .extract()
                    .cookie("NOPCOMMERCE.AUTH");
        });

        step("check correct redirect after login", () -> {

            Response response = given()
                    .spec(request)
                    .cookie("NOPCOMMERCE.AUTH", authorizationCookie)
                    .when()
                    .get(BASE_URI)
                    .then()
                    .spec(responseSpec)
                    .body("html.head.title", is("Demo Web Shop"))
                    .extract()
                    .response();

            String str = response.asString();
            Document doc = Jsoup.parse(str);

            String text = Objects.requireNonNull(doc.select("a:contains(tester667@gmail.com)")
                    .first()).text();
            assertThat(text).isEqualTo("tester667@gmail.com");
        });
    }

    @Feature("JIRAPROJECT-26012")
    @Story("JIRAPROJECT-28000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("check login without email")
    @Test
    void loginWithOutEmailTest() {

        Response response =
                given()
                        .spec(request)
                        .formParam("Password", password)
                        .when()
                        .post(BASE_URI + "/login")
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .response();

        String str = response.asString();
        Document doc = Jsoup.parse(str);

        String errorText = Objects.requireNonNull(doc.select("li:contains(No customer account found)")
                .first()).text();
        assertThat(errorText).isEqualTo("No customer account found");

    }

    @Feature("JIRAPROJECT-26012")
    @Story("JIRAPROJECT-28000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("check login without password")
    @Test
    void loginWithOutPasswordTest() {

        Response response =
                given()
                        .spec(request)
                        .formParam("Email", login)
                        .when()
                        .post(BASE_URI + "/login")
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .response();

        String str = response.asString();
        Document doc = Jsoup.parse(str);

        String text = Objects.requireNonNull(doc.select("li:contains(The credentials provided are incorrect)")
                .first()).text();

        assertThat(text).isEqualTo("The credentials provided are incorrect");

    }

    @Feature("JIRAPROJECT-26012")
    @Story("JIRAPROJECT-28000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("check login without password and login")
    @Test
    void loginWithOutLoginAndPasswordTest() {

        Response response =
                given()
                        .spec(request)
                        .when()
                        .post(BASE_URI + "/login")
                        .then()
                        .spec(responseSpec)
                        .extract()
                        .response();

        String str = response.asString();
        Document doc = Jsoup.parse(str);

        String errorText = Objects.requireNonNull(doc.select("li:contains(No customer account found)")
                .first()).text();

        assertThat(errorText).isEqualTo("No customer account found");

    }
}
