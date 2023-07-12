package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProvider.ConfigFileReader;

public class PhonePage {
	WebDriver driver;
	private WebDriverWait wait;
	ConfigFileReader config = new ConfigFileReader();
	
	public PhonePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, config.getExplicitlyWait());
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//button[contains(@data-see-all,'Shop by Brand')]")
	private WebElement seeAll;

	@FindBy(how = How.XPATH, using = "//div[contains(@data-aspecttitle,'Screen')]")
	private WebElement addScreenFilter;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'20Size_5.0')]")
	private WebElement selectScreenSize;

	@FindBy(how = How.XPATH, using = "//div[@class='x-overlay__wrapper--left']")
	private WebElement scrollPopUp;

	@FindBy(how = How.XPATH, using = "//div[@data-aspecttitle='price']")
	private WebElement priceSection;

	@FindBy(how = How.XPATH, using = "//input[@aria-label='Minimum Value, US Dollar']")
	private WebElement addMinPrice;

	@FindBy(how = How.XPATH, using = "//input[@aria-label='Maximum Value, US Dollar']")
	private WebElement addMaxPrice;

	@FindBy(how = How.XPATH, using = "//div[@data-aspecttitle='location']")
	private WebElement addLocationFilter;

	@FindBy(how = How.XPATH, using = "//span[@class='radio__icon']")
	private List<WebElement> chooseLocationRadio;

	@FindBy(how = How.XPATH, using = "//button[@aria-label='Apply']")
	private WebElement applyButton;

	public void clickOnSeeAll() {
		wait.until(ExpectedConditions.visibilityOf(seeAll)).click();
	}

	public void clickOnScreenSize() {
		wait.until(ExpectedConditions.visibilityOf(addScreenFilter)).click();
	}

	public void selectScreenSize() {
		wait.until(ExpectedConditions.visibilityOf(selectScreenSize)).click();
	}

	public void scrollPopup() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", scrollPopUp);
	}

	public void clickOnPrice() {
		wait.until(ExpectedConditions.visibilityOf(priceSection)).click();
	}

	public void addMinPrice(String minPrice) {
		wait.until(ExpectedConditions.visibilityOf(addMinPrice)).sendKeys(minPrice);
	}

	public void addMaxPrice(String maxPrice) {
		wait.until(ExpectedConditions.visibilityOf(addMaxPrice)).sendKeys(maxPrice);
	}

	public void clickOnLocation() {
		wait.until(ExpectedConditions.visibilityOf(addLocationFilter)).click();
	}

	public void selectLocation() {
		Actions action = new Actions(driver);
		action.moveToElement(chooseLocationRadio.get(0)).click().perform();
		action.moveToElement(chooseLocationRadio.get(1)).click().perform();
	}

	public void clickOnApply() {
		wait.until(ExpectedConditions.visibilityOf(applyButton)).click();
	}
}
