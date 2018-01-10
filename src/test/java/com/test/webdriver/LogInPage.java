package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

class LogInPage {
	
	/** Перейти на стенд (+ начальная настройка WebDriver'а)
	 */
	static void goToStand(WebDriver drvr, String url) {
		drvr.get(url);
		drvr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("Wrong URL!", "VTB DBO front", drvr.getTitle());
	}
	
	/** Авторизоваться в системе
	 */
	static void logIn(WebDriver drvr) {
		WebElement formLogIn = drvr.findElement(By.xpath("//form"));
		WebElement loginField = formLogIn.findElement(By.xpath("//input[@type='text']"));
		WebElement passwordField = formLogIn.findElement(By.xpath("//input[@type='password']"));
		WebElement buttonLogIn = formLogIn.findElement(By.xpath("//button[text()='Войти']"));
		
		do {
			loginField.clear();
			loginField.sendKeys(Data.CLIENT_LOGIN);
		} while (!loginField.getAttribute("value").equals(Data.CLIENT_LOGIN));
		do {
			passwordField.clear();
			passwordField.sendKeys(Data.CLIENT_PSWRD);
		} while (!loginField.getAttribute("value").equals(Data.CLIENT_PSWRD));
		buttonLogIn.click();
	}
	
	/** Открыть и загрузить данные в форму создания нового ПП
	 *  + Возвращается formNewRP
	 */
	static WebElement newRP (WebDriver drvr) {
		// button: Create RP
		WebElement temp = drvr.findElement(By.xpath(".//*[text()='Создать ПП']/.."));
		temp.click();
		
		WebElement formNewRP = drvr.findElement(By.xpath("//*[@id=\"appframe\"]/form"));
		
		// field: random. Account works good for this
		temp = formNewRP.findElement(By.xpath("//div[@title=\"Расчетный счет плательщика\"]/div[2]/div[1]/input"));
		// Form is loading while value in field is empty
		while (temp.getAttribute("value").isEmpty());
		
		return formNewRP;
	}
}
