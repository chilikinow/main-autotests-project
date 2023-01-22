package com.company.project.config;

import org.aeonbits.owner.Config;
import static org.aeonbits.owner.Config.*;

@Sources({"file:src/test/resources/config/${config.location}.properties",
        "classpath:config/${config.location}.properties"})
public interface SelenideConfig extends Config {

    @Key("selenoid.url")
    String getSelenoidUrl();

    @Key("browser.name")
    String getBrowserName();

    @Key("browser.version")
    @DefaultValue("104.0")
    String getBrowserVersion();

    @Key("browser.size")
    @DefaultValue("1024x768")
    String getBrowserSize();

    @Key("browser.timeout")
    Long getBrowserTimeout();

    @Key("browser.headless")
    boolean isBrowserHeadless();

    @Key("browser.hold.open")
    boolean isHoldBrowserOpen();

}