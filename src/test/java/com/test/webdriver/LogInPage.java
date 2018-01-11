package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class LogInPage {
	
	WebDriver drvr;
	
	private By fieldUsername = By.xpath("//input[@type='text']");
	private By fieldPassword = By.xpath("//input[@type='password']");
	private By buttonLogin = By.xpath("//button[text()='Войти']");
	
	String CLIENT_LOGIN = "1111111111";
	String CLIENT_PSWRD = "1111111111";
	
	/** Авторизоваться в системе с именем пользователя CLIENT_LOGIN и паролем CLIENT_PSWRD
	 */
	void logIn() {
		typeUsername();
		typePassword();
		clickLoginButton();
	}
	
	/** Получить драйвер
	 */
	public LogInPage(WebDriver driver) {
		drvr = driver;
	}
	
	/** Заполнить поле имени пользователя строкой CLIENT_LOGIN
	 */
	public void typeUsername() {
		WebElement fLogin = this.drvr.findElement(fieldUsername);
		
		do {
			fLogin.clear();
			fLogin.sendKeys(CLIENT_LOGIN);
		} while (!fLogin.getAttribute("value").equals(CLIENT_LOGIN));
	}
	
	/** Заполнить поле пароля пользователя строкой CLIENT_PSWRD
	 */
	public void typePassword() {
		WebElement fPassword = this.drvr.findElement(fieldPassword);
		
		do {
			fPassword.clear();
			fPassword.sendKeys(CLIENT_PSWRD);
		} while (!fPassword.getAttribute("value").equals(CLIENT_PSWRD));
	}
	
	/** DO IT!
	 *  JUST... Log in!
	 *  // push the button
	 */
	public void clickLoginButton() {
		drvr.findElement(buttonLogin).click();
	}
}
