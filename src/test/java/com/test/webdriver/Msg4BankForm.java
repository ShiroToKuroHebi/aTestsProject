package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** DICTIONARY!!!
 */
class Msg4BankForm {
	private final static String fHeader = "Типовые сообщения для банка";
	
	// Page-specific locators
	private final static By formMessageForBank = By.xpath("//div[text()=\"" + fHeader + "\"]/../../..");
	private final static By buttonCreateNewMessage = By.xpath(".//span[text()=\"Добавить\"]");
	
	
	// Elements
	WebElement fMsg4B;
	
	
	
	Msg4BankForm (WebDriver driver) {
	
	}
}
