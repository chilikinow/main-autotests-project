package com.company.project.demowebshop.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static com.company.project.demowebshop.specs.SpecBase.*;

@Tag("demowebshop")
@DisplayName("check add to card on https://demowebshop.tricentis.com")
public class AddToCardTests extends TestBase {

    @Feature("JIRAPROJECT-26011")
    @Story("JIRAPROJECT-28000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("check add to card")
    @Test
    void addGiftCardToCardAnonymTest() {

        given()
                .spec(request)
                .formParam("giftcard_1.RecipientName", firstName)
                .formParam("giftcard_1.RecipientEmail", email)
                .formParam("giftcard_1.SenderName", firstName)
                .formParam("giftcard_1.SenderEmail", email)
                .formParam("giftcard_1.Message", faker.lorem().fixedString(10))
                .formParam("addtocart_1.EnteredQuantity", 1)
                .when()
                .post("/addproducttocart/details/1/1")
                .then()
                .statusCode(200)
                .body("success", is(true),
                        "message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

    }
}