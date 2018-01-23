package vtbStand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** DICTIONARY!!!
 */
class Msg4BankForm {
	private WebDriver drvr;
	private WebDriverWait wait;
	
	private final static String fHeader = "Типовые сообщения для банка";
	
	// Page-specific locators
	private final static By formMessageForBank = By.xpath("//div[text()=\"" + fHeader + "\"]/../../..");
	private final static By buttonCreateNewMessage = By.xpath(".//span[text()=\"Добавить\"]");
	
	
	// Elements
	WebElement fMsg4B;
	
	
	
	Msg4BankForm (WebDriver driver) {
		drvr = driver;
		wait = new WebDriverWait(drvr, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(formMessageForBank));
		
		
	}
}
