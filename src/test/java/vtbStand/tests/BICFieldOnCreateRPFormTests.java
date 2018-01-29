package vtbStand.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import vtbStand.*;
import vtbStand.pages.*;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BICFieldOnCreateRPFormTests extends Tests {
	private static LogInPage login;
	private static MainPage main;
	private static NewRPForm_MT newRPForm_MT;
	private static NewRPForm_ST newRPForm_ST;
	private static BICRFForm bicRFForm;
	
	@Before
	public void setUp() throws IOException {
		super.setUp();
		
		login = Page.logInPage();
		//		login.typeUsername(ClientData.BILALOVA_L_R.getPassword());
		//		login.typePassword(ClientData.BILALOVA_L_R.getUsername());
		
		main = login.clickLoginButton();
		logger.info("Logged in as default user");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
	}
	

	
	/** Проверить поле "БИК банка получателя"
	 *  Ожидается:
	 *  Поле пустое
	 */
	@Test
	public void checkIfEmptyByDefault() {
		logger.info("checkIfEmptyByDefault");
		
		assertTrue("FAILED", newRPForm_MT.getFieldRecBIC().getAttribute("value").isEmpty());
	}
	
	/** Заполнить "вручную" поле "БИК банка получателя"
	 *  Ожидается:
	 *  Редактирование поля (вручную) возможно
	 */
	@Test
	public void checkIfEditable() {
		logger.info("checkIfEditable");
		
		// that way it works always - even when not in Debug
		assertTrue("Failed to pass '" + BankData.NUMBERS_5x0 + "' to field 'БИК (банка получателя)'",
				Page.fillFieldWithValidValue(newRPForm_MT.getFieldRecBIC(), BankData.NUMBERS_5x0));
	}
	
	/** Заполнить поле "БИК банка получателя" любыми символами, кроме цифр
	 *  Ожидается:
	 *  Ввод невозможен (= в поле не сохраняются передаваемые символы)
	 *  // SHOULD work. Not sure though. Workaround as in "checkIfEditable(...)" won't help
	 */
	@Test
	public void checkIfOnlyDigitsAllowed() {
		logger.info("checkIfOnlyDigitsAllowed");
		
		// Not using Page.fillFieldWithValidValue(), because empty field expected after sendKeys()
		newRPForm_MT.getFieldRecBIC().sendKeys(BankData.NO_DIGITS_STRING);
		
		assertTrue("FAILED", newRPForm_MT.getFieldRecBIC().getAttribute("value").isEmpty());
	}
	
	/** Ввести 9 допустимых символов (только цифры), сохранить документ
	 *  (= отсутствие проверки на длину БИКа, если БИК известен!)
	 *  Ожидается:
	 *  Контроли на длину поля не сработали
	 */
	@Test
	public void checkIfLengthControlAllows9Digits() {
		logger.info("checkIfLengthControlAllows9Digits");
		
		assertTrue("Failed to pass '" + BankData.FONDSERVICEBANK.getBIC() + "' to field 'БИК (банка получателя)'",
				Page.fillFieldWithValidValue(newRPForm_MT.getFieldRecBIC(), BankData.FONDSERVICEBANK.getBIC()));
		
		newRPForm_MT.getForm().click();
		
		// wait until data is loaded
		newRPForm_MT.wait.until(ExpectedConditions.textToBePresentInElement(
				newRPForm_MT.getFieldRecBankName(),
				BankData.FONDSERVICEBANK.getName() + " Г " + BankData.FONDSERVICEBANK.getTown()));
		

		newRPForm_MT.clickCreateRP();
		List<WebElement> errorTooltips;
		newRPForm_MT.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Page.tooltipErrorXPath)));
		errorTooltips = newRPForm_MT.getForm().findElements(By.xpath(Page.tooltipErrorXPath));
		
		for (WebElement errorMsg : errorTooltips) {
			assertTrue("FAILED", !(errorMsg.getText().equals(NewRPForm_MT.BIC_TOO_SHORT)));
		}
	}
	
	/** Ввести менее 9 допустимых символов (только цифры), сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_TOO_SHORT)
	 *  (!) Ноль символов - тоже меньше 9, но проверяется другим тестом и
	 *  использует другое сообщение об ошибке
	 *  (!) Можно использовать в качестве проверки на редактируемость (вместо checkIfEditable())
	 */
	@Test
	public void checkIfLessThan9DigitsIsNotAllowed() {
		logger.info("checkIfLessThan9DigitsIsNotAllowed");
		
		assertTrue("Failed to pass '" + BankData.NUMBERS_5x0 + "' to field 'БИК (банка получателя)'",
				Page.fillFieldWithValidValue(newRPForm_MT.getFieldRecBIC(), BankData.NUMBERS_5x0));
		
		newRPForm_MT.clickCreateRP();
		
		// comparing exact error tooltip text
		assertTrue("FAILED", newRPForm_MT.getForm()
				.findElement(By.xpath("//div[@title=\"БИК\"]" + Page.tooltipErrorXPath))
				.getText().equals(NewRPForm_MT.BIC_TOO_SHORT));
	}
	
	/** Выбрать БИК из справочника
	 *  Ожидается:
	 *  Поле БИК заполнено выбранным (первым из списка) значением
	 *  + поле БИК не блокируется. Ручной ввод по-прежнему возможен
	 */
	@Test
	public void checkIfCanBeFilledFromDictionaryAndStillEditable() {
		logger.info("checkIfCanBeFilledFromDictionaryAndStillEditable");
		
		bicRFForm = newRPForm_MT.openBICDictionary();
		WebElement row = bicRFForm.getTableRows().get(0);
		row.click();
		bicRFForm.apply();
		
		//assertTrue("FAILED", newRPForm_MT.getFieldRecBIC().getAttribute("value").equals(new BankData(row).getBIC()));
		//if done using DictRowBIC() /\
		assertTrue(
				"FAILED",
				newRPForm_MT.getFieldRecBIC().getAttribute("value").equals(
						row.findElement(By.xpath(".//div/div[0]/span")).getText())
		);
		
		// Additional part
		newRPForm_MT.getFieldRecBIC().clear();

		assertTrue("Failed to pass '" + BankData.NUMBERS_5x0 + "' to field 'БИК (банка получателя)'",
				Page.fillFieldWithValidValue(newRPForm_MT.getFieldRecBIC(), BankData.NUMBERS_5x0));

		assertTrue("+ FAILED", !newRPForm_MT.getFieldRecBIC().getAttribute("value").isEmpty());
	}
	
	/** Очистить поле БИК, сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_MUST_BE_NONEMPTY)
	 *  Фактически:
	 *  Необходимое сообщение возникает только если кнопка сохранить была нажата сразу после
	 *  открытия формы или было введено значение и фокус был перемещён с поля.
	 *  Иначе (введён не-/известный БИК) сообщение не возникает!
	 */
	@Test
	public void checkIfMustBeNonempty () {
		logger.info("checkIfMustBeNonempty");
		
		assertTrue("Failed to pass '" + BankData.NUMBERS_9x0 + "' to field 'БИК (банка получателя)'",
				Page.fillFieldWithValidValue(newRPForm_MT.getFieldRecBIC(), BankData.NUMBERS_9x0));
		
		// перевести фокус с поля
		newRPForm_MT.getForm().click();
		
		newRPForm_MT.getFieldRecBIC().clear();
		newRPForm_MT.clickCreateRP();
		
		// comparing exact error tooltip text
		assertTrue("FAILED",	newRPForm_MT.getForm()
				.findElement(By.xpath("//div[@title=\"БИК\"]" + Page.tooltipErrorXPath))
				.getText().equals(NewRPForm_MT.BIC_MUST_BE_NONEMPTY));
	}
	
	/** OUTDATED?
	 *  Ввести в поле корректнрее по формату значение, которого нет в справочнике БИК, сохранить
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_UNKNOWN)
	 *  Фактически:
	 *  Сообщение контроля несколько отличается (см. комментарий для NewRPForm_MT.BIC_UNKNOWN)
	 */
	@Test
	public void checkIfUnknownBICNotAllowed() {
		logger.info("checkIfUnknownBICNotAllowed (looks like it's outdated)");
		
		assertTrue("Failed to pass '" + BankData.NUMBERS_9x0 + "' to field 'БИК (банка получателя)'",
				Page.fillFieldWithValidValue(newRPForm_MT.getFieldRecBIC(), BankData.NUMBERS_9x0));
		
		newRPForm_MT.clickCreateRP();
		
		// comparing exact error tooltip text
		// * this also means that passed unknown 9-digit BIC doesn't trigger length check error (checkIfLengthControlAllows9Digits)
		assertTrue("FAILED", newRPForm_MT.getForm()
				.findElement(By.xpath("//div[@title=\"БИК\"]" + Page.tooltipErrorXPath))
				.getText().equals(NewRPForm_MT.BIC_UNKNOWN));
	}
	
	/** Открыть (?)универсальную форму ПП, очистить поле "БИК банка получателя", скрыть блок получателя
	 *  (+ сохранить)
	 *  Ожидается:
	 *  1) Блок получателя раскрыт
	 *  2) Поверх метки-переключателя выведено сообщение "Необходимо заполнить реквизиты"
	 *  (OUTDATED: не поверх, а меняется текст самой метки-переключателя)
	 */
	@Test
	public void checkIfShowsBankDataBlockOnSimpleTabOnAttemptToCreateRPWhenEmpty() {
		logger.info("checkIfShowsBankDataBlockOnSimpleTabOnAttemptToCreateRPWhenEmpty");
		
		// fill BIC field from dictionary
		bicRFForm = newRPForm_MT.openBICDictionary();
		bicRFForm.getTableRows().get(0).click();
		bicRFForm.apply();
		
		// wait until data is loaded
		newRPForm_MT.getForm().click();
		newRPForm_MT.wait.until(ExpectedConditions.attributeToBeNotEmpty(newRPForm_MT.getFieldRecBankName(),"value"));
		


		// switching to 'universal' form type
		newRPForm_ST = newRPForm_MT.switchToSimpleTab();
		
		// show receiver data block
		newRPForm_ST.getButtonShowHideRecData().click();
		
		// clear BIC field
		newRPForm_ST.getFieldRecBIC().clear();
		
		// hide receiver data block
		newRPForm_ST.showRecDataBlock();
		
		//newRPForm_ST.getForm().click();
		
		newRPForm_ST.clickCreateRP();
		
		assertTrue("FAILED", newRPForm_ST.getButtonShowHideRecData()
				.findElement(By.xpath("./div"))
				.getText().equals(NewRPForm_ST.invalidReceiverData));
	}

}
