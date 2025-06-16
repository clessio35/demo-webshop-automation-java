package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DriverManager {

    private static WebDriver driver;
    private static final String CONFIG_FILE = "/config.properties";

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();

            // Headless para CI via flag -Dheadless=true
            if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--window-size=1920,1080");
            }

            // Detecta se está rodando localmente (Windows) ou CI (Linux)
            String osName = System.getProperty("os.name").toLowerCase();

            if (osName.contains("win")) {
                // Windows local: usa chromedriver local (exemplo: drivers/chromedriver.exe)
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            } else {
                // CI Linux: usa WebDriverManager para baixar driver compatível
                WebDriverManager.chromedriver().setup();
            }

            driver = new ChromeDriver(options);
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
