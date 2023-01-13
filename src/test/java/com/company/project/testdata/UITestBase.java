package com.company.project.testdata;

import com.codeborne.selenide.Configuration;
import com.company.project.helpers.Attach;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

@Slf4j
public class UITestBase {

    private static String selenideLocation;

    @BeforeAll
    static void beforeAll() {

        Configuration.remote = System.getProperty("selenoid.url");
        Configuration.browser = System.getProperty("selenide.browser.name");
        Configuration.browserVersion = System.getProperty("selenide.browser.version");
        Configuration.browserSize = System.getProperty("selenide.browser.size");
        Configuration.timeout = Integer.valueOf(System.getProperty("selenide.browser.timeout"), 10000);
        Configuration.headless = Boolean.valueOf(System.getProperty("selenide.browser.headless", "false"));
        Configuration.holdBrowserOpen = Boolean.valueOf(System.getProperty("selenide.hold.browser.open", "false"));

        selenideLocation = System.getProperty("selenide.location", "local");
//
//        SelenideConfig selenideConfig = ConfigFactory.create(SelenideConfig.class, System.getProperties());
//        Configuration.remote = selenideConfig.getSelenoidUrl();
//        Configuration.browser = selenideConfig.getBrowserName();
//        Configuration.browserVersion = selenideConfig.getBrowserVersion();
//        Configuration.browserSize = selenideConfig.getBrowserSize();
//        Configuration.timeout = selenideConfig.getBrowserTimeout();
//        Configuration.headless = selenideConfig.isBrowserHeadless();
//        Configuration.holdBrowserOpen = selenideConfig.isHoldBrowserOpen();

        Configuration.fileDownload = FOLDER;

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