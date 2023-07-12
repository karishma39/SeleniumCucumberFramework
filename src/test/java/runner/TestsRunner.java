package runner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dataProvider.ConfigFileReader;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature", glue = { "stepDefinition" }, plugin = { "pretty",
		"html:target/cucumber-reports" }, monochrome = true)
public class TestsRunner {
	public static ChromeDriver driver;

	@BeforeClass
	public static void setupClass() {
		ConfigFileReader config = new ConfigFileReader();
		if (config.getBrowser().equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
		}
	}
}