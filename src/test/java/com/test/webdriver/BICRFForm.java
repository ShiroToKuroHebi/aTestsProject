package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/** That would be 'BIC RF Form'
 *  Actually, this one could be not a form but only an overlap.
 */
class BICRFForm {
	
	
	
	private final static String fHeader = "Российские банки (БИК РФ)";
	
	//By formBIC = By.xpath("//div[text()=\""+fHeader+"\"]/ancestor::?..");
	private final static By formBIC = By.xpath("//div[text()=\"" + fHeader + "\"]/../../.."); //3rd parent from header - the form we need
	private final static By elementFirstRowInTable = By.xpath(".//div[@class=\"table__body\"]/div[1]");
	private final static By buttonApply = By.xpath(".//button[@class=\"Button__base--3ZP3W Button__basePrimary--3ryz2\"]"); //don't like this
	
	WebElement fBIC;
	
	/** When called from creating new RP form
	 * @param fNewRP - the form from where called
	 */
	BICRFForm(WebElement fNewRP) {
		fBIC = fNewRP.findElement(formBIC);
	}
	
	/** Closes the overlay when 'Apply' is pressed
	 */
	dictRowBIC chooseFirstInTable(){
		dictRowBIC result;
		WebElement row = fBIC.findElement(elementFirstRowInTable);
		result = new dictRowBIC(row);
		
		row.click();
		fBIC.findElement(buttonApply).click();

		return result;
	}
}
