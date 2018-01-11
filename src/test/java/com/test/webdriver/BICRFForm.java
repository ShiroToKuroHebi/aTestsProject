package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/** That would be 'BIC RF Form'
 *  Actually, this one could be not a form but only an overlap.
 */
public class BICRFForm {
	
	String fHeader = "Российские банки (БИК РФ)";
	
	//By formBIC = By.xpath("//div[text()=\""+fHeader+"\"]/ancestor::?..");
	By formBIC = By.xpath("//div[text()=\"" + fHeader + "\"]/../../.."); //3rd parent from header - the form we need
	By elementFirstBICInTable = By.xpath(".//div[@class=\"table__body\"]/div[1]/div[1]/div/span");
	By buttonApply = By.xpath(".//button[@class=\"Button__base--3ZP3W Button__basePrimary--3ryz2\"]"); //don't like this
	
	WebElement fBIC;
	
	/** When called from creating new RP form
	 * @param fNewRP - the form from where called
	 */
	public BICRFForm(WebElement fNewRP) {
		fBIC = fNewRP.findElement(formBIC);
	}
	
	/** Closes the overlay when 'Apply' is pressed
	 */
	String chooseFirstInTable(){
		String bic = fBIC.findElement(elementFirstBICInTable).getText();
		fBIC.findElement(elementFirstBICInTable).click();
		fBIC.findElement(buttonApply).click();
		return bic;
	}
}
