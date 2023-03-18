package com.company.project.demowebshop.tests;

import com.company.project.demowebshop.data.TestData;
import com.company.project.demowebshop.utils.RegisterClient;
import com.company.project.demowebshop.model.User;
import io.qameta.allure.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("JIRAPROJECT-28000 - https://demowebshop.tricentis.com")
@Story("JIRAPROJECT-26014 - add registration")
@Owner("chilikinow@gmail.com")
@Tags({@Tag("demowebshop"),@Tag("api")})
@DisplayName("check registration")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class RegisterTests extends TestBase {

    public static String requestVerificationToken;
    public static final RegisterClient client = new RegisterClient();
    TestData testData = new TestData();
    User user = User.builder()
            .gender(testData.getGender())
            .firstName(testData.getFirstName())
            .lastName(testData.getLastName())
            .email(testData.getEmail())
            .password(testData.getPassword())
            .build();

    @Test
    @DisplayName("check registration")
    @Severity(SeverityLevel.NORMAL)
    void registerTest() {

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

            assertThat(successText).isNotNull();
            assertThat(successText.html()).isEqualTo("Your registration completed");
        });
    }
}

