package com.company.project.tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.company.project.helpers.Attach;
import com.company.project.config.SelenideConfig;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

@Slf4j
public class UITestBase {

    private static String selenideLocation;

    @BeforeAll
    static void beforeAll() {

        selenideLocation = System.getProperty("selenide.location");

        SelenideConfig selenideConfig = ConfigFactory.create(SelenideConfig.class, System.getProperties());
        Configuration.remote = selenideConfig.getSelenoidUrl();
        Configuration.browser = selenideConfig.getBrowserName();
        Configuration.browserVersion = selenideConfig.getBrowserVersion();
        Configuration.browserSize = selenideConfig.getBrowserSize();
        Configuration.timeout = selenideConfig.getBrowserTimeout();
        Configuration.headless = selenideConfig.isBrowserHeadless();
        Configuration.holdBrowserOpen = selenideConfig.isHoldBrowserOpen();

        Configuration.fileDownload = FOLDER;

        if (selenideLocation != null && selenideLocation.equals("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;

        }
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach(){

        Selenide.closeWebDriver();

        if (selenideLocation.equals("remote")) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }
    }
}