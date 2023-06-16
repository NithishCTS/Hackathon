package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.BaseClass;

public class Gift_Cards extends BaseClass {

	private By giftCardsButton_xpath = By.xpath("//*[@id=\"header\"]/section/div/ul[2]/li[3]/a");
	private By birthDayCard_xpath = By.xpath("//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]");
	private By amount_xpath = By.name("amount_select");
	private By giftNextButton_xpath = By.xpath("//button[text()='Next']");
	private By recipient_name_xpath = By.name("recipient_name");
	private By recipient_email_xpath = By.name("recipient_email");
	private By recipient_mobile_number_xpath = By.name("recipient_mobile_number");
	private By customer_name_xpath = By.name("customer_name");
	private By customer_email_xpath = By.name("customer_email");
	private By customer_mobile_number_xpath = By.name("customer_mobile_number");
	private By customer_address_xpath = By.name("customer_address");
	private By zip_xpath = By.name("zip");
	private By message_xpath = By.name("message");
	private By giftConfirmButton_xpath = By.xpath("//button[normalize-space()='Confirm']");

	public Gift_Cards customizeGiftCards() throws InterruptedException {
		// scroll
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].scrollIntoView();",
		 * driver.findElement(giftCardsButton_xpath));
		 */

		wait.until(ExpectedConditions.elementToBeClickable(giftCardsButton_xpath)).click();
		driver.findElement(birthDayCard_xpath).click();
		driver.findElement(amount_xpath).sendKeys("10000");
		TimeUnit.SECONDS.sleep(3);
		driver.findElement(giftNextButton_xpath).click();
		return this;
	}

	public Gift_Cards fillDetails() throws IOException {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\Gift details.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheet("Gift Details");

		// Recipient
		driver.findElement(recipient_name_xpath).sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
		driver.findElement(recipient_email_xpath).sendKeys(sheet.getRow(2).getCell(0).getStringCellValue());
		driver.findElement(recipient_mobile_number_xpath)
				.sendKeys("" + (long) sheet.getRow(3).getCell(0).getNumericCellValue());

		// Customer
		driver.findElement(customer_name_xpath).sendKeys(sheet.getRow(1).getCell(1).getStringCellValue());
		driver.findElement(customer_email_xpath).sendKeys(sheet.getRow(2).getCell(1).getStringCellValue());
		driver.findElement(customer_mobile_number_xpath)
				.sendKeys("" + (long) sheet.getRow(3).getCell(1).getNumericCellValue());
		driver.findElement(customer_address_xpath).sendKeys(sheet.getRow(4).getCell(1).getStringCellValue());
		driver.findElement(zip_xpath).sendKeys("" + (long) sheet.getRow(5).getCell(1).getNumericCellValue());
		driver.findElement(message_xpath).sendKeys(sheet.getRow(1).getCell(2).getStringCellValue());

		workbook.close();
		return this;
	}

	public Gift_Cards captureErrorMessage() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		//select click button
		driver.findElement(giftConfirmButton_xpath).click();
		
		TakesScreenshot capture = (TakesScreenshot) driver;
		File Src = capture.getScreenshotAs(OutputType.FILE);
		File Target = new File(System.getProperty("user.dir") + "\\Screenshot\\Error message.png");
		try {
			FileHandler.copy(Src, Target);
		} catch (IOException e) {
			System.out.println("File not Found...!");
		}
		String error = driver.findElement(By.id("ip_3177473671")).getAttribute("title");
		System.out.print("\nError Message: " + error.toUpperCase()+"\n");
		return this;
	}
}
