package com.company.project.utils;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

public class InitSelenoid {

    public static String locationselenoid;

    static {

        locationselenoid = System.getProperty("selenoid.location");

        ConfigSelenoid configSelenoid = ConfigFactory.create(ConfigSelenoid.class, System.getProperties());
        Configuration.remote = configSelenoid.getSelenoidUrl();
        Configuration.browser = configSelenoid.getBrowserName();
        Configuration.browserVersion = configSelenoid.getBrowserVersion();
        Configuration.browserSize = configSelenoid.getBrowserSize();
        Configuration.timeout = configSelenoid.getBrowserTimeout();
        Configuration.headless = configSelenoid.isBrowserHeadless();
        Configuration.holdBrowserOpen = configSelenoid.isHoldBrowserOpen();

        Configuration.fileDownload = FOLDER;

        if (locationselenoid != null && locationselenoid.equals("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }

    }

}
