package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookLogin {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties locators = new Properties();
	public static FileReader fr;
	public static FileReader fr_locators;
	
	public void Switcher() {

		String originalWindow = driver.getWindowHandle();

		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(originalWindow)) {
				driver.switchTo().window(actual);

			}
		}
	}

	@Test
	public void FbLogin() throws IOException, InterruptedException {

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

		driver.manage().window().maximize();

		// accept cookies
		driver.findElement(By.xpath(locators.getProperty("cookies"))).click();

		driver.findElement(By.xpath(locators.getProperty("login_via_fb"))).click();

		String originalWindow = driver.getWindowHandle();

		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(originalWindow)) {
				driver.switchTo().window(actual);

			}
		}

		WebElement cookies = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Allow all cookies']"))));
		cookies.click();

		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("Login");

		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("Password");

		driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
		
		driver.switchTo().window(originalWindow);

		Thread.sleep(5000);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://qorpo.world/qorpo-id/dashboard");

		driver.quit();
		
		
		
		
		
		

	}

	@Test
	public void GoogleLogin() throws IOException, InterruptedException {

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

		driver.manage().window().maximize();

		// accept cookies
		driver.findElement(By.xpath(locators.getProperty("cookies"))).click();

		driver.findElement(By.xpath(locators.getProperty("login_via_google"))).click();

		String originalWindow = driver.getWindowHandle();

		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(originalWindow)) {
				driver.switchTo().window(actual);

			}

		}

		WebElement email = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"identifierId\"]"))));
		// email.click();
		email.sendKeys("Login");

		// driver.findElement(By.xpath(locators.getProperty(""))).click();

		WebElement next = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Next']"))));
		next.click();

		WebElement password = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))));
		password.sendKeys("password");

		WebElement next2 = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"passwordNext\"]/div/button/span"))));
		next2.click();
		
		driver.switchTo().window(originalWindow);
		
//		Thread.sleep(5000);
//		
//		Assert.assertEquals(driver.getCurrentUrl(), "https://qorpo.world/qorpo-id/dashboard");

		driver.quit();
		
		

	}

	@Test
	public void LoginMM() throws IOException, InterruptedException {

		FacebookLogin fl = new FacebookLogin();

		System.setProperty("webdriver.http.factory", "jdk-http-client");

		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions(new File("app.crx"));

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(ChromeOptions.CAPABILITY, opt);

		if (driver == null) {

			//System.out.println("The path is: " + System.getProperty("user.dir"));
			fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configfiles/config.properties");
			fr_locators = new FileReader(
					System.getProperty("user.dir") + "/src/test/resources/configfiles/locators.properties");
			prop.load(fr);
			locators.load(fr_locators);

		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver(opt);
			driver.get(prop.getProperty("testUrl"));
		}

		else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(prop.getProperty("testUrl"));

		}

		driver.manage().window().maximize();

		String originalWindow = driver.getWindowHandle();

		fl.Switcher();
		
		

		Thread.sleep(3000);

		// Meta mask add wallet
		WebElement iAgree = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='onboarding__terms-checkbox']"))));
		iAgree.click();

		WebElement importWallet = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id='app-content']/div/div[2]/div/div/div/ul/li[3]/button"))));
		importWallet.click();

		WebElement iAgree2 = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id='app-content']/div/div[2]/div/div/div/div/button[1]"))));
		iAgree2.click();

		WebElement inputPhrase = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='import-srp__srp-word-0']"))));
		inputPhrase.sendKeys("unfold");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-1']")).sendKeys("height");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-2']")).sendKeys("betray");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-3']")).sendKeys("ankle");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-4']")).sendKeys("lyrics");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-5']")).sendKeys("wagon");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-6']")).sendKeys("grant");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-7']")).sendKeys("icon");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-8']")).sendKeys("fossil");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-9']")).sendKeys("noodle");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-10']")).sendKeys("rain");
		driver.findElement(By.xpath("//*[@id='import-srp__srp-word-11']")).sendKeys("error");

		WebElement confirm = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[@id='app-content']/div/div[2]/div/div/div/div[4]/div/button"))));
		confirm.click();

		WebElement code = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[@id='app-content']/div/div[2]/div/div/div/div[2]/form/div[1]/label/input"))));
		code.sendKeys("985630Joker");

		WebElement code2 = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[@id=\"app-content\"]/div/div[2]/div/div/div/div[2]/form/div[2]/label/input"))));
		code2.sendKeys("985630Joker");

		WebElement iUnder = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[@id=\"app-content\"]/div/div[2]/div/div/div/div[2]/form/div[3]/label/input"))));
		iUnder.click();

		WebElement confirmImport = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[@id=\"app-content\"]/div/div[2]/div/div/div/div[2]/form/button"))));
		confirmImport.click();

		WebElement goIt = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id=\"app-content\"]/div/div[2]/div/div/div/div[2]/button"))));
		goIt.click();

		WebElement next = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id=\"app-content\"]/div/div[2]/div/div/div/div[2]/button"))));
		next.click();

		WebElement done = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id=\"app-content\"]/div/div[2]/div/div/div/div[2]/button"))));
		done.click();

		driver.close();

		driver.switchTo().window(originalWindow);

		Thread.sleep(3000);

		// accept cookies
		WebElement cookies = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.getProperty("cookies")))));

		cookies.click();

		Thread.sleep(3000);

		driver.findElement(By.xpath(locators.getProperty("login_via_mm"))).click();

		Thread.sleep(3000);

		fl.Switcher();

		WebElement nex3 = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Next']"))));
		nex3.click();

		WebElement connect = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[@id=\"app-content\"]/div/div[2]/div/div[2]/div[2]/div[2]/footer/button[2]"))));
		connect.click();

		WebElement signin = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[@id=\"app-content\"]/div/div[2]/div/div[5]/footer/button[2]"))));
		signin.click();

		driver.close();

		driver.switchTo().window(originalWindow);

		Thread.sleep(5000);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://qorpo.world/qorpo-id/dashboard");

		driver.quit();

	}
	
	@Test
	public void TwitterLogin() throws IOException, InterruptedException {
		
		System.setProperty("webdriver.http.factory", "jdk-http-client");

	
		if (driver == null) {

			//System.out.println("The path is: " + System.getProperty("user.dir"));
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

		driver.manage().window().maximize();

		//String originalWindow = driver.getWindowHandle();
		
		WebElement cookies = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.getProperty("cookies")))));

		cookies.click();

		Thread.sleep(3000);

		driver.findElement(By.xpath(locators.getProperty("login_via_twitter"))).click();

		Thread.sleep(3000);
		
		WebElement twLogin = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[2]/div/div/a/div/span/span"))));

		twLogin.click();
		
		WebElement twEmail = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"layers\"]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/div[5]/label/div/div[2]/div/input"))));
		twEmail.sendKeys("login");
		
		driver.findElement(By.xpath("//*[@id='layers']/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/div[6]/div/span/span")).click();
		
		WebElement twAuth = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"layers\"]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/label/div/div[2]/div/input"))));
		twAuth.sendKeys("word");
		
		driver.findElement(By.xpath("//*[@id=\"layers\"]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div/div/div")).click();
		
		WebElement twPW = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"layers\"]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[3]/div/label/div/div[2]/div[1]/input"))));
		twPW.sendKeys("password");
		
		driver.findElement(By.xpath("//*[@id=\"layers\"]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/div/span/span")).click();
		
		WebElement twAuthApp = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[2]/div/div/div[1]/div[3]/div/div/span/span"))));

		twAuthApp.click();
		
		Thread.sleep(5000);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://qorpo.world/qorpo-id/dashboard");
		
		driver.quit();
		
		
	}

	

}
