package vtbStand.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/** This is the
 *  'Create new RP' form, 'simpleTab'
 */
public class NewRPForm_ST extends NewRPForm {
	public final static String showReceiverData = "Показать реквизиты";
	public final static String hideReceiverData = "Скрыть реквизиты";
	public final static String invalidReceiverData = "Необходимо заполнить реквизиты";
	
	// Page-specific locators
	private final static By labelPayerAccountRest_ST = By.xpath(".//div[@class=\"GridSpan__gridSpan--1jA-G GridSpan__gridSpanEnd--16-If PayDocRu__gutterBottom--1avZl\"]");
	private final static By labelShowReceiverData_ST = By.xpath(".//div[@class=\"fieldGroup\"][3]//div[text()=\"Показать реквизиты\"]");
	private final static By textButtonsInReceiverBlock_ST = By.xpath(".//div[@class=\"fieldGroup\"][3]//button/div");
	
	private final static By fieldReceiverBIC_ST = By.xpath(".//div[@title=\"БИК\"]//input");
	
	// Elements
	public WebElement form_ST;
	
	public WebElement bShowHideRecData_ST;
	public WebElement fRecBIC_ST;
	
	public NewRPForm_ST () {
		// stub
	}
	
	public NewRPForm_ST (WebDriver driver) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(formNewRPSimpleTab));
		
		form = drvr.findElement(formNewRPRoot);
		bCreateRP = form.findElement(buttonCreateRP);
		
		form_ST = form.findElement(formNewRPSimpleTab);
		
		waitForDataLoadOnForm();
	}
	
	/** Form is loading while value in tooltip is empty
	 */
	private void waitForDataLoadOnForm() {
		WebElement lPayAccRest = form_ST.findElement(labelPayerAccountRest_ST);
		
		while (lPayAccRest.getText().isEmpty());
	}
	
	/** Find 'button' for showing/hiding receiver data block
	 */
	public void findButtonShowHideRecData () {
		List<WebElement> buttonsOnForm_ST = form_ST.findElements(textButtonsInReceiverBlock_ST);

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
	public void showRecDataBlock (WebElement bShowHideRecData) {
		bShowHideRecData.click();
		wait.until(ExpectedConditions.visibilityOf(fRecBIC_ST = form_ST.findElement(fieldReceiverBIC_ST)));
	}
}
