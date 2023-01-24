package com.company.project.config;

import org.aeonbits.owner.Config;
import static org.aeonbits.owner.Config.*;

@Sources({"file:src/test/resources/config/${selenide.location}.properties",
        "classpath:config/${selenide.location}.properties"})
public interface SelenideConfig extends Config {

    @Key("selenoid.url")
    String getSelenoidUrl();

    @Key("browser.name")
    String getBrowserName();

    @Key("browser.version")
    String getBrowserVersion();

    @Key("browser.size")
    @DefaultValue("1024x768")
    String getBrowserSize();

    @Key("browser.timeout")
    @DefaultValue("10000")
    Long getBrowserTimeout();

    @Key("browser.headless")
    @DefaultValue("false")
    boolean isBrowserHeadless();

    @Key("browser.hold.open")
    @DefaultValue("false")
    boolean isHoldBrowserOpen();

}