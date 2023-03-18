package com.company.project.demowebshop.tests;

import com.company.project.demowebshop.data.TestData;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.company.project.specs.SpecBase.baseRequestSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Feature("JIRAPROJECT-28000 - https://demowebshop.tricentis.com")
@Story("JIRAPROJECT-26011 - add, add to card")
@Owner("chilikinow@gmail.com")
@Tags({@Tag("demowebshop"),@Tag("api")})
@DisplayName("check add, add to card")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AddToCardTests extends TestBase {

    @Test
    @DisplayName("check add to card")
    @Severity(SeverityLevel.NORMAL)
    void addGiftCardToCardAnonymTest() {

        TestData testData = new TestData();

        given()
                .spec(baseRequestSpec)
                .formParam("giftcard_1.RecipientName", testData.getFirstName())
                .formParam("giftcard_1.RecipientEmail", testData.getEmail())
                .formParam("giftcard_1.SenderName", testData.getFirstName())
                .formParam("giftcard_1.SenderEmail", testData.getEmail())
                .formParam("giftcard_1.Message", testData.faker.lorem().fixedString(10))
                .formParam("addtocart_1.EnteredQuantity", 1)
                .when()
                .post(BASE_URI + "/addproducttocart/details/1/1")
                .then()
                .statusCode(200)
                .body("success", is(true),
                        "message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

    }
}
