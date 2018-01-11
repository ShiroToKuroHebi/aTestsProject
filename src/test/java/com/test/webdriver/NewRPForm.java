package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class NewRPForm {
	
	WebDriver drvr;
	
	private By formNewRP = By.xpath("//*[@id=\"appframe\"]/form"); // not sure about this one, tbh
	private By fieldPayerAccount = By.xpath("//div[@title=\"Расчетный счет плательщика\"]/div[2]/div[1]/input");
	
	WebElement form;
	
	public NewRPForm (WebDriver driver) {
		drvr = driver;
		
		form = drvr.findElement(formNewRP);
	}
	
	/** Form is loading while value in field is empty
	 */
	public void waitForDataLoadOnForm() {
		WebElement payerAcc = form.findElement(fieldPayerAccount);
		
		
		while (payerAcc.getAttribute("value").isEmpty());
	}
	
}
