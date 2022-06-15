package com.tgc.bullsAndCows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigUnit {

    private static Environment environment;

    @Autowired
    public ConfigUnit(Environment environment) {
        ConfigUnit.environment = environment;
    }

    public static String getProperty() {
        return environment.getProperty("limitation");
    }
}
