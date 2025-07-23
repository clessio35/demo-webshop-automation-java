package pages;

import static config.DriverManager.getDriver;
import static org.junit.Assert.assertEquals;
import static utils.MetodosUtils.isElementVisible;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
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

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String company = faker.company().name();
    String address = faker.address().streetAddress();
    String state = faker.address().state();
    String city = faker.address().cityName();
    String zipCode = faker.address().zipCode();
    String phone = faker.phoneNumber().cellPhone();
    String email = faker.internet().emailAddress();
    String password = faker.internet().password(8, 12);
    
    
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
		System.out.println("access signup");
		isElementVisible(driver, By.xpath("//a[normalize-space()='Signup / Login']"));
		metodo.clickElementByXpath("//a[normalize-space()='Signup / Login']");
		WebElement inputNome = driver.findElement(By.xpath("//form[@action='/signup']//input[@name='name']"));
		WebElement inputEmail = driver.findElement(By.xpath("//form[@action='/signup']//input[@name='email']"));
		WebElement btnSignup = driver.findElement(By.xpath("//form[@action='/signup']//button[@type='submit']"));
		inputNome.sendKeys(firstName);
		inputEmail.sendKeys(email);
		MetodosUtils.takeStepScreenshot(getDriver(), Hooks.getScenarioName());
		btnSignup.click();
	}
	
	public void birth() {
		WebElement days = driver.findElement(By.id("days"));
		Select selectDays = new Select(days);
		selectDays.selectByVisibleText("1");
		WebElement month = driver.findElement(By.id("months"));
		Select selectMonth = new Select(month);
		selectMonth.selectByVisibleText("January");
		WebElement year = driver.findElement(By.id("years"));
		Select selectYear = new Select(year);
		selectYear.selectByVisibleText("1997");
	}

	public void fillRegistrationForm() {
		System.out.println("registration");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement el = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[normalize-space()='Enter Account Information']")));
		assertEquals("enter account information", el.getText().trim().toLowerCase());
		metodo.clickElementById("uniform-id_gender1");
		metodo.typeElement("password", password);
		birth();
		metodo.typeElement("first_name", firstName);
		metodo.typeElement("last_name", lastName);
		metodo.typeElement("company", company);
		metodo.typeElement("address1", address);
		WebElement countrySelect = driver.findElement(By.id("country"));
		Select select = new Select(countrySelect);
		select.selectByVisibleText("Australia");
		metodo.typeElement("state", state);
		metodo.typeElement("city", city);
		metodo.typeElement("zipcode", zipCode);
		metodo.typeElement("mobile_number", phone);
		MetodosUtils.saveData(firstName, lastName, email, password, address);
		MetodosUtils.takeStepScreenshot(getDriver(), Hooks.getScenarioName());
	}

	public void clickBtnCreateAccount() {
		System.out.println("create account");
		isElementVisible(driver, By.xpath("//button[normalize-space()='Create Account']"));
		metodo.clickElementByXpath("//button[normalize-space()='Create Account']");
		MetodosUtils.takeStepScreenshot(getDriver(), Hooks.getScenarioName());
	}

	public void validateAccountCreated(String msg) {
		System.out.println("validate account created");
		isElementVisible(driver, By.xpath("//b[normalize-space()='Account Created!']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement el = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[normalize-space()='Account Created!']")));
		assertEquals(msg.toUpperCase(), el.getText().trim().toUpperCase());
		MetodosUtils.takeStepScreenshot(getDriver(), Hooks.getScenarioName());
	}

	


}
