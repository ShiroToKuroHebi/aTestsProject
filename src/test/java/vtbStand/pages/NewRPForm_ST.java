package vtbStand.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


/** This is the
 *  'Create new RP' form, 'simpleTab'
 */
public class NewRPForm_ST extends NewRPForm {
	@FindBy(xpath = "//div[contains(@class, 'PayDocRu__simpleTab')]")
			private WebElement form;
	
	@FindBy(xpath = "//div[@title='БИК']//input")
	@CacheLookup
			private WebElement fieldRecBIC;
	
	@FindBy(xpath = ("//div[contains(@class,'receiverAccount')]" +
			"/ancestor::div[contains(@class,'fieldGroup__body')]" +
			"//div[contains(text(),'реквизиты')]" +
			"/parent::button"))
			private WebElement buttonShowHideRecData;
	
	
	public static String showReceiverData = "Показать реквизиты";
	public static String hideReceiverData = "Скрыть реквизиты";
	public static String invalidReceiverData = "Необходимо заполнить реквизиты";
	
	
	
	// Elements
	public WebElement bShowHideRecData_ST;
	

	
	/** Click the button!
	 */
	public void showRecDataBlock() {
		buttonShowHideRecData.click();
		wait.until(ExpectedConditions.visibilityOf(fieldRecBIC));
	}
	
	public WebElement getForm() {	return form;	}
	public WebElement getFieldRecBIC() {	return fieldRecBIC;	}
	public WebElement getButtonShowHideRecData() {	return buttonShowHideRecData;   }
}
