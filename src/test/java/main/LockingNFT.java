package main;

import java.time.Duration;



import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;




	
	
	




public class LockingNFT extends BaseTest {
	
	
	
	@Test
	public void lockNFT() throws InterruptedException {

		WebElement assetsMenu = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Assets']"))));
		assetsMenu.click();

		Actions action = new Actions(driver);

		WebElement from = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Citizen Alpha Key']"))));

		WebElement to = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cc\"]"))));

		action.moveToElement(from).pause(Duration.ofSeconds(1)).clickAndHold().moveToElement(to).pause(Duration.ofSeconds(1)).pause(Duration.ofSeconds(1)).build()
				.perform();

		action.moveToElement(to).pause(Duration.ofSeconds(1)).release().build().perform();

		Thread.sleep(3000);

		WebElement offChain = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Off-chain']"))));

		offChain.click();

		driver.findElement(By.xpath("//*[@id=\"lock-modal\"]/div/div/app-lock-modal/div/button")).click();

		WebElement alert = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='message-text']"))));

		Assert.assertEquals(alert.getText(), "Assets successfully locked.");

		System.out.println(alert.getText());

		driver.navigate().refresh();

		Thread.sleep(3000);

		WebElement fromCC = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Citizen Alpha Key']"))));

		WebElement backToQID = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"qid\"]"))));

		action.moveToElement(fromCC).pause(Duration.ofSeconds(1)).clickAndHold().pause(Duration.ofSeconds(1))
				.moveToElement(backToQID).pause(Duration.ofSeconds(1)).build().perform();

		action.moveToElement(backToQID).pause(Duration.ofSeconds(1)).release().build().perform();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"lock-modal\"]/div/div/app-unlock-modal/div/button")).click();

		WebElement alert2 = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='message-text']"))));

		Assert.assertEquals(alert2.getText(), "Assets successfully unlocked.");

		System.out.println(alert2.getText());

		Thread.sleep(3000);

	}
}


