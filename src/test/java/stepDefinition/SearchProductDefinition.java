package stepDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.apache.commons.codec.language.bm.Rule.Phoneme;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProvider.ConfigFileReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.HomePage;
import pages.PhonePage;
import pages.SearchResultPage;
import runner.TestsRunner;

public class SearchProductDefinition {
	private WebDriver driver;
	private WebDriverWait wait;
	PhonePage phonePage;
	HomePage homePage;
	SearchResultPage searchPage;
	public static String screenSize = "5.0";
	public static String minPrice = "200.00";
	public static String maxPrice = "300.00";
	public static String location = "US Only";
	ConfigFileReader config = new ConfigFileReader();

	public SearchProductDefinition() {
		this.driver = TestsRunner.driver;
		driver.manage().timeouts().implicitlyWait(config.getImplicitlyWait(), TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, config.getExplicitlyWait());
		driver.manage().window().maximize();
		phonePage = new PhonePage(driver);
		homePage = new HomePage(driver);
		searchPage = new SearchResultPage(driver);
	}

	@Given("I am on the eBay homepage")
	public void navigateToEbayHomePage() {
		driver.get(config.getApplicationUrl());
	}

	@When("I navigate to {string}")
	public void navigateToCategory(String category) {
		homePage.clickOnShopCat();
		homePage.clickOnCategory(category);
	}

	@When("I click on {string} in the left-hand side navigation")
	public void clickOnCellPhones(String category) {
		homePage.clickOnCellPhoneLink(category);
	}

	@When("I click on {string} under {string}")
	public void clickOnSeeAll(String seeAllLink, String section1) {
		phonePage.clickOnSeeAll();
	}

	@When("I add filters for {string}, {string}, and {string}")
	public void addFilters(String screenSize, String price, String itemLocation) throws InterruptedException {
		phonePage.clickOnScreenSize();
		phonePage.selectScreenSize();
		phonePage.scrollPopup();
		phonePage.clickOnPrice();
		phonePage.addMinPrice(minPrice);
		phonePage.addMaxPrice(maxPrice);
		phonePage.clickOnLocation();
		phonePage.selectLocation();
	}

	@When("I apply the filters")
	public void applyFilters() {
		phonePage.clickOnApply();
	}

	@Then("I should see the filter tags applied")
	public void validateFilters() {
		driver.navigate().refresh();
		String pageHeadig = searchPage.getPageHeading();
		Assert.assertTrue(
				pageHeadig.contains("Cell Phones & Smartphones between US $" + minPrice + " and US $" + maxPrice));
		searchPage.clickOnAllFilters();
		searchPage.validateAppliedFilters();
	}

	@When("I type {string} in the search bar")
	public void typeInSearchBar(String searchedString) {
		homePage.typeInSearchBox(searchedString);

	}

	@When("I change the search category to {string}")
	public void changeSearchCategory(String categoryString) {
		homePage.changeCategory(categoryString);

	}

	@When("I click on Search")
	public void clickSearchButton() {
		homePage.clickOnSearchButton();
	}

	@Then("the page should load completely")
	public void pageShouldLoadCompeletly() {
		wait.until(webDriver -> {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			return jsExecutor.executeScript("return document.readyState").equals("complete");
		});
	}

	@Then("the first result name should match {string}")
	public void matchFirstResult(String searchedString) {
		String resultString = searchPage.getFirstSearchResult();
		Assert.assertTrue(resultString.contains(searchedString));
	}

}
