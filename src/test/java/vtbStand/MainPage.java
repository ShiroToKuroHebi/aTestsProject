package vtbStand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class MainPage {
	private WebDriver drvr;
	private WebDriverWait wait;
	
	// Locators
	private final static By buttonCreateNewRP = By.xpath(".//div[text()='Создать ПП']");
	


	/** Получить драйвер
	 */
	MainPage (WebDriver driver) {
		drvr = driver;
		wait = new WebDriverWait(drvr, 10);
	}
	
	/** Press the 'Создать ПП' button
	 */
	void openFormCreateNewRP() {
		WebElement bCreateNewRP = drvr.findElement(buttonCreateNewRP);
		wait.until(ExpectedConditions.elementToBeClickable(bCreateNewRP));
		bCreateNewRP.click();
	}
}
