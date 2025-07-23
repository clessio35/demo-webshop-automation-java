package pages;

import static config.DriverManager.getDriver;
import static org.junit.Assert.assertEquals;
import static utils.MetodosUtils.isElementVisible;
import static utils.MetodosUtils.readCell;
import static utils.MetodosUtils.saveData;
import static utils.MetodosUtils.takeStepScreenshot;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import config.DriverManager;
import hooks.Hooks;
import utils.MetodosUtils;

public class WebAutomationExercisePage {
	

    WebDriver driver = DriverManager.getDriver();
    private MetodosUtils metodo;

    public WebAutomationExercisePage(WebDriver driver) {
        this.driver = driver;
        this.metodo = new MetodosUtils(driver);
    }

	public void validateUrlAccess() {
		System.out.println("validate url");
		String url = "https://automationexercise.com/";
		String getUrl = DriverManager.getDriver().getCurrentUrl();
		Assert.assertTrue("URL incorreta ao acessar o site", getUrl.contains(url));
	}

	public void validateHomePage(String text) {
		System.out.println("validate home page");
		isElementVisible(driver, By.xpath("//a[normalize-space()='Home']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement el = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Home']")));
			assertEquals("Home", el.getText().trim());
			MetodosUtils.takeStepScreenshot(getDriver(), Hooks.getScenarioName());
	}

	public void accessSignupAndLogin() {
		// TODO Auto-generated method stub
		
	}

	


}
