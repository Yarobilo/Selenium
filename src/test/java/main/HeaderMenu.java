package main;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;

public class HeaderMenu extends BaseTest {

	@Test
	public void checkHeaderUrl() throws InterruptedException {

		HeaderMenu hm = new HeaderMenu();

		String assetUrl = "https://qorpo.world/qorpo-id/manage";
		String marketUrl = "https://qorpo.market/market";
		String gamesUrl = "https://qorpo.world/qorpo-id/downloads";
		String tokenUrl = "https://qorpo.world/qorpo-id/token";
		String pollsUrl = "https://qorpo.world/qorpo-id/dao-voting";
		String leaderUrl = "https://qorpo.world/qorpo-id/tournaments/leaderboard";

		WebElement assets = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.getProperty("assets")))));
		assets.click();

		hm.checkUrl(assetUrl);
		
		WebElement market = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.getProperty("marketplace")))));
		market.click();

		hm.checkingLink(marketUrl);

		WebElement games = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.getProperty("games")))));
		games.click();

		hm.checkUrl(gamesUrl);

		WebElement token = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.getProperty("token")))));
		token.click();

		hm.checkUrl(tokenUrl);

		WebElement polls = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.getProperty("polls")))));
		polls.click();

		hm.checkUrl(pollsUrl);

		WebElement leaderboard = (new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.getProperty("leaderboard")))));
		leaderboard.click();

		hm.checkUrl(leaderUrl);

		

	}

}
