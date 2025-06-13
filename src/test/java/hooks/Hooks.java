package hooks;

import config.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.MetodosUtils;
import utils.TestContext;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        DriverManager.getDriver();
        TestContext.setScenario(scenario);
        MetodosUtils.resetScreenshotCounter();
    }
    
    @After
    public void tearDown(Scenario scenario) {
        if (DriverManager.getDriver() != null) {
            if (scenario.isFailed()) {
                System.out.println("test fail");
            }
            DriverManager.quitDriver(); 
        }
    }


    
}
