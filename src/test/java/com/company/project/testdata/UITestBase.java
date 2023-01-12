package com.company.project.testdata;

import com.codeborne.selenide.Configuration;
import com.company.project.helpers.Attach;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class UITestBase {

    private static String selenideLocation;

    @BeforeAll
    static void beforeAll() {

        System.setProperty("selenide.location", "local");

        selenideLocation = System.getProperty("selenide.location");

        SelenideConfig selenideConfig = ConfigFactory.create(SelenideConfig.class, System.getProperties());
        Configuration.remote = selenideConfig.getSelenoidUrl();
        Configuration.browser = selenideConfig.getBrowserName();
        Configuration.browserVersion = selenideConfig.getBrowserVersion();
        Configuration.browserSize = selenideConfig.getBrowserSize();
        Configuration.timeout = selenideConfig.getBrowserTimeout();
        Configuration.headless = selenideConfig.isBrowserHeadless();

        if (selenideLocation.equals("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;

        }

    }

    @AfterEach
    void afterEach(){

        if (selenideLocation.equals("remote")) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }
    }


}