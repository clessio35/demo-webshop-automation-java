package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)  // JUnit 4
@CucumberOptions(
    features = "src/test/resources/features",   
    glue = {"steps", "hooks"},                  
    tags = "@login",                             
    plugin = {                                 
        "pretty", 
        "html:target/cucumber-reports", 
    }
)

public class RunnerTest {

}
