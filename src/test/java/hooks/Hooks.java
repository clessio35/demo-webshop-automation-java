package hooks;

import config.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario() {
        DriverManager.getDriver();
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
