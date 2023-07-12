package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProvider.ConfigFileReader;
import junit.framework.Assert;
import validation.SearchValidation;

public class SearchResultPage {

	WebDriver driver;
	private WebDriverWait wait;
	ConfigFileReader config = new ConfigFileReader();

	public SearchResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, config.getExplicitlyWait());
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//h1[@class='b-pageheader']/span")
	private WebElement pageHeading;

	@FindBy(how = How.XPATH, using = "//button/span[contains(text(),'filters applied')]")
	private WebElement filterAppliedDropDown;

	@FindBy(how = How.XPATH, using = "//li[@class='brm__aspect-item brm__aspect-item--applied']/a/span[@class='brm__item-label']")
	private List<WebElement> totalFilters;

	@FindBy(how = How.XPATH, using = "//span[@role='heading']")
	private List<WebElement> searchResult;

	public String getPageHeading() {
		return wait.until(ExpectedConditions.visibilityOf(pageHeading)).getText();
	}

	public void clickOnAllFilters() {
		wait.until(ExpectedConditions.visibilityOf(filterAppliedDropDown)).click();
	}

	public void validateAppliedFilters() {
		SearchValidation.getInstance().validateAppliedFilter(totalFilters);
	}

	public String getFirstSearchResult() {
		return searchResult.get(1).getText();
	}
}
