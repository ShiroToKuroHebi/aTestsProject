package vtbStand.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** DICTIONARY!!!
 */
public class Msg4BankForm extends Page{

	private final static String fHeader = "Типовые сообщения для банка";
	
	// Page-specific locators
	private final static By formMessageForBank = By.xpath("//div[text()=\"" + fHeader + "\"]/../../..");
	private final static By buttonCreateNewMessage = By.xpath(".//span[text()=\"Добавить\"]");
	
	
	// Elements
	WebElement fMsg4B;
	
	
	
	public Msg4BankForm (WebDriver driver) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(formMessageForBank));
	}
}
