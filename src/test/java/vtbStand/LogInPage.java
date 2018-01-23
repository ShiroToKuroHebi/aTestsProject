package vtbStand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

class LogInPage extends Page {
	
	// Page-specific locators
	private final static By fieldUsername = By.xpath("//input[@type='text']");
	private final static By fieldPassword = By.xpath("//input[@type='password']");
	private final static By buttonLogin = By.xpath("//button[text()='Войти']");
	
	/** Получить драйвер
	 *  + create DriverWait. It WILL be for all
	 */
	LogInPage (WebDriver driver) {
		this.drvr = driver;
		wait = new WebDriverWait(driver, 10);
	}
	
	/** Заполнить поле имени пользователя строкой login
	 */
	void typeUsername(String login) {
		WebElement fLogin = this.drvr.findElement(fieldUsername);
		
		do {
			fLogin.clear();
			fLogin.sendKeys(login);
		} while (!fLogin.getAttribute("value").equals(login));
	}
	
	/** Заполнить поле пароля пользователя строкой password
	 */
	void typePassword(String password) {
		WebElement fPassword = this.drvr.findElement(fieldPassword);
		
		do {
			fPassword.clear();
			fPassword.sendKeys(password);
		} while (!fPassword.getAttribute("value").equals(password));
	}
	
	/** Push the button
	 */
	void clickLoginButton() {
		drvr.findElement(buttonLogin).click();
	}
}
