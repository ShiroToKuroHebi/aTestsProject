package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
	WebDriver drvr;
	
	private By buttonCreateNewRP = By.xpath(".//*[text()='Создать ПП']/..");
	
	public MainPage (WebDriver driver) {
		drvr = driver;
	}
	
	public void openFormCreateNewRP() {
		drvr.findElement(buttonCreateNewRP).click();
	}
}
