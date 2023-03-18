package com.company.project.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.company.project.utils.ConfigSelenoid;
import com.company.project.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

@Slf4j
public class UiTestBase {

    public static String locationSelenoid;

    static {

        if (System.getProperty("selenoid.location") != null) {
            locationSelenoid = System.getProperty("selenoid.location");
        }else{
            locationSelenoid = "remote";
        }

        ConfigSelenoid configSelenoid = ConfigFactory.create(ConfigSelenoid.class, System.getProperties());
        Configuration.remote = configSelenoid.getSelenoidUrl();
        Configuration.browser = configSelenoid.getBrowserName();
        Configuration.browserVersion = configSelenoid.getBrowserVersion();
        Configuration.browserSize = configSelenoid.getBrowserSize();
        Configuration.timeout = configSelenoid.getBrowserTimeout();
        Configuration.headless = configSelenoid.isBrowserHeadless();
        Configuration.holdBrowserOpen = configSelenoid.isHoldBrowserOpen();

        Configuration.fileDownload = FOLDER;

        if (locationSelenoid != null && locationSelenoid.equals("remote")) {

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

        if (locationSelenoid != null && locationSelenoid.equals("remote")) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }

        Selenide.closeWebDriver();
    }

}