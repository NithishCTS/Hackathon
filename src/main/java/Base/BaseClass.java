package Base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

	public static RemoteWebDriver driver;
	public static WebDriverWait wait;
	public String driverSelection;
	public static Properties properties;

	public void setDriver() throws IOException {
		properties = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");
		properties.load(file);
		//new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties"));

		driverSelection = properties.getProperty("browser");
		switch (driverSelection.toLowerCase()) {
		case "chrome":
			chromeDriverSetup();
			break;
		case "edge":
			edgeDriverSetup();
			break;
		default:
			System.out.println("Invalid Input");
		}
		openUrl();
		
		//Checking Server Response
		URL obj = new URL(driver.getCurrentUrl());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK)
			System.out.println("Server response is good " + responseCode);
		else
			System.out.println("Server response is not good " + responseCode);
	}

	private void chromeDriverSetup() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\drivers\\chromedriver 111-32bit.exe");

		driver = new ChromeDriver();
	}

	private void edgeDriverSetup() {
		System.setProperty("webdriver.edge.driver",
				System.getProperty("user.dir") + "\\drivers\\msedgedriver 114 - 64bit.exe");
		driver = new EdgeDriver();
	}

	private void openUrl() {
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	}

	public void driverClose() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		driver.quit();

	}
}
