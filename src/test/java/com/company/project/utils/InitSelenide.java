package com.company.project.utils;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

public class InitSelenide {

    public static String selenideLocation;

    static {

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

}
