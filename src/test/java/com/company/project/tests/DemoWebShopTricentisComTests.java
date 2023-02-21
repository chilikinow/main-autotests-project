package com.company.project.tests;

import com.company.project.model.reqres.in.ListUsers;
import com.company.project.model.reqres.in.User;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Disabled
@Tag("api_demowebshop_tricentis_com")
@DisplayName("check api https://demowebshop.tricentis.com")
public class DemoWebShopTricentisComTests {

    @BeforeAll
    static void beforeAll() {
        baseURI = "https://demowebshop.tricentis.com";
    }

    @ValueSource(ints = {1,2,3})
    @ParameterizedTest(name = "check all user attributes not null on page {0}")
    @Feature("JIRAPROJECT-24121")
    @Story("JIRAPROJECT-27027")
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
    }

}
