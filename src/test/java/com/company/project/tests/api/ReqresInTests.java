package com.company.project.tests.api;

import com.company.project.model.list.ListUsers;
import com.company.project.model.list.SingleUser;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("api_reqres.in")
@DisplayName("check api https://reqres.in/")
public class ReqresInTests {

    @BeforeAll
    static void beforeAll() {
        baseURI = "https://reqres.in";
    }

    @ValueSource(ints = {1,2,3})
    @ParameterizedTest(name = "check all user attributes not null on page {0}")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    void checkAllUserAttributesNotNullTest(int pageNumber) {

        ListUsers listUsers = given()
                                    .queryParam("page", pageNumber)
                                    .when()
                                    .get("/api/users")
                                    .then()
                                    .log().status()
                                    .statusCode(200)
                                    .extract()
                                    .body()
                                    .as(ListUsers.class);

        assertAll("all users have attributes (email, first name, last name): ",

                listUsers.getData().stream().map(user -> () -> {

                    assertAll(

                            () -> assertThat(user.getEmail() != null)
                                    .withFailMessage(user.getId() + ", haven't email.")
                                    .isTrue(),

                            () -> assertThat( user.getFirstName()!= null)
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
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    void checkDefiniteUserTest(Long id, String email, String firstName, String lastName) {

        SingleUser singleUser = given()
                .when()
                .get("/api/users/" + id)
                .then()
                .log().status()
                .statusCode(200)
                .extract()
                .body()
                .as(SingleUser.class);

        assertAll("it's same user what expected on id " + id + " : ",

                () -> assertThat(singleUser.getData().getEmail())
                        .withFailMessage(singleUser.getData().getEmail() + ", expected email: " + email)
                        .isEqualTo(email),

                () -> assertThat(singleUser.getData().getFirstName())
                        .withFailMessage(singleUser.getData().getFirstName() + ", expected first name: " + firstName)
                        .isEqualTo(firstName),

                () -> assertThat(singleUser.getData().getLastName())
                        .withFailMessage(singleUser.getData().getLastName() + ", expected last name: " + lastName)
                        .isEqualTo(lastName)

        );
    }
}