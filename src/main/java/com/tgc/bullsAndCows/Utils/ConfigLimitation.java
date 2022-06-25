package com.tgc.bullsAndCows.Utils;

import com.tgc.bullsAndCows.entity.Limitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ConfigLimitation {

    private static Environment environment;

    @Autowired
    public ConfigLimitation(Environment environment) {
        ConfigLimitation.environment = environment;
    }

    public static Limitation getProperty() {
        switch (Objects.requireNonNull(environment.getProperty("limitation"))) {
            case "time":
                return Limitation.TIME;
            case "without":
                return Limitation.WITHOUT;
            case "steps":
                return Limitation.STEPS;
            default:
                throw new IllegalArgumentException();
        }
    }
}
