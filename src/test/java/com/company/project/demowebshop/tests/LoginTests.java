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
import static com.company.project.specs.SpecBase.baseRequestSpec;
import static com.company.project.specs.SpecBase.baseResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@Story("JIRAPROJECT-28000 - https://demowebshop.tricentis.com")
@Owner("chilikinow@gmail.com")
@Tags({@Tag("demowebshop"),@Tag("api")})
@DisplayName("check login")
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

    @Test
    @DisplayName("check login")
    @Feature("JIRAPROJECT-26012 - add login")
    @Severity(SeverityLevel.NORMAL)
    void loginTest() {

        step("fill login form and get request to login/Get authorization cookie", () -> {

            authorizationCookie = given()
                    .spec(baseRequestSpec)
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
                    .spec(baseRequestSpec)
                    .cookie("NOPCOMMERCE.AUTH", authorizationCookie)
                    .when()
                    .get(BASE_URI)
                    .then()
                    .spec(baseResponseSpec)
                    .statusCode(200)
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

    @Test
    @DisplayName("check login without email")
    @Feature("JIRAPROJECT-26012 - add login")
    @Severity(SeverityLevel.NORMAL)
    void loginWithOutEmailTest() {

        Response response =
                given()
                        .spec(baseRequestSpec)
                        .formParam("Password", password)
                        .when()
                        .post(BASE_URI + "/login")
                        .then()
                        .spec(baseResponseSpec)
                        .statusCode(200)
                        .extract()
                        .response();

        String str = response.asString();
        Document doc = Jsoup.parse(str);

        String errorText = Objects.requireNonNull(doc.select("li:contains(No customer account found)")
                .first()).text();
        assertThat(errorText).isEqualTo("No customer account found");

    }

    @Test
    @DisplayName("check login without password")
    @Feature("JIRAPROJECT-26012 - add login")
    @Severity(SeverityLevel.NORMAL)
    void loginWithOutPasswordTest() {

        Response response =
                given()
                        .spec(baseRequestSpec)
                        .formParam("Email", login)
                        .when()
                        .post(BASE_URI + "/login")
                        .then()
                        .spec(baseResponseSpec)
                        .statusCode(200)
                        .extract()
                        .response();

        String str = response.asString();
        Document doc = Jsoup.parse(str);

        String text = Objects.requireNonNull(doc.select("li:contains(The credentials provided are incorrect)")
                .first()).text();

        assertThat(text).isEqualTo("The credentials provided are incorrect");

    }

    @Test
    @DisplayName("check login without password and login")
    @Feature("JIRAPROJECT-26012 - add login")
    @Severity(SeverityLevel.NORMAL)
    void loginWithOutLoginAndPasswordTest() {

        Response response =
                given()
                        .spec(baseRequestSpec)
                        .when()
                        .post(BASE_URI + "/login")
                        .then()
                        .spec(baseResponseSpec)
                        .statusCode(200)
                        .extract()
                        .response();

        String str = response.asString();
        Document doc = Jsoup.parse(str);

        String errorText = Objects.requireNonNull(doc.select("li:contains(No customer account found)")
                .first()).text();

        assertThat(errorText).isEqualTo("No customer account found");

    }
}
