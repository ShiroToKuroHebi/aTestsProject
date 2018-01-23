package vtbStand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class MainPage extends Page{
	
	// Locators
	private final static By buttonCreateNewRP = By.xpath(".//div[text()='Создать ПП']");
	
	WebElement bCreateNewRP;

	/** Получить драйвер
	 */
	MainPage (WebDriver driver) {
		this.drvr = driver;
		this.wait = new WebDriverWait(driver, 10);
	}
	
	/** Press the 'Создать ПП' button
	 */
	void openFormCreateNewRP() {
		bCreateNewRP = this.drvr.findElement(buttonCreateNewRP);
		this.wait.until(ExpectedConditions.elementToBeClickable(bCreateNewRP));
		bCreateNewRP.click();
	}
}
