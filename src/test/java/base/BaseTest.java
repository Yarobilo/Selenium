package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties locators = new Properties();
	public static FileReader fr;
	public static FileReader fr_locators;
	
	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		
		if(driver==null) {
			System.out.println("The path is: " + System.getProperty("user.dir"));
		    fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configfiles/config.properties");
			fr_locators = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configfiles/locators.properties");
			prop.load(fr);
			locators.load(fr_locators);
			
		}
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(prop.getProperty("testUrl"));
		}
		
		else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(prop.getProperty("testUrl"));
			
		}
		
		driver.manage().window().maximize();

		// accept cookies
		driver.findElement(By.xpath(locators.getProperty("cookies"))).click();

		// click login via email
		driver.findElement(By.xpath(locators.getProperty("login_via_email"))).click();

		driver.findElement(By.id(locators.getProperty("email"))).sendKeys("yaroslav.bilozir@qorpo.co");

		driver.findElement(By.id(locators.getProperty("password"))).sendKeys("985630Joker");

		driver.findElement(By.xpath(locators.getProperty("login_button"))).click();

		Thread.sleep(1000);
		
		//driver.findElement(By.xpath(locators.getProperty("login_button"))).click();
		
		
	}
	
	
	@AfterTest
	public void tearDown() {
		
		driver.quit();
	}
	
	public void checkingLink (String link) throws InterruptedException {

		String originalWindow = driver.getWindowHandle();

		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(originalWindow)) {
				driver.switchTo().window(actual);

			}
		}

		if (link.equals("https://www.youtube.com/@QORPOGameStudio")) {

			driver.findElement(By.xpath("//span[text()='Accept all']")).click();
			Thread.sleep(3000);

		}

		Assert.assertEquals(driver.getCurrentUrl(), link);
		driver.switchTo().window(originalWindow);
	}
	
	public void checkUrl(String url) throws InterruptedException {
		
		Thread.sleep(1000);
		
		Assert.assertEquals(driver.getCurrentUrl(), url);
		
	}

}
