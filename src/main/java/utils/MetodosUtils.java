package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	private static final String FILE_PATH = "src/main/resources/dadosUsuarios.xlsx"; // Corrigido

	// Salvar dados no Excel
	public static void saveData(String firstName, String lastName, String email, String password) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Usuarios");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("FirstName");
		header.createCell(1).setCellValue("LastName");
		header.createCell(2).setCellValue("Email");
		header.createCell(3).setCellValue("Password");

		Row row = sheet.createRow(1);
		row.createCell(0).setCellValue(firstName);
		row.createCell(1).setCellValue(lastName);
		row.createCell(2).setCellValue(email);
		row.createCell(3).setCellValue(password);

		try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Ler célula específica por linha e nome da coluna
	@SuppressWarnings("deprecation")
	public static String readCell(int rowIndex, String columnName) {
		try (FileInputStream fis = new FileInputStream(FILE_PATH);
			 Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet("Usuarios");
			Row headerRow = sheet.getRow(0);
			Row dataRow = sheet.getRow(rowIndex);

			if (headerRow == null || dataRow == null) return null;

			for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
				String header = headerRow.getCell(i).getStringCellValue();
				if (header.equalsIgnoreCase(columnName)) {
					Cell cell = dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					return cell.getStringCellValue();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// WebDriver helper methods
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
