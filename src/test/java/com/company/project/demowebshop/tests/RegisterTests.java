package com.company.project.demowebshop.tests;

import com.company.project.demowebshop.utils.RegisterClient;
import com.company.project.demowebshop.model.User;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("demowebshop")
@DisplayName("check registration on https://demowebshop.tricentis.com")
public class RegisterTests extends TestBase {

    public static String requestVerificationToken;
    public static final RegisterClient client = new RegisterClient();
    User user = null;

    @BeforeAll
    static void setUp() {
        baseURI = "https://demowebshop.tricentis.com";
    }

    @Feature("JIRAPROJECT-26014")
    @Story("JIRAPROJECT-28000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("check registration")
    @Test
    void registerTest() {

        step("set data for user model", () -> {

            user = User.builder()
                    .gender(gender)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .build();
        });

        step("go to registration page/get token for new user", () -> {

            requestVerificationToken = client.getVerifyToken()
                    .htmlPath()
                    .getString("**.find{it.@name == '__RequestVerificationToken'}.@value");
        });

        step("create new user", () -> {

            client.createNewUser(user);
        });

        step("check success redirect", () -> {

            client.successRedirect(user);

            Document doc = Jsoup.parse(client.successRedirect(user).asString());
            Element successText = doc.select("div:containsOwn(Your registration completed)")
                    .first();

            assert successText != null;
            assertThat(successText.html()).isEqualTo("Your registration completed");
        });
    }
}

