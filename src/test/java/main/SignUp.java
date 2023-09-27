package main;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SignUp {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties locators = new Properties();
	public static FileReader fr;
	public static FileReader fr_locators;
	String nick = "DalsaCol";

	// @Parameters({ "browser" })

	@Test
	// public void launchApplication(String browser)throws InterruptedException,
	// IOException {

	public void launchApplication() throws InterruptedException, IOException {

		System.setProperty("webdriver.http.factory", "jdk-http-client");

		if (driver == null) {
			System.out.println("The path is: " + System.getProperty("user.dir"));
			fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configfiles/config.properties");
			fr_locators = new FileReader(
					System.getProperty("user.dir") + "/src/test/resources/configfiles/locators.properties");
			prop.load(fr);
			locators.load(fr_locators);

		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(prop.getProperty("testUrl"));
		}

		else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(prop.getProperty("testUrl"));

		}

		driver.get("https://qorpo.world/register");

		String originalWindow = driver.getWindowHandle();

		driver.manage().window().maximize();

		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(nick);

		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("first" + nick + "@gmail.com");

		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("password");

		// driver.findElement(By.xpath("//*[@id=\"repeatPassword\"]")).sendKeys("password");

		driver.findElement(By.xpath(
				"/html/body/app-root/app-auth-layout/div/app-register/div/div/div/div[1]/email-registration-form/form/div[5]/q-country-picker/div/div"))
				.click();

		driver.findElement(By.xpath(
				"/html/body/app-root/app-auth-layout/div/app-register/div/div/div/div[1]/email-registration-form/form/div[5]/q-country-picker/div/div[2]/div[2]/div/div[1]/p"))
				.click();

		Thread.sleep(3000);

		driver.findElement(By.xpath(
				"/html/body/app-root/app-auth-layout/div/app-register/div/div/div/div[1]/email-registration-form/form/div[6]/div/form-checkbox-input/div/div"))
				.click();

		driver.findElement(By.xpath("//button[text()='Sign Up']")).click();

		Thread.sleep(3000);

		driver.switchTo().newWindow(WindowType.TAB);

		driver.navigate().to("https://mail.google.com/mail/u/0/#inbox");

		// Sign in button to e-mail
		WebElement signin = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Sign in']"))));

		signin.click();

		Thread.sleep(3000);

		WebElement email = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='identifierId']"))));

		email.sendKeys("email");

		driver.findElement(By.xpath("//*[@id='identifierNext']/div/button/span")).click();

		WebElement password = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))));
		password.sendKeys("passwordg");

		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span")).click();

		Thread.sleep(3000);

		WebElement activationCode = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@class='zA zE']"))));

		activationCode.click();

		WebElement code = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1"))));

		String Acode = new String();

		Acode = code.getText();

		Thread.sleep(3000);

		driver.switchTo().window(originalWindow);

		WebElement ActivateCode = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"code\"]"))));
		ActivateCode.sendKeys(Acode);

		Thread.sleep(3000);

		driver.findElement(By.xpath("//button[text() = 'Confirm']")).click();

		Thread.sleep(5000);
		driver.quit();

	}
}
