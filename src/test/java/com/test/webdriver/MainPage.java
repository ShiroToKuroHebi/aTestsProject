package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	WebDriver drvr;
	WebDriverWait wait;
	
	private By buttonCreateNewRP = By.xpath(".//div[text()='Создать ПП']");
	
	WebElement bCreateNewRP;
	
	/** Получить драйвер
	 */
	public MainPage (WebDriver driver) {
		drvr = driver;
		wait = (new WebDriverWait(drvr, 10));
		
		bCreateNewRP = drvr.findElement(buttonCreateNewRP);
	}
	
	/** Press the 'Создать ПП' button
	 */
	public void openFormCreateNewRP() {
		wait.until(ExpectedConditions.elementToBeClickable(bCreateNewRP));
		bCreateNewRP.click();
	}
}
