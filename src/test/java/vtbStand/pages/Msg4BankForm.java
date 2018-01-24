package vtbStand.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/** DICTIONARY!!!
 */
public class Msg4BankForm extends Page {
	private final static String HEADER = "Типовые сообщения для банка";
	
	@FindBy(xpath = "//div[text()='" + HEADER + "']/ancestor::div[contains(@class,'Overlap__base')]")
	@CacheLookup
			private WebElement form;
	
	@FindBy(xpath = "//span[text()='Добавить']/ancestor::button")
	@CacheLookup
			private WebElement buttonNewMsg;
	
	
	
	// more code will be here



	public WebElement getForm() {	return form;	}
	
}
