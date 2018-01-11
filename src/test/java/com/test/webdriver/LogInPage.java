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
	
	/** Открыть и загрузить данные в форму создания нового ПП
	 *  + Возвращается formNewRP
	 */
	WebElement newRP () {
		// button: Create RP
		WebElement temp = this.drvr.findElement(By.xpath(".//*[text()='Создать ПП']/.."));
		temp.click();
		
		WebElement formNewRP = this.drvr.findElement(By.xpath("//*[@id=\"appframe\"]/form"));
		
		// field: random. Account works good for this
		temp = formNewRP.findElement(By.xpath("//div[@title=\"Расчетный счет плательщика\"]/div[2]/div[1]/input"));
		// Form is loading while value in field is empty
		while (temp.getAttribute("value").isEmpty());
		
		return formNewRP;
	}
	
	/** Получить уже преднастроенный драйвер
	 */
	public LogInPage(WebDriver driver) {
		drvr = driver;
	}
	
	/** Заполнить поле имени пользователя строкой CLIENT_LOGIN
	 */
	public void typeUsername() {
		WebElement loginField = this.drvr.findElement(fieldUsername);
		
		do {
			loginField.clear();
			loginField.sendKeys(CLIENT_LOGIN);
		} while (!loginField.getAttribute("value").equals(CLIENT_LOGIN));
	}
	
	/** Заполнить поле пароля пользователя строкой CLIENT_PSWRD
	 */
	public void typePassword() {
		WebElement passwordField = this.drvr.findElement(fieldPassword);
		
		do {
			passwordField.clear();
			passwordField.sendKeys(CLIENT_PSWRD);
		} while (!passwordField.getAttribute("value").equals(CLIENT_PSWRD));
	}
	
	/** DO IT!
	 *  JUST... Log in!
	 *  // push the button
	 */
	public void clickLoginButton() {
		drvr.findElement(buttonLogin).click();
	}
}
