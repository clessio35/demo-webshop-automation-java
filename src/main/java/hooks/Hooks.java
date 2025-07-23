package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import config.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import report.ExtentReport;
import utils.MetodosUtils;
import utils.TestContext;

public class Hooks {

    private static Scenario currentScenario;
    private static ExtentReports extent = ExtentReport.getExtentReports();

    @Before
    public void beforeScenario(Scenario scenario) {
        currentScenario = scenario;
        TestContext.setScenario(scenario);
        MetodosUtils.resetScreenshotCounter();

        ExtentTest test = extent.createTest(scenario.getName());
        TestContext.setExtentTest(test); // Set no contexto global

        boolean isWeb = scenario.getSourceTagNames().stream()
                         .anyMatch(tag -> tag.equalsIgnoreCase("@web"));

        if (isWeb) {
            DriverManager.getDriver();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        ExtentTest test = TestContext.getExtentTest();

        if (scenario.isFailed()) {
            test.fail("❌ Scenario failed");
        } else {
            test.pass("✅ Scenario passed");
        }

        if (DriverManager.getDriver() != null) {
            DriverManager.quitDriver();
        }

        // ⚠️ NÃO dar flush aqui — o flush correto está no Runner, após todos os testes!
    }

    public static String getScenarioName() {
        if (currentScenario != null) {
            return currentScenario.getName().replaceAll("[^a-zA-Z0-9\\-_]", "_");
        }
        return "unknown_scenario";
    }
}
