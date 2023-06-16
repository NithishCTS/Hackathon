package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.BaseClass;

public class BookShelves extends BaseClass {

	private By searchBox_xpath = By.id("search");
	private By popupCloseButton_xpath = By.xpath("//*[@id='authentication_popup']/div[1]/div/div[2]/a[1]");
	private By priceButton_xpath = By.xpath("//li[@class= 'item' and @data-group='price']");
	private By RightSlider_xpath = By.xpath("//div[@class='noUi-origin' and @style ='left: 100%;']");
	private By category_xpath = By.xpath("//li[@data-group='category']/div[1]");
	private By wallShelves_xpath = By.xpath("//input[@id='filters_primary_category_Wall_Shelves']");
	private By excludeOutOfStock_xpath = By.xpath("//input[@id='filters_availability_In_Stock_Only']");
	private By bookNames_xpath = By.xpath("//span[@itemprop='name']");
	private By bookPrice_xpath = By.xpath("//div[@class='price-number']/span");

	public BookShelves searchBookShelves() {
		WebElement searchBox_Element = driver.findElement(searchBox_xpath);
		searchBox_Element.sendKeys("Bookshelves",Keys.ENTER);
		//searchBox_Element.sendKeys(Keys.ENTER);
		popupClosing();
		return this;
	}

	private void popupClosing() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(popupCloseButton_xpath)).click();
		} catch (Exception e) {
			// Nothing to display...
		}
	}

	public BookShelves price() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(priceButton_xpath)).click();
		TimeUnit.SECONDS.sleep(2);
		Actions actions = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(RightSlider_xpath));
		WebElement leftSlider_Element = driver.findElement(RightSlider_xpath);
		actions.dragAndDropBy(leftSlider_Element, -196, 0).perform();
		return this;
	}

	public BookShelves category() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popupCloseButton_xpath));
		wait.until(ExpectedConditions.elementToBeClickable(category_xpath)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(wallShelves_xpath)).click();
		return this;
	}

	public BookShelves excludeOutOfStock() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		wait.until(ExpectedConditions.elementToBeClickable(excludeOutOfStock_xpath)).click();
		return this;
	}

	public BookShelves display_1st_Three_Results() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bookNames_xpath));
		List<WebElement> bookShelvesNames = driver.findElements(bookNames_xpath);
		List<WebElement> bookShelvesPrice = driver.findElements(bookPrice_xpath);
		System.out.println("\nThe First 3 Bookshelves details: ");

		for (int i = 0; i < 3; i++) {
			System.out.println(i + 1 + " BookShelves Name: " + bookShelvesNames.get(i).getText());
			System.out.println("  Price: " + bookShelvesPrice.get(i).getText());
		}
		return this;
	}
}