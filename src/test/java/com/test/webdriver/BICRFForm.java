package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** DICTIONARY!!!
 *  This is 'BIC RF Form'
 *  Actually, this one is not a 'form' but an 'overlap'.
 */
class BICRFForm {
	private WebDriverWait wait;
	
	private final static String fHeader = "Российские банки (БИК РФ)";
	
	//By formBIC = By.xpath("//div[text()=\""+fHeader+"\"]/ancestor::?..");
	private final static By formBIC = By.xpath("//div[text()=\"" + fHeader + "\"]/../../.."); //3rd parent from header - the form we need
	private final static By elementFirstRowInTable = By.xpath(".//div[@class=\"table__body\"]/div[1]");
	private final static By buttonApply = By.xpath(".//button[@class=\"Button__base--3ZP3W Button__basePrimary--3ryz2\"]");
	
	private WebElement fBIC;
	
	/** When called from creating new RP form (MT/ST)
	 * @param newRPForm_MT - the form from where called
	 */
	BICRFForm(NewRPForm_MT newRPForm_MT) {
		fBIC = newRPForm_MT.form.findElement(formBIC);
		wait = newRPForm_MT.wait;
	}
	
	/** Closes the overlay when 'Apply' is pressed
	 */
	dictRowBIC chooseFirstInTable(){
		dictRowBIC result;
		WebElement row = fBIC.findElement(elementFirstRowInTable);
		result = new dictRowBIC(row);
		
		row.click();
		fBIC.findElement(buttonApply).click();
		wait.until(ExpectedConditions.invisibilityOf(fBIC));
		
		return result;
	}
}
