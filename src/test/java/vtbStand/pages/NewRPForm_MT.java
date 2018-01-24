package vtbStand.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** That would be the
 *  'Create new RP' form, 'mainTab'
 */
public class NewRPForm_MT extends NewRPForm {
	@FindBy(xpath = "//div[@title='Расчетный счет плательщика']//input")
	@CacheLookup
			private WebElement fieldPayerAccount;
	
	@FindBy(xpath = "//div[@title='Банк получателя']//textarea")
	@CacheLookup
			private WebElement fieldRecBankName;
	
	@FindBy(xpath = "//button[text()='БИК']")
	@CacheLookup
			private WebElement buttonRecBIC;
	
	@FindBy(xpath = "//div[@title='БИК']//input")
	@CacheLookup
			private WebElement fieldRecBIC;
	
	@FindBy(xpath = "//div[text()='Сообщение для банка']")
	@CacheLookup
			private WebElement headerMsg4BankBlock;
	
	@FindBy(xpath = "//div[contains(@class, 'PayDocRu__mainTab')]")
	@CacheLookup
			private WebElement form;
	
	
	
	// Control messages
	public final static String BIC_UNKNOWN = "Указанный БИК не найден в справочнике российских банков, выполнение проверки ключа счета не доступно";//, выполнение проверки ключа счета не доступно";
	public final static String BIC_TOO_SHORT = "БИК должен состоять из 9 цифр";
	public final static String BIC_MUST_BE_NONEMPTY = "Поле БИК банка получателя обязательно для заполнения";
	
	// Page-specific locators
	private final static By buttonMessageForBankDict_MT = By.xpath("//button[text()=\"Сообщение для банка\"]");
	
	// Elements
	public WebElement bMsg4B_MT;
	

	
	/** Form is loading while value in field is empty
	 *  TODO CHANGE IT!
	 */
	public void waitForDataLoadOnForm() {
		while (fieldPayerAccount.getAttribute("value").isEmpty());
	}
	
	/** Show 'Message for bank' data-block
	 *  By default - always hidden
	 */
	public void showMsg4BankBlock () {
		headerMsg4BankBlock.click();
		wait.until(ExpectedConditions.visibilityOf((bMsg4B_MT = form.findElement(buttonMessageForBankDict_MT))));
	}
	
	/** Hide 'Message for bank' data-block
	 *  By default - always hidden
	 */
	public void hideMsg4BankBlock() {
		headerMsg4BankBlock.click();
		wait.until(ExpectedConditions.invisibilityOf((bMsg4B_MT = form.findElement(buttonMessageForBankDict_MT))));
	}
	
	public BICRFForm openBICDictionary() {
		buttonRecBIC.click();
		return Page.bicRFForm();
	}

	public WebElement getForm() {	return form;	}
	public WebElement getFieldRecBankName() {	return fieldRecBankName;	}
	public WebElement getFieldRecBIC() {	return fieldRecBIC;	}
	
}
