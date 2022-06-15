package com.tgc.bullsAndCows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigLimitation {

    private static Environment environment;

    @Autowired
    public ConfigLimitation(Environment environment) {
        ConfigLimitation.environment = environment;
    }

    public static String getProperty() {
        return environment.getProperty("limitation");
    }
}
