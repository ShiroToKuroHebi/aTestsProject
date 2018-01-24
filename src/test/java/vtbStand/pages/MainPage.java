package vtbStand.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Page {
	
	@FindBy(xpath = ".//div[text()='Создать ПП']")
	@CacheLookup
			private WebElement buttonCreateNewRP;

	
	
	/** Press the 'Создать ПП' button
	 */
	public NewRPForm openFormCreateNewRP() {
		buttonCreateNewRP.click();
		return Page.newRPForm();
	}
	
}
