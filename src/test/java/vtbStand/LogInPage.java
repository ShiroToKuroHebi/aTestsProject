package vtbStand;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends Page {
	
	@FindBy(xpath = "//input[@type='text']")
			private WebElement fieldUsername;
	
	@FindBy(xpath = "//input[@type='password']")
			private WebElement fieldPassword;
	
	@FindBy(xpath = "//button[text()='Войти']")
			private WebElement buttonLogin;
	

	
	public LogInPage (WebDriver driver) {
		this.drvr = driver;
	}

	/** Заполнить поле имени пользователя строкой login
	 */
	public void typeUsername(String login) {
		fieldUsername.clear();
		fieldUsername.sendKeys(login);
	}
	
	/** Заполнить поле пароля пользователя строкой password
	 */
	public void typePassword(String password) {
		fieldPassword.clear();
		fieldPassword.sendKeys(password);
	}
	
	/** Successful authorization
	 */
	public MainPage clickLoginButton() {
		buttonLogin.click();
		return new MainPage(drvr);
	}
}
