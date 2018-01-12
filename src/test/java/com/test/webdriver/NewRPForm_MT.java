package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** That would be the
 *  'Create new RP' form, 'mainTab'
 */
class NewRPForm_MT extends NewRPForm{
	// Control messages
	final static String BIC_UNKNOWN = "Указанный БИК не найден в справочнике российских банков";//, выполнение проверки ключа счета не доступно";
	final static String BIC_TOO_SHORT = "БИК должен состоять из 9 цифр";
	final static String BIC_MUST_BE_NONEMPTY = "Поле БИК банка получателя обязательно для заполнения";
	
	// Page-specific locators
	private final static By fieldPayerAccount_MT = By.xpath(".//div[@title=\"Расчетный счет плательщика\"]/div[2]/div[1]/input");
	private final static By buttonReceiverBIC_MT = By.xpath(".//button[text()=\"БИК\"]");
	private final static By fieldReceiverBIC_MT = By.xpath(".//div[@title='БИК']/div[2]/div[1]/input");
	private final static By fieldReceiverBankName_MT = By.xpath(".//textarea[@disabled]"); // may lead to an error?
	
	// Elements
	WebElement bRecBIC_MT;
	WebElement fRecBIC_MT;
	WebElement fRecBankName_MT;
	
	

	NewRPForm_MT () {
		// stub
	}
	
	/** Get driver
	 *  + initialize form elements
	 */
	NewRPForm_MT (WebDriver driver) {
		drvr = driver;
		wait = new WebDriverWait(drvr, 10);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(formNewRPMainTab));
		
		form = drvr.findElement(formNewRPRoot);
		bRecBIC_MT = form.findElement(buttonReceiverBIC_MT);
		fRecBIC_MT = form.findElement(fieldReceiverBIC_MT);
		fRecBankName_MT = form.findElement(fieldReceiverBankName_MT);
		bCreateRP = form.findElement(buttonCreateRP);

		waitForDataLoadOnForm();
	}
	
	/** Form is loading while value in field is empty
	 */
	private void waitForDataLoadOnForm() {
		WebElement fPayAcc = form.findElement(fieldPayerAccount_MT);
		
		while (fPayAcc.getAttribute("value").isEmpty());
	}
}
