package com.company.project.reqres.tests;

import com.company.project.reqres.models.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.project.specs.SpecBase.*;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.Matchers.hasItem;

@Feature("JIRAPROJECT-21011 - add api interface")
@Story("JIRAPROJECT-21000 - https://reqres.in")
@Owner("chilikinow@gmail.com")
@Tags({@Tag("reqres"),@Tag("api")})
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("check api")
public class ReqresTests extends TestBase{

    public static final String USERS_ENDPOINT = "/users/";
    public static final String UNKNOWN_ENDPOINT = "/unknown/";

    @ValueSource(ints = {1,2,3})
    @ParameterizedTest(name = "check all user attributes not null on page: {arguments}")
    @Severity(SeverityLevel.NORMAL)
    void checkAllUserAttributesNotNullTest(int pageNumber) {

        List<User> userList = given()
                .spec(baseRequestSpec)
                .queryParam("page", pageNumber)
                .when()
                .get(BASE_URI + USERS_ENDPOINT)
                .then()
                .spec(baseResponseSpec)
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
    @ParameterizedTest(name = "check definite user exist")
    @Severity(SeverityLevel.NORMAL)
    void checkDefiniteUserTest(Long id, String email, String firstName, String lastName) {

        User user = given()
                .spec(baseRequestSpec)
                .when()
                .get(BASE_URI + USERS_ENDPOINT + id)
                .then()
                .spec(baseResponseSpec)
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
    @ParameterizedTest(name = "check user {arguments} not exist")
    @Severity(SeverityLevel.NORMAL)
    void checkSingleUserNotFound(int id) {

        given()
                .spec(baseRequestSpec)
                .when()
                .get(BASE_URI + USERS_ENDPOINT + id)
                .then()
                .spec(baseResponseSpec)
                .statusCode(404);
    }

    @CsvFileSource(resources = "/resources.csv")
    @ParameterizedTest(name = "check name {0} exist on resource page")
    @Severity(SeverityLevel.NORMAL)
    void checkNameExistOnResourcePageTest(String name) {

        List<Resource> resourcesList = given()
                .spec(baseRequestSpec)
                .when()
                .get(BASE_URI + UNKNOWN_ENDPOINT)
                .then()
                .spec(baseResponseSpec)
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

    @DisplayName("check user create successful")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void checkUserCreateSuccessful() {

//        String name = "morpheus";
//        String job = "leader";
//
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("name", name);
//        jsonObject.addProperty("job", job);

        User user = User.builder()
                .name("morpheus")
                .job("leader")
                .build();

        given()
                .spec(baseRequestSpec)
                .body(user)
                .when()
                .post(BASE_URI + USERS_ENDPOINT)
                .then()
                .spec(baseResponseSpec)
                .statusCode(201);
    }

    @DisplayName("check email with groovy")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void checkEmailTest() {
        given()
                .spec(baseRequestSpec)
                .when()
                .get(BASE_URI + USERS_ENDPOINT)
                .then()
                .spec(baseResponseSpec)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("emma.wong@reqres.in"));
    }
}
