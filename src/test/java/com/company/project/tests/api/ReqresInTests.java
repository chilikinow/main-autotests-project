package com.company.project.tests.api;

import com.company.project.model.*;
import com.google.gson.JsonObject;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("api_reqres_in")
@DisplayName("check api https://reqres.in/")
public class ReqresInTests {

    @BeforeAll
    static void beforeAll() {
        baseURI = "https://reqres.in";
    }

    @ValueSource(ints = {1,2,3})
    @ParameterizedTest(name = "check all user attributes not null on page {0}")
    @Feature("JIRAPROJECT-25261")
    @Story("JIRAPROJECT-23077")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    void checkAllUserAttributesNotNullTest(int pageNumber) {

        List<User> userList = given()
                                    .queryParam("page", pageNumber)
                                    .when()
                                    .get("/api/users")
                                    .then()
                                    .log().status()
                                    .statusCode(200)
                                    .extract()
                                    .body()
                                    .as(ListUsers.class).getUsersList();

        assertAll("all users have attributes (email, first name, last name): ",

                userList.stream().map(user -> () -> {

                    assertAll(

                            () -> assertThat(user.getEmail() != null)
                                    .withFailMessage(user.getId() + ", haven't email.")
                                    .isTrue(),

                            () -> assertThat(user.getFirstName() != null)
                                    .withFailMessage(user.getId() + ", haven't first name.")
                                    .isTrue(),

                            () -> assertThat(user.getLastName() != null)
                                    .withFailMessage(user.getId() + ", haven't last name.")
                                    .isTrue()

                    );

                }));
    }

    @CsvFileSource(resources = "/users.csv")
    @ParameterizedTest(name = "check user id:{0}, exist")
    @Feature("JIRAPROJECT-25261")
    @Story("JIRAPROJECT-23077")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    void checkDefiniteUserTest(Long id, String email, String firstName, String lastName) {

        User user = given()
                            .when()
                            .get("/api/users/" + id)
                            .then()
                            .log().status()
                            .statusCode(200)
                            .extract()
                            .body()
                            .as(SingleUser.class)
                            .getUser();

        assertAll("it's same user what expected on id " + id + " : ",

                () -> assertThat(user.getEmail())
                        .withFailMessage(user.getEmail() + ", expected email: " + email)
                        .isEqualTo(email),

                () -> assertThat(user.getFirstName())
                        .withFailMessage(user.getFirstName() + ", expected first name: " + firstName)
                        .isEqualTo(firstName),

                () -> assertThat(user.getLastName())
                        .withFailMessage(user.getLastName() + ", expected last name: " + lastName)
                        .isEqualTo(lastName)

        );
    }

    @ValueSource(ints = {997, 998, 999})
    @ParameterizedTest(name = "check user id {0}, not exist")
    @Feature("JIRAPROJECT-25261")
    @Story("JIRAPROJECT-23077")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    void checkSingleUserNotFound(int id) {

        given()
                .when()
                .get("/api/users/" + id)
                .then()
                .log().status()
                .statusCode(404);

    }

    @CsvFileSource(resources = "/resources.csv")
    @ParameterizedTest(name = "check name {0} exist on resource page")
    @Feature("JIRAPROJECT-25261")
    @Story("JIRAPROJECT-23077")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    void checkNameExistOnResourcePageTest(String name) {

        List<Resource> resourcesList = given()
                                            .when()
                                            .get("/api/unknown")
                                            .then()
                                            .log().status()
                                            .statusCode(200)
                                            .extract()
                                            .body()
                                            .as(ListResources.class)
                                            .getResourcesList();

        List<String> nameResourcesList = resourcesList.stream().map(resource -> resource.getName()).collect(Collectors.toList());

        assertThat(nameResourcesList.contains(name))
                .withFailMessage(name + " not exist on resource page")
                .isTrue();
    }

    @Test
    @DisplayName("check user create successful")
    @Feature("JIRAPROJECT-25261")
    @Story("JIRAPROJECT-23077")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.CRITICAL)
    void checkUserCreateSuccessful() {

        String name = "morpheus";
        String job = "leader";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("job", job);

        given()
                .body(jsonObject)
                .when()
                .post("/api/users")
                .then()
                .log().status()
                .statusCode(201);
    }
}
