package vtbStand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class LogInPage {
	private WebDriver drvr;
	
	// Page-specific locators
	private final static By fieldUsername = By.xpath("//input[@type='text']");
	private final static By fieldPassword = By.xpath("//input[@type='password']");
	private final static By buttonLogin = By.xpath("//button[text()='Войти']");

	// Page-specific data
	private final static String CLIENT_LOGIN = "1111111111";
	private final static String CLIENT_PSWRD = "1111111111";

	
	
	/** Авторизоваться в системе с именем пользователя CLIENT_LOGIN и паролем CLIENT_PSWRD
	 */
	void logIn() {
		typeUsername();
		typePassword();
		clickLoginButton();
	}
	
	/** Получить драйвер
	 */
	LogInPage(WebDriver driver) {
		drvr = driver;
	}
	
	/** Заполнить поле имени пользователя строкой CLIENT_LOGIN
	 */
	void typeUsername() {
		WebElement fLogin = this.drvr.findElement(fieldUsername);
		
		do {
			fLogin.clear();
			fLogin.sendKeys(CLIENT_LOGIN);
		} while (!fLogin.getAttribute("value").equals(CLIENT_LOGIN));
	}
	
	/** Заполнить поле пароля пользователя строкой CLIENT_PSWRD
	 */
	void typePassword() {
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
	void clickLoginButton() {
		drvr.findElement(buttonLogin).click();
	}
}
