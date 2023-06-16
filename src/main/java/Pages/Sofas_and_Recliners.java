package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.BaseClass;

public class Sofas_and_Recliners extends BaseClass {
	
	private List<WebElement> sofas, recliners, sofaBed, chairs;
	
	private By sofasAndReclinders_Menu_xpath = By.xpath("//*[@id=\"topnav_wrapper\"]/ul/li[2]/span");
	private By sofas_xpath = By.xpath("//*[@id='topnav_wrapper']/ul/li[2]/div/div/ul/li[1]//ul//span");
	private By recliners_xpath = By.xpath("//*[@id='topnav_wrapper']/ul/li[2]/div/div/ul/li[2]//span");
	private By sofaBed_xpath = By.xpath("//*[@id='topnav_wrapper']/ul/li[2]/div/div/ul/li[3]//span");
	private By chairs_xpath = By.xpath("//*[@id='topnav_wrapper']/ul/li[2]/div/div/ul/li[4]//span");

	public Sofas_and_Recliners sofasAndReclinders_Menu() throws InterruptedException {
		/*
		 * JavascriptExecutor js = (JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].scrollIntoView();",
		 * driver.findElement(sofasAndReclinders_Menu_xpath));
		 */
		wait.until(ExpectedConditions.presenceOfElementLocated(sofasAndReclinders_Menu_xpath)).click();
		TimeUnit.SECONDS.sleep(1);
		return this;
	}

	public Sofas_and_Recliners extractSub_Menus() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(sofas_xpath));
		sofas = driver.findElements(sofas_xpath);
		recliners = driver.findElements(recliners_xpath);
		sofaBed = driver.findElements(sofaBed_xpath);
		chairs = driver.findElements(chairs_xpath);
		return this;
	}

	public Sofas_and_Recliners printSub_Menus() {
		System.out.println("\nSOFAS AND RECLINERS SUB-MENUS: ");
		System.out.println("Sofas :");
		for (int i = 0; i < sofas.size(); i++)
			System.out.println("  " + sofas.get(i).getText());

		System.out.println("Recliners :");
		for (int i = 0; i < recliners.size(); i++)
			System.out.println("  " + recliners.get(i).getText());

		System.out.println("Sofa Bed :");
		for (int i = 0; i < sofaBed.size(); i++)
			System.out.println("  " + sofaBed.get(i).getText());

		System.out.println("Seating & Chairs :");
		for (int i = 0; i < chairs.size(); i++)
			System.out.println("  " + chairs.get(i).getText());
		return this;
	}
}
