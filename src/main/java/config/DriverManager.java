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

            String osName = System.getProperty("os.name").toLowerCase();
            System.out.println("[DriverManager] OS detectado: " + osName);

            if (osName.contains("win")) {
                // Windows local: chromedriver.exe local
                System.out.println("[DriverManager] Usando chromedriver local para Windows");
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            } else {
                // Linux (CI): tenta baixar chromedriver compatível automaticamente
                System.out.println("[DriverManager] Usando WebDriverManager para baixar ChromeDriver no CI");

                try {
                    // Forçar a versão para evitar erros de incompatibilidade
                    // Aqui tentamos pegar a versão do Chrome instalada (melhor prática)
                    String chromeVersion = getChromeVersion();
                    if (chromeVersion != null) {
                        System.out.println("[DriverManager] Versão do Chrome no CI: " + chromeVersion);
                        String majorVersion = chromeVersion.split("\\.")[0];
                        WebDriverManager.chromedriver().driverVersion(getCompatibleDriverVersion(majorVersion)).setup();
                    } else {
                        // fallback simples: baixa driver mais recente
                        WebDriverManager.chromedriver().setup();
                    }
                } catch (Exception e) {
                    System.err.println("[DriverManager] Erro ao configurar WebDriverManager: " + e.getMessage());
                    WebDriverManager.chromedriver().setup();
                }
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

    private static String getChromeVersion() {
        try {
            Process process = new ProcessBuilder("google-chrome", "--version").start();
            try (java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A")) {
                String output = s.hasNext() ? s.next() : "";
                // Exemplo de saída: "Google Chrome 137.0.7151.103"
                if (output.toLowerCase().contains("google chrome")) {
                    return output.replaceAll("[^0-9\\.]", "");
                }
            }
        } catch (IOException e) {
            System.err.println("[DriverManager] Não conseguiu detectar versão do Chrome: " + e.getMessage());
        }
        return null;
    }

    // Mapeia versão major do Chrome para uma versão conhecida do ChromeDriver
    private static String getCompatibleDriverVersion(String chromeMajorVersion) {
        switch (chromeMajorVersion) {
            case "137":
                return "137.0.7151.41";
            case "136":
                return "136.0.8358.46";
            case "135":
                return "135.0.6396.63";
            case "134":
                return "134.0.6546.61";
            case "114":
                return "114.0.5735.90";
            default:
                System.out.println("[DriverManager] Versão Chrome desconhecida, usando driver padrão");
                return ""; // WebDriverManager pegará driver latest
        }
    }
}
