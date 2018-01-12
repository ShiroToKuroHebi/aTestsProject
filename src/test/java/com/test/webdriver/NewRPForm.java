package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class NewRPForm {
	WebDriver drvr;
	WebDriverWait wait;
	
	// Page-specific locators
	final static By formNewRPRoot = By.xpath("//form");
	final static By formNewRPMainTab = By.xpath("//div[@class=\"PayDocRu__tabContent--muDt2 PayDocRu__mainTab--3jpGE\"]");
	final static By formNewRPSimpleTab = By.xpath("//div[@class=\"PayDocRu__tabContent--muDt2 PayDocRu__simpleTab--1ZXGr\"]");
	
	final static By buttonSimpleTabFormLong = By.xpath(".//div[@class=\"PayDocRu__header--3fL7X\"]//div[@class=\"RadioBtns__base--2hihs\"]/button[2]");
	final static By buttonSimpleTabForm = By.xpath(".//div[@class=\"PayDocRu__header--3fL7X\"]//button[2]"); // may lead to an error?
	final static By buttonMainTabForm = By.xpath(".//div[@class=\"PayDocRu__header--3fL7X\"]//button[1]"); // may lead to an error?
	
	final static By buttonCloseForm = By.xpath(".//div[1]/div[1]/div[2]/button");
	final static By buttonCreateRP = By.xpath(".//div[@class=\"menuActions__group\"][2]/div[1]/button");
	
	// Elements
	WebElement form;
	WebElement bCreateRP;
	
	NewRPForm () {
		//stub
	}
	
	NewRPForm (WebDriver driver) {
		this.drvr = driver;
		this.wait = new WebDriverWait(drvr,10);
		
		form = drvr.findElement(formNewRPRoot);
	}
	
	/** Close the form
	 */
	void close() {
		WebElement bClose = form.findElement(buttonCloseForm);
		wait.until(ExpectedConditions.elementToBeClickable(bClose));
		
		bClose.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("appframe")));
	}
	
	void switchToMainTab() {
		form.findElement(buttonMainTabForm).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(formNewRPMainTab));
	}
	
	void switchToSimpleTab() {
		form.findElement(buttonSimpleTabForm).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(formNewRPSimpleTab));
	}
}
