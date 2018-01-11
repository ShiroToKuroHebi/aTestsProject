package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class NewRPForm {
	
	WebDriver drvr;
	
	private By formNewRP = By.xpath("//form");
	private By fieldPayerAccount = By.xpath(".//div[@title=\"Расчетный счет плательщика\"]/div[2]/div[1]/input");
	private By buttonReceiverBIC = By.xpath(".//button[text()=\"БИК\"]");
	private By fieldReceiverBIC = By.xpath(".//div[@title='БИК']/div[2]/div[1]/input");
	private By buttonCloseForm = By.xpath(".//div[1]/div[1]/div[2]/button");
	private By buttonCreateRP = By.xpath(".//div[@class=\"menuActions__group\"][2]/div[1]/button");
	
	WebElement fNewRP;
	WebElement bRecBIC;
	WebElement fRecBIC;
	WebElement bClose;
	WebElement bCreateRP;
	
	/** Получить драйвер
	 *  + Заполнение fNewRP, bClose, bRecBIC, fRecBIC
	 */
	public NewRPForm (WebDriver driver) {
		drvr = driver;
		
		fNewRP = drvr.findElement(formNewRP);
		bRecBIC = fNewRP.findElement(buttonReceiverBIC);
		fRecBIC = fNewRP.findElement(fieldReceiverBIC);
		bClose = fNewRP.findElement(buttonCloseForm);
		bCreateRP = fNewRP.findElement(buttonCreateRP);
	}
	
	/** Form is loading while value in field is empty
	 */
	void waitForDataLoadOnForm() {
		WebElement fPayerAcc = fNewRP.findElement(fieldPayerAccount);
		
		while (fPayerAcc.getAttribute("value").isEmpty());
	}
	
	/** Close the form
	 */
	void close() {
		WebDriverWait timeout = (new WebDriverWait(drvr, 10));
		timeout.until(ExpectedConditions.elementToBeClickable(bClose));
		bClose.click();
		timeout = (new WebDriverWait(drvr, 10));
		timeout.until(ExpectedConditions.invisibilityOfElementLocated(By.id("appframe")));
	}
}
