package pages;

import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

import utils.MetodosUtils;

public class DemoWebShopPage {

    @SuppressWarnings("unused")
	private WebDriver driver;
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
    }

    public void validateUserRegistration() {
    	System.out.println("validate user registration");
    	metodo.validateText("Your registration completed", "//div[contains(text(),'Your registration completed')]");
    }

    public void accessLoginPage() {
        System.out.println("Access login page");
        metodo.clickElementByXpath("//a[contains(text(),'Log in')]");
    }


}
