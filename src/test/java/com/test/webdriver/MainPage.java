package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	WebDriver drvr;
	
	private By buttonCreateNewRP = By.xpath(".//div[text()='Создать ПП']");
	
	WebElement bCreateNewRP;
	
	/** Получить драйвер
	 */
	public MainPage (WebDriver driver) {
		drvr = driver;
	}
	
	/** Press the 'Создать ПП' button
	 */
	public void openFormCreateNewRP() {
		bCreateNewRP = drvr.findElement(buttonCreateNewRP);
		WebDriverWait timeout = (new WebDriverWait(drvr, 10));
		timeout.until(ExpectedConditions.elementToBeClickable(bCreateNewRP));
		bCreateNewRP.click();
	}
}
