package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProvider.ConfigFileReader;

public class HomePage {
	private WebDriverWait wait;
	ConfigFileReader config = new ConfigFileReader();

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, config.getExplicitlyWait());
	}

	@FindBy(how = How.ID, using = "gh-shop-a")
	private WebElement shopCatLink;

	@FindBy(how = How.XPATH, using = "//input[@type='text']")
	private WebElement searchBoxPath;

	@FindBy(how = How.XPATH, using = "//select[@name='_sacat']")
	private WebElement dropDownPath;

	@FindBy(how = How.XPATH, using = "//input[@value='Search']")
	private WebElement searchButton;

	private WebElement categoryLink;

	private WebElement cellPhoneLink;

	public void clickOnShopCat() {
		wait.until(ExpectedConditions.visibilityOf(shopCatLink)).click();
	}

	public void clickOnSearchButton() {
		wait.until(ExpectedConditions.visibilityOf(searchButton)).click();
	}

	public void typeInSearchBox(String text) {
		wait.until(ExpectedConditions.visibilityOf(searchBoxPath)).sendKeys(text);
	}

	public void changeCategory(String categoryString) {
		Select category = new Select(dropDownPath);
		category.selectByVisibleText(categoryString);
	}

	public void clickOnCategory(String category) {
		categoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(category)));
		categoryLink.click();
	}

	public void clickOnCellPhoneLink(String category) {
		cellPhoneLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(category)));
		cellPhoneLink.click();
	}

}
