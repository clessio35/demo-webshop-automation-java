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
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
    String city = faker.address().cityName();
    String address = faker.address().streetAddress();
    String zipCode = faker.address().zipCode();
    String phone = faker.phoneNumber().cellPhone();
    String cardNumber = "4111111111111111";
    String ccv = faker.business().creditCardExpiry();
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
	    try {
	        WebElement addToCartBtn = driver.findElement(By.cssSelector("input[value='Add to cart']"));
	        addToCartBtn.click();
	        System.out.println("Clicked add to cart button");
	    } catch (StaleElementReferenceException e) {
	        System.out.println("Stale element, trying again...");
	        WebElement addToCartBtn = driver.findElement(By.cssSelector("input[value='Add to cart']"));
	        addToCartBtn.click();
	        System.out.println("Clicked add to cart button on retry");
	    }
	    takeStepScreenshot(DriverManager.getDriver(), "Add to cart");
	}


	public void validateCart() {
		System.out.println("validate cart");
		isElementVisible(driver, By.id("bar-notification"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
	
	public void billingAddress() {
		System.out.println("billing address");
		isElementVisible(getDriver(), By.id("co-billing-form"));
		WebElement countrySelect = driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"));
		Select select = new Select(countrySelect);
		select.selectByVisibleText("Brazil");
		metodo.typeElement("BillingNewAddress_City", city);
		metodo.typeElement("BillingNewAddress_Address1", address);
		metodo.typeElement("BillingNewAddress_ZipPostalCode", zipCode);
		metodo.typeElement("BillingNewAddress_PhoneNumber", phone);
		takeStepScreenshot(getDriver(), "billing address");
		metodo.clickElementByXpath("//input[@class='button-1 new-address-next-step-button']");
	}
	public void shippingAddress() {
		System.out.println("shipping address");
		takeStepScreenshot(getDriver(), "shipping address");
		isElementVisible(getDriver(), By.id("PickUpInStore"));
		metodo.clickElementById("PickUpInStore");
		List<WebElement> btn = driver.findElements(By.cssSelector("input[value='Continue']"));
        if (!btn.isEmpty()) {
        	btn.get(1).click();
            System.out.println("second button select");
        } else {
            System.out.println("not found");
        }
	}
	public void paymentMethod() {
		System.out.println("payment method");
		isElementVisible(getDriver(), By.id("checkout-step-payment-method"));
		metodo.clickElementById("paymentmethod_2");
		takeStepScreenshot(getDriver(), "payment method");
		metodo.clickElementByXpath("//input[@class='button-1 payment-method-next-step-button']");
	}
	public void paymentInformation() {
		System.out.println("payment information");
		isElementVisible(getDriver(), By.id("checkout-step-payment-info"));
		metodo.typeElement("CardholderName", firstName);
		metodo.typeElement("CardNumber", cardNumber);
		WebElement month = driver.findElement(By.id("ExpireMonth"));
		Select selectMonth = new Select(month);
		selectMonth.selectByVisibleText("12");
		WebElement year = driver.findElement(By.id("ExpireYear"));
		Select selectYear = new Select(year);
		selectYear.selectByVisibleText("2036");
		metodo.typeElement("CardCode", ccv);
		takeStepScreenshot(getDriver(), "payment information");
		metodo.clickElementByXpath("//input[@class='button-1 payment-info-next-step-button']");
	}
	public void confirmOrder() {
		System.out.println("confirm order");
		isElementVisible(getDriver(), By.id("checkout-step-confirm-order"));
		isElementVisible(getDriver(), By.xpath("//th[@class='cart-header-row']"));
		takeStepScreenshot(getDriver(), "confirm order");
		metodo.clickElementByXpath("//input[@class='button-1 confirm-order-next-step-button']");
	}

	public void realizeCheckout() {
		System.out.println("realize checkout");
		isElementVisible(driver, By.id("termsofservice"));
		metodo.clickElementById("termsofservice");
		isElementVisible(driver, By.id("checkout"));
		metodo.clickElementById("checkout");
		billingAddress();
		shippingAddress();
		paymentMethod();
		paymentInformation();
		confirmOrder();
	}
	
	public void validateOrderProcessed() {
	    System.out.println("validate order");
	    By element = By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	    String actualText = el.getText().trim().replaceAll("\\s+", " ");
	    System.out.println("Texto real encontrado: '" + actualText + "'");
	    assertEquals("Your order has been successfully processed!", actualText);
	}




}
