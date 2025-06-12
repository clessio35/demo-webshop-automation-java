package utils;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MetodosUtils {

	private WebDriver driver;

	public MetodosUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void clickElementByXpath(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By locator = By.xpath(xpath);
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		elemento.click();
	}

	public void clickElementById(String id) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By locator = By.id(id);
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		elemento.click();
	}

	public void typeElement(String locatorString, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By locator = getByFromString(locatorString);
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		elemento.clear();
		elemento.sendKeys(text);
	}

	private By getByFromString(String locatorString) {
		if (locatorString.startsWith("id=")) {
			return By.id(locatorString.substring(3));
		} else if (locatorString.startsWith("xpath=")) {
			return By.xpath(locatorString.substring(6));
		} else {
			return By.id(locatorString);
		}
	}

	public void validateText(String expectedText, String element) {
		WebElement report = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		String text = report.getText().trim();
		Assert.assertEquals(expectedText, text);
	}
	
	
}
