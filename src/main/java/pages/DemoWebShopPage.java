package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import config.DriverManager;
import junit.framework.Assert;
import utils.MetodosUtils;

public class DemoWebShopPage {

    WebDriver driver = DriverManager.getDriver();
    private MetodosUtils metodo;

    public DemoWebShopPage(WebDriver driver) {
        this.driver = driver;
        this.metodo = new MetodosUtils(driver);
    }
    
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String password = faker.internet().password(8, 12);
    
    public void accessRegisterPage() {
       System.out.println("access page register");
       metodo.clickElementByXpath("//a[contains(text(),'Register')]");
    }

    public void fillRegisterUser() {
       System.out.println("fill user register");
        metodo.clickElementById("gender-male");
        metodo.typeElement("FirstName", firstName);
        metodo.typeElement("LastName", lastName);
        metodo.typeElement("Email", email);
        metodo.typeElement("Password", password);
        metodo.typeElement("ConfirmPassword", password);
        metodo.clickElementByXpath("//input[@id='register-button']");
        MetodosUtils.saveData(firstName, lastName, email, password);
    }

    public void validateUserRegistration() {
    	System.out.println("validate user registration");
    	metodo.validateText("Your registration completed", "//div[contains(text(),'Your registration completed')]");
    }

    public void accessLoginPage() {
        System.out.println("Access login page");
        metodo.clickElementByXpath("//a[contains(text(),'Log in')]");
    }
    
    public void realizeLogin() {
    	System.out.println("log in...");
    	String email = MetodosUtils.readCell(1, "Email");
    	String password = MetodosUtils.readCell(1, "Password");
    	metodo.typeElement("Email", email);
    	metodo.typeElement("Password", password);
    	metodo.clickElementByXpath("//input[@value='Log in']");
    }

	@SuppressWarnings("deprecation")
	public void validateLogin() {
		System.out.println("validate Homepage");
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    try {
		        WebElement linkAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/customer/info']")));
		        Assert.assertTrue("Link 'customer info' deve estar visível", linkAccount.isDisplayed());
		    } catch (TimeoutException e) {
		        Assert.fail("Link 'customer info' não está visível na página após o tempo esperado");
		    }
	}


}
