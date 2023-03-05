package com.company.project.demowebshop.tests;

import com.company.project.demowebshop.utils.RegisterClient;
import com.company.project.demowebshop.model.User;
import io.qameta.allure.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("JIRAPROJECT-26014 - add registration")
@Story("JIRAPROJECT-28000 - https://demowebshop.tricentis.com")
@Owner("chilikinow@gmail.com")
@Tags({@Tag("demowebshop"),@Tag("api")})
@DisplayName("check registration")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class RegisterTests extends TestBase {

    public static String requestVerificationToken;
    public static final RegisterClient client = new RegisterClient();
    User user = null;

    @Test
    @DisplayName("check registration")
    @Severity(SeverityLevel.NORMAL)
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

            assertThat(successText).isNotNull();
            assertThat(successText.html()).isEqualTo("Your registration completed");
        });
    }
}

