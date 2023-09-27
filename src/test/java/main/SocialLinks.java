package main;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.interactions.Actions;
import base.BaseTest;

import org.testng.annotations.Test;

public class SocialLinks extends BaseTest {

	

	@Test
	public void launchApplication() throws InterruptedException {

		SocialLinks login = new SocialLinks();
		Actions actions = new Actions(driver);

		String twitterLink = "https://linktr.ee/qorpotwitters";
		String telegramLink = "https://linktr.ee/qorpotelegram";
		String instagramLink = "https://linktr.ee/QORPOInstagrams";
		String linkedInLink = "https://www.linkedin.com/company/qorpogamestudio/?original_referer=https%3A%2F%2Fqorpo.world%2F";
		String youtubeLink = "https://www.youtube.com/@QORPOGameStudio";
		String discordLink = "https://discord.com/invite/qorpogamestudio";
		String twitchLink = "https://www.twitch.tv/qorpogamestudio";


		WebElement twitter = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='socials-row']//a[1]//*[name()='svg']"))));
		Thread.sleep(1000);

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", twitter);

		Thread.sleep(1000);

		actions.moveToElement(twitter).perform();

		twitter.click();

		login.checkingLink(twitterLink);

		WebElement telegram = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='socials-row']//a[2]//*[name()='svg']"))));

		actions.moveToElement(telegram).perform();

		telegram.click();

		login.checkingLink(telegramLink);

		Thread.sleep(1000);

		WebElement instagram = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='socials-row']//a[3]//*[name()='svg']"))));

		actions.moveToElement(instagram).perform();

		instagram.click();

		login.checkingLink(instagramLink);

		Thread.sleep(1000);

		WebElement linkedIn = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='content-wrapper desktop']//a[4]//*[name()='svg']"))));

		actions.moveToElement(linkedIn).perform();
		linkedIn.click();

		login.checkingLink(linkedInLink);

		Thread.sleep(1000);

		WebElement youtube = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='content-wrapper desktop']//a[5]//*[name()='svg']"))));
		actions.moveToElement(youtube).perform();
		youtube.click();
		Thread.sleep(3000);

		login.checkingLink(youtubeLink);

		Thread.sleep(1000);

		WebElement discord = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='content-wrapper desktop']//a[6]//*[name()='svg']"))));
		actions.moveToElement(discord).perform();
		discord.click();

		login.checkingLink(discordLink);

		Thread.sleep(1000);

		WebElement twitch = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='content-wrapper desktop']//a[7]//*[name()='svg']"))));
		actions.moveToElement(twitch).perform();
		twitch.click();

		login.checkingLink(twitchLink);

		Thread.sleep(1000);

		driver.findElement(By.xpath("//h3[text()='Back to top']")).click();

		Thread.sleep(1000);

	}

}