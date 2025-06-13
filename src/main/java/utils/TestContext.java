package utils;

import io.cucumber.java.Scenario;

public class TestContext {
    private static Scenario scenario;

    public static void setScenario(Scenario s) {
        scenario = s;
    }

    public static Scenario getScenario() {
        return scenario;
    }
}
