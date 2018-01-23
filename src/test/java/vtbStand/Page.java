package vtbStand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

class Page {
	WebDriver drvr;
	WebDriverWait wait;
	
	// Errors may appear on any page or form
	static String tooltipErrorXPath = "//div[@class=\"field__tooltip field__tooltip_error\"]";
	static By tooltipsErrors = By.xpath(Page.tooltipErrorXPath);

}
