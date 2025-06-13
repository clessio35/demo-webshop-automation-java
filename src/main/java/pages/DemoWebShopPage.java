package pages;

import static config.DriverManager.getDriver;
import static org.junit.Assert.assertEquals;
import static utils.MetodosUtils.isElementVisible;
import static utils.MetodosUtils.readCell;
import static utils.MetodosUtils.saveData;
import static utils.MetodosUtils.takeStepScreenshot;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import config.DriverManager;
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
        takeStepScreenshot(DriverManager.getDriver(), "fill user register");
        metodo.clickElementByXpath("//input[@id='register-button']");
        saveData(firstName, lastName, email, password, "");
    }

    public void validateUserRegistration() {
    	System.out.println("validate user registration");
    	metodo.validateText("Your registration completed", "//div[contains(text(),'Your registration completed')]");
    	takeStepScreenshot(DriverManager.getDriver(), "Validate User registration");
    }

    public void accessLoginPage() {
        System.out.println("Access login page");
        metodo.clickElementByXpath("//a[contains(text(),'Log out')]");
        metodo.clickElementByXpath("//a[contains(text(),'Log in')]");
    }
    
    public void realizeLogin() {
    	System.out.println("log in...");
    	String email = readCell(1, "Email");
    	String password = readCell(1, "Password");
    	metodo.typeElement("Email", email);
    	metodo.typeElement("Password", password);
    	takeStepScreenshot(DriverManager.getDriver(), "login");
    	metodo.clickElementByXpath("//input[@value='Log in']");
    }

	public void validateLogin() {
		System.out.println("validate Homepage");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement linkAccount = wait
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//a[@href='/customer/info']")));
			if(linkAccount.isDisplayed()) {
				assertEquals(linkAccount.getText().trim(), email);
			}
			
		takeStepScreenshot(DriverManager.getDriver(), "Validate Login");
	}

	public void searchItems() {
		System.out.println("search itens");
		String product = "14.1-inch Laptop";
		saveData(firstName, lastName, email, password, product);
		metodo.typeElement("small-searchterms", product);
		isElementVisible(driver, By.cssSelector("#ui-id-1"));
		metodo.clickElementByXpath("//input[@class='button-1 search-box-button']");
	}

	public void validateResultsBySearch() {
		System.out.println("validate results");
		isElementVisible(driver, By.xpath("//h1[contains(text(),'Search')]"));
		isElementVisible(driver, By.xpath("//h2[@class='product-title']"));
		takeStepScreenshot(DriverManager.getDriver(), "Validate results");
	}

	public void addToCart() {
		System.out.println("Add to cart");
		 List<WebElement> addToCart = driver.findElements(By.cssSelector("input[value='Add to cart']"));
	        if (!addToCart.isEmpty()) {
	        	addToCart.get(0).click();
	            System.out.println("first button select");
	        } else {
	            System.out.println("not found");
	        }
		takeStepScreenshot(DriverManager.getDriver(), "Add to cart");
	}

	public void validateCart() {
		System.out.println("validate cart");
		isElementVisible(driver, By.id("bar-notification"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement text = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='bar-notification']")));
			assertEquals("The product has been added to your shopping cart", text.getText().trim());
		isElementVisible(driver, By.id("topcartlink"));
		takeStepScreenshot(DriverManager.getDriver(), "Validate cart");
	}

	public void accessCart() {
		System.out.println("access to cart");
		isElementVisible(driver, By.xpath("//span[contains(text(),'Shopping cart')]"));
		metodo.clickElementByXpath("//span[contains(text(),'Shopping cart')]");
	}

	public void removeProduct() {
		System.out.println("remove product"); 
		isElementVisible(driver, By.xpath("//h1[contains(text(),'Shopping cart')]"));
		takeStepScreenshot(DriverManager.getDriver(), "Shopping cart");
		metodo.clickElementByXpath("//input[@name='removefromcart']");
		isElementVisible(driver, By.xpath("//input[@name='updatecart']"));
		metodo.clickElementByXpath("//input[@name='updatecart']");
	}

	public void validateEmptyCart() {
		System.out.println("Validate empty cart");
		By element = By.xpath("//div[contains(text(),'Your Shopping Cart is empty!')]");
		isElementVisible(driver, element);
		WebElement el = driver.findElement(element);
		assertEquals("Your Shopping Cart is empty!", el.getText().trim());
		takeStepScreenshot(getDriver(), "cart is empty!");
	}


}
