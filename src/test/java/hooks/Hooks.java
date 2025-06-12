package hooks;

import config.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void beforeScenario() {
        DriverManager.getDriver();
    }
    
    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        if (DriverManager.getDriver() != null) {
            if (scenario.isFailed()) {
                System.out.println("test fail");
            }
            DriverManager.quitDriver(); 
        }
    }


    
}
