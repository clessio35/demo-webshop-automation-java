package config;

import org.openqa.selenium.WebDriver;

public class TesteChromeDriver {
    public static void main(String[] args) {
        WebDriver driver = DriverManager.getDriver();

        driver.get("https://demowebshop.tricentis.com");

        System.out.println("Título da página: " + driver.getTitle());

        DriverManager.quitDriver();
    }
}
