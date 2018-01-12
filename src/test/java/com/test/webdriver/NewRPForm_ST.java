package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** This is the
 *  'Create new RP' form, 'simpleTab'
 */
class NewRPForm_ST extends NewRPForm {
	// Page-specific locators
	private final static By labelPayerAccountRest_ST = By.xpath(".//div[@class=\"GridSpan__gridSpan--1jA-G GridSpan__gridSpanEnd--16-If PayDocRu__gutterBottom--1avZl\"]");
	private final static By labelShowReceiverData_ST = By.xpath("//div[@class=\"fieldGroup\"][3]//div[text()=\"Показать реквизиты\"]");
	
	
	
	NewRPForm_ST () {
		// stub
	}
	
	NewRPForm_ST (WebDriver driver) {
		this.drvr = driver;
		this.wait = new WebDriverWait(drvr, 10);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(formNewRPSimpleTab));
		
		form = drvr.findElement(formNewRPRoot);
		waitForDataLoadOnForm();
	}
	
	/** Form is loading while value in tooltip is empty
	 */
	private void waitForDataLoadOnForm() {
		WebElement lPayAccRest = form.findElement(labelPayerAccountRest_ST);
		
		while (lPayAccRest.getText().isEmpty());
	}
}
