package com.company.project.demowebshop.config;

import org.aeonbits.owner.Config;
import static org.aeonbits.owner.Config.*;

@Sources({

        "file:src/test/resources/auth.properties",
        "classpath:auth.properties"
})
public interface Authorization extends Config {

    @Key("Email")
    String getLogin();

    @Key("Password")
    String getPass();
}
