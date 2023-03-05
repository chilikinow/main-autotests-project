package com.company.project.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.company.project.utils.InitSelenoid;
import com.company.project.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@Slf4j
public class UiTestBase extends InitSelenoid {

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach(){

        if (InitSelenoid.locationSelenoid != null && InitSelenoid.locationSelenoid.equals("remote")) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }

        Selenide.closeWebDriver();
    }

}