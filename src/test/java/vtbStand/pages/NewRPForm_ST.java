package vtbStand.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/** This is the
 *  'Create new RP' form, 'simpleTab'
 */
public class NewRPForm_ST extends NewRPForm {
	@FindBy(xpath = "//div[contains(@class, 'PayDocRu__simpleTab')]")
			private WebElement form;
	
	public final static String showReceiverData = "Показать реквизиты";
	public final static String hideReceiverData = "Скрыть реквизиты";
	public final static String invalidReceiverData = "Необходимо заполнить реквизиты";
	
	// Page-specific locators
	private final static By labelPayerAccountRest_ST = By.xpath(".//div[@class=\"GridSpan__gridSpan--1jA-G GridSpan__gridSpanEnd--16-If PayDocRu__gutterBottom--1avZl\"]");
	private final static By labelShowReceiverData_ST = By.xpath(".//div[@class=\"fieldGroup\"][3]//div[text()=\"Показать реквизиты\"]");
	private final static By textButtonsInReceiverBlock_ST = By.xpath(".//div[@class=\"fieldGroup\"][3]//button/div");
	
	private final static By fieldReceiverBIC_ST = By.xpath(".//div[@title=\"БИК\"]//input");
	
	// Elements
	public WebElement bShowHideRecData_ST;
	public WebElement fRecBIC_ST;
	

	
	/** Form is loading while value in tooltip is empty
	 */
	public void waitForDataLoadOnForm() {
		WebElement lPayAccRest = form.findElement(labelPayerAccountRest_ST);
		
		while (lPayAccRest.getText().isEmpty());
	}
	
	/** Find 'button' for showing/hiding receiver data block
	 */
	public void findButtonShowHideRecData() {
		List<WebElement> buttonsOnForm_ST = form.findElements(textButtonsInReceiverBlock_ST);

		for (WebElement text : buttonsOnForm_ST) {
			// it's either show-/hide-/invalidReceiverData
			if (text.getText().contains("реквизиты")) {
				bShowHideRecData_ST = text.findElement(By.xpath("./.."));
				break;
			}
		}
	}
	
	/** Click the button!
	 */
	public void showRecDataBlock(WebElement bShowHideRecData) {
		bShowHideRecData.click();
		wait.until(ExpectedConditions.visibilityOf(fRecBIC_ST = form.findElement(fieldReceiverBIC_ST)));
	}
	
	public WebElement getForm() {	return form;	}

}
