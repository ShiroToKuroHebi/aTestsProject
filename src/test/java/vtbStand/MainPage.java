package vtbStand;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Page {
	
	@FindBy(xpath = ".//div[text()='Создать ПП']")
	@CacheLookup
			private WebElement buttonCreateNewRP;

	public MainPage (WebDriver driver) {
		this.drvr = driver;
	}
	
	/** Press the 'Создать ПП' button
	 */
	public NewRPForm openFormCreateNewRP() {
		buttonCreateNewRP.click();
		return new NewRPForm(drvr);
	}
	
}
