package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class NewRPForm {
	WebDriver drvr;
	WebDriverWait wait;
	
	// Control messages
	final static String BIC_UNKNOWN = "Указанный БИК не найден в справочнике российских банков";//, выполнение проверки ключа счета не доступно";
	final static String BIC_TOO_SHORT = "БИК должен состоять из 9 цифр";
	final static String BIC_MUST_BE_NONEMPTY = "Поле БИК банка получателя обязательно для заполнения";
	
	// Locators
	private final static By formNewRP = By.xpath("//form");
	private final static By buttonSimpleTabFormLong = By.xpath(".//div[@class=\"PayDocRu__header--3fL7X\"]//div[@class=\"RadioBtns__base--2hihs\"]/button[2]");
	private final static By buttonSimpleTabForm = By.xpath(".//div[@class=\"PayDocRu__header--3fL7X\"]//button[2]"); // may lead to an error?
	private final static By buttonMainTabForm = By.xpath(".//div[@class=\"PayDocRu__header--3fL7X\"]//button[1]"); // may lead to an error?
	
	private final static By labelShowReceiverData_ST = By.xpath("//div[@class=\"fieldGroup\"][3]//div[text()=\"Показать реквизиты\"]");
	
	private final static By fieldPayerAccount_MT = By.xpath(".//div[@title=\"Расчетный счет плательщика\"]/div[2]/div[1]/input");
	private final static By buttonReceiverBIC_MT = By.xpath(".//button[text()=\"БИК\"]");
	private final static By fieldReceiverBIC_MT = By.xpath(".//div[@title='БИК']/div[2]/div[1]/input");
	private final static By fieldReceiverBankName_MT = By.xpath(".//textarea[@disabled]"); // may lead to an error?
	
	private final static By buttonCloseForm = By.xpath(".//div[1]/div[1]/div[2]/button");
	private final static By buttonCreateRP = By.xpath(".//div[@class=\"menuActions__group\"][2]/div[1]/button");
	
	// Elements
	WebElement form;
	WebElement bRecBIC_MT;
	WebElement fRecBIC_MT;
	WebElement fRecBankName_MT;
	private WebElement bClose;
	WebElement bCreateRP;
	
	/** Get driver
	 *  + initialize form elements
	 */
	NewRPForm (WebDriver driver) {
		drvr = driver;
		wait = (new WebDriverWait(drvr, 10));
		
		form = drvr.findElement(formNewRP);
		bRecBIC_MT = form.findElement(buttonReceiverBIC_MT);
		fRecBIC_MT = form.findElement(fieldReceiverBIC_MT);
		fRecBankName_MT = form.findElement(fieldReceiverBankName_MT);
		bCreateRP = form.findElement(buttonCreateRP);
	}
	
	/** Form is loading while value in field is empty
	 */
	void waitForDataLoadOnForm() {
		WebElement fPayerAcc = form.findElement(fieldPayerAccount_MT);
		
		while (fPayerAcc.getAttribute("value").isEmpty());
	}
	
	/** Close the form
	 */
	void close() {
		bClose = form.findElement(buttonCloseForm);
		wait.until(ExpectedConditions.elementToBeClickable(bClose));

		bClose.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("appframe")));
	}
	
	void switchToMainTab() {
		form.findElement(buttonMainTabForm).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"PayDocRu__tabContent--muDt2 PayDocRu__mainTab--3jpGE\"]")));
	}
	
	void switchToSimpleTab() {
		form.findElement(buttonSimpleTabForm).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"PayDocRu__tabContent--muDt2 PayDocRu__simpleTab--1ZXGr\"]")));
	}
}
