package validation;

import java.util.List;

import org.openqa.selenium.WebElement;

import junit.framework.Assert;

public class SearchValidation {
	public static String screenSize = "5.0";
	public static String minPrice = "200.00";
	public static String maxPrice = "300.00";
	public static String location = "US Only";

	private static volatile SearchValidation instance;

	private SearchValidation() {

	}

	public static SearchValidation getInstance() {
		if (instance == null) {
			instance = new SearchValidation();
		}
		return instance;
	}

	public void validateAppliedFilter(List<WebElement> totalFilters) {
		for (WebElement filter : totalFilters) {
			String text = filter.getText();
			if (text.contains("Screen Size")) {
				Assert.assertTrue(text.contains("Screen Size: " + screenSize));
			}
			if (text.contains("Price")) {
				Assert.assertTrue(text.contains("Price: $" + minPrice + " to $" + maxPrice));
			}
			if (text.contains("Location")) {
				Assert.assertTrue(text.contains("Item Location: " + location));
			}
		}
	}
}
