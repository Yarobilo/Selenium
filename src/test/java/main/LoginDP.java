package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginDP {
	
	public static WebDriver driver;

	@Test(dataProvider = "data2_3", dataProviderClass = DataProviderQID.class)

	public void test(String email, String password, String browser) throws InterruptedException

	{

		System.setProperty("webdriver.http.factory", "jdk-http-client");
		if (browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

		} else if (browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();

		} else if (browser.equals("Edge")) {
			WebDriverManager.edgedriver().setup();

			driver = new EdgeDriver();
		}

		driver.get("https://dev.qorpo.world/qorpo-id/auth/login");

		driver.manage().window().maximize();

		// accept cookies
		driver.findElement(By.xpath("//*[@id=\"cookiefirst-root\"]/div/div/div[2]/div[2]/div[2]/div[1]/button/span"))
				.click();

		Thread.sleep(3000);
		// click login via email
		driver.findElement(By
				.xpath("/html/body/app-root/app-site-layout/div[3]/app-auth-layout/app-login/div/div/div/button[3]/p"))
				.click();

		Thread.sleep(1000);

		driver.findElement(By.id("email")).sendKeys(email);

		driver.findElement(By.id("password")).sendKeys(password);

		Thread.sleep(1000);

		driver.findElement(
				By.xpath("/html/body/app-root/app-site-layout/div[3]/app-auth-layout/app-login/div/div/div/button"))
				.click();

		Thread.sleep(3000);

		driver.quit();
		
		

	}
	
	

}
