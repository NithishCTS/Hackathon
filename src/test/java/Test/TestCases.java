package Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Pages.BookShelves;
import Pages.Gift_Cards;
import Pages.Sofas_and_Recliners;

public class TestCases {

	private BookShelves bookShelves = new BookShelves();
	private Sofas_and_Recliners sofas_and_Recliners = new Sofas_and_Recliners();
	private Gift_Cards gift_Cards = new Gift_Cards();
	private ExtentTest extendTest;
	private ExtentReports extendreports;

	@BeforeSuite(alwaysRun = true)
	public void driverSetup() throws IOException {
		bookShelves.setDriver();
		//creation of reporter 
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\reports\\extentReport.html");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Bookshelves Report");
		htmlReporter.config().setReportName("Test report");

		extendreports = new ExtentReports();
		extendreports.attachReporter(htmlReporter);
	}

	@Test(priority = 1, groups = { "bookShelves_page" })
	public void searchBookShelves() throws Exception {
		extendTest = extendreports.createTest("Module:1 SearchBookShelves");
		try {
			bookShelves.searchBookShelves();
			extendTest.log(Status.INFO, "Searched for Bookshelves");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "searchBookShelves", groups = { "bookShelves_page" })
	public void category() throws Exception {
		extendTest = extendreports.createTest("Module:1 Category");
		try {
			bookShelves.category();
			extendTest.log(Status.INFO, "Category is selected as Wall Shelves");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "category", groups = { "bookShelves_page" })
	public void price() throws Exception {
		extendTest = extendreports.createTest("Module:1 Price");
		try {
			bookShelves.price();
			extendTest.log(Status.INFO, "Price is selected below 15,000");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "price", groups = { "bookShelves_page" })
	public void excludeOutOfStock() throws Exception {
		extendTest = extendreports.createTest("Module:1 ExcludeOutOfStock");
		try {
			bookShelves.excludeOutOfStock();
			extendTest.log(Status.INFO, "ExcludeOutOfStock is selected Successfully");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "excludeOutOfStock", groups = { "bookShelves_page" })
	public void display_1st_Three_Results() throws Exception {
		extendTest = extendreports.createTest("Module:1 Display_1st_Three_Results");
		try {
			bookShelves.display_1st_Three_Results();
			extendTest.log(Status.INFO, "First 3 results are displayed");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(priority = 2, groups = { "sofa_page" })
	public void sofasAndReclinders_Menu() throws Exception {
		extendTest = extendreports.createTest("Module:2 sofasAndReclinders_Menu");
		try {
			sofas_and_Recliners.sofasAndReclinders_Menu();
			extendTest.log(Status.INFO, "Sofas & Reclinders Menu is selected");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "sofasAndReclinders_Menu", groups = { "sofa_page" })
	public void extractSub_Menus() throws Exception {
		extendTest = extendreports.createTest("Module:2 extractSub_Menus");
		try {
			sofas_and_Recliners.extractSub_Menus();
			extendTest.log(Status.INFO, "Sub-Menus are extracted Successfully");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "extractSub_Menus", groups = { "sofa_page" })
	public void printSub_Menus() throws Exception {
		extendTest = extendreports.createTest("Module:2 printSub_Menus");
		try {
			sofas_and_Recliners.printSub_Menus();
			extendTest.log(Status.INFO, "Sub-Menus are printed Successfully");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(priority = 3, groups = "giftcard_page")
	public void customizeGiftCards() throws Exception {
		extendTest = extendreports.createTest("Module:3 customizeGiftCards");
		try {
			gift_Cards.customizeGiftCards();
			extendTest.log(Status.INFO, "Gift card is customized Succesfully");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "customizeGiftCards", groups = { "giftcard_page" })
	public void fillDetails() throws Exception {
		extendTest = extendreports.createTest("Module:3 fillDetails");
		try {
			gift_Cards.fillDetails();
			extendTest.log(Status.INFO, "Gift card details are entered Successfully");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "fillDetails", groups = { "giftcard_page" })
	public void captureErrorMessage() throws Exception {
		extendTest = extendreports.createTest("Module:3 captureErrorMessage");
		try {
			gift_Cards.captureErrorMessage();
			extendTest.log(Status.INFO, "Error Message is captured & displayed Successfully");
		} catch (Exception e) {
			extendTest.log(Status.FAIL, e.getMessage());
			throw new Exception(e);
		}
	}

	@AfterSuite(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		extendreports.flush();
		bookShelves.driverClose();
	}
}
