package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DriverManager {

    private static WebDriver driver;
    private static final String CONFIG_FILE = "/config.properties";

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver",
                "C:\\workspace\\workspace-eclipse\\demo-web-shop-automation-java\\drivers\\chromedriver.exe");

            driver = new ChromeDriver();
            driver.manage().window().maximize();

            driver.get(getBaseUrl());
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static String getBaseUrl() {
        try (InputStream input = DriverManager.class.getResourceAsStream(CONFIG_FILE)) {
            Properties properties = new Properties();
            properties.load(input);
            String url = properties.getProperty("base.url");
            if (url == null || url.isEmpty()) {
                throw new RuntimeException("⚠️ base.url não configurado no config.properties!");
            }
            return url;
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("❌ Erro ao ler config.properties: " + CONFIG_FILE, e);
        }
    }
}
