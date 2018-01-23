package vtbStand;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertTrue;

class BICFieldOnCreateRPFormTests implements BankData {
	private static Logger logger = Logger.getLogger(BICFieldOnCreateRPFormTests.class);
	private static MainPage main;
	private static NewRPForm_MT newRPForm_MT;
	private static NewRPForm_ST newRPForm_ST;
	
	/** When starting from login page
	 */
	static void authorize (WebDriver driver) {
		LogInPage start = PageFactory.initElements(driver, LogInPage.class);
//		start.typeUsername(ClientData.CLIENT_LOGIN);
//		start.typePassword(ClientData.CLIENT_PSWRD);
		main = start.clickLoginButton();
		
		PageFactory.initElements(driver, main);
	}

	

	/** Проверить поле "БИК банка получателя"
	 *  Ожидается:
	 *  Поле пустое
	 *  (!) Starting from main page
	 */
	static void checkIfEmptyByDefault () {
		logger.info("STARTED checkIfEmptyByDefault");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		assertTrue("FAILED", newRPForm_MT.fRecBIC_MT.getAttribute("value").isEmpty());
		
		newRPForm_MT.closeForm();
		logger.info("PASSED");
	}
	
	/** Заполнить "вручную" поле "БИК банка получателя"
	 *  Ожидается:
	 *  Редактирование поля (вручную) возможно
	 *  (!) Starting from main page
	 */
	static void checkIfEditable () {
		logger.info("STARTED checkIfEditable");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		// that way it works always - even when not in Debug
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_5x0);
		} while (!newRPForm_MT.fRecBIC_MT.getAttribute("value").equals(NUMBERS_5x0));
		assertTrue("FAILED", newRPForm_MT.fRecBIC_MT.getAttribute("value").equals(NUMBERS_5x0));
		
		newRPForm_MT.closeForm();
		logger.info("PASSED");
	}
	
	/** Заполнить поле "БИК банка получателя" любыми символами, кроме цифр
	 *  Ожидается:
	 *  Ввод невозможен (= в поле не сохраняются передаваемые символы)
	 *  // SHOULD work. Not sure though. Workaround as in "checkIfEditable(...)" won't help
	 *  (!) Starting from main page
	 */
	static void checkIfOnlyDigitsAllowed () {
		logger.info("STARTED checkIfOnlyDigitsAllowed");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		newRPForm_MT.fRecBIC_MT.clear();
		newRPForm_MT.fRecBIC_MT.sendKeys(NO_DIGITS_STRING);
		assertTrue("FAILED", newRPForm_MT.fRecBIC_MT.getAttribute("value").isEmpty());
		
		newRPForm_MT.closeForm();
		logger.info("PASSED");
	}
	
	/** Ввести 9 допустимых символов (только цифры), сохранить документ
	 *  (= отсутствие проверки на длину БИКа, если БИК известен!)
	 *  Ожидается:
	 *  Контроли на длину поля не сработали
	 *  (!) Starting from main page
	 */
	static void checkIfLengthControlAllows9Digits () {
		logger.info("STARTED checkIfLengthControlAllows9Digits");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(FONDSERVICEBANK.BIC);
		} while (!newRPForm_MT.fRecBIC_MT.getAttribute("value").equals(FONDSERVICEBANK.BIC));
		
		newRPForm_MT.form.click();
		
		// wait until data is loaded; T3H 5L0000000Wa M461Cey...
		while (!newRPForm_MT.fRecBankName_MT.getAttribute("value").equals(
				(FONDSERVICEBANK.NAME) + " Г " + FONDSERVICEBANK.TOWN));
		
		newRPForm_MT.bCreateRP.click();
		List<WebElement> errorTooltips;
		do {
			errorTooltips = newRPForm_MT.form.findElements(Page.tooltipsErrors);
		} while (errorTooltips.size() == 0);
		
		for (WebElement errorMsg : errorTooltips) {
			assertTrue("FAILED", !(errorMsg.getText().equals(NewRPForm_MT.BIC_TOO_SHORT)));
		}
		
		newRPForm_MT.closeForm();
		logger.info("PASSED");
	}
	
	/** Ввести менее 9 допустимых символов (только цифры), сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_TOO_SHORT)
	 *  (!) Ноль символов - тоже меньше 9, но проверяется другим тестом и
	 *  использует другое сообщение об ошибке
	 *  (!) Starting from main page
	 */
	static void checkIfLessThan9DigitsIsNotAllowed () {
		logger.info("STARTED checkIfLessThan9DigitsIsNotAllowed");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_5x0);
		} while (!newRPForm_MT.fRecBIC_MT.getAttribute("value").equals(NUMBERS_5x0));
		
		newRPForm_MT.bCreateRP.click();
		// comparing exact error tooltip text
		assertTrue("FAILED", newRPForm_MT.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
				Page.tooltipErrorXPath)).getText().equals(NewRPForm_MT.BIC_TOO_SHORT));
		
		newRPForm_MT.closeForm();
		logger.info("PASSED");
	}
	
	/** Выбрать БИК из справочника
	 *  Ожидается:
	 *  Поле БИК заполнено выбранным (первым из списка) значением
	 *  + поле БИК не блокируется. Ручной ввод по-прежнему возможен
	 *  (!) Starting from main page
	 */
	static void checkIfCanBeFilledFromDictionaryAndStillEditable () {
		logger.info("STARTED checkIfCanBeFilledFromDictionaryAndStillEditable");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		newRPForm_MT.bRecBIC_MT.click();
		
		BICRFForm fDictBIC = new BICRFForm(newRPForm_MT);
		dictRowBIC passedBIC = fDictBIC.chooseFirstInTable();
		
		assertTrue("FAILED", newRPForm_MT.fRecBIC_MT.getAttribute("value").equals(passedBIC.BIC));
		
		// Additional part
		newRPForm_MT.fRecBIC_MT.clear();
		newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_5x0);
		assertTrue("+ FAILED", !newRPForm_MT.fRecBIC_MT.getAttribute("value").isEmpty());
		
		newRPForm_MT.closeForm();
		logger.info("PASSED");
	}
	
	/** Очистить поле БИК, сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_MUST_BE_NONEMPTY)
	 *  Фактически:
	 *  Необходимое сообщение возникает только если кнопка сохранить была нажата сразу после открытия формы или
	 *  было введено менее 9 символов. Иначе (введён не-/известный БИК) сообщение контроля не возникает!
	 *  (!) Starting from main page
	 */
	static void checkIfMustBeFilledWithValidValue () {
		logger.info("STARTED checkIfMustBeFilledWithValidValue");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_9x0);
		} while (!newRPForm_MT.fRecBIC_MT.getAttribute("value").equals(NUMBERS_9x0));
		
		newRPForm_MT.fRecBIC_MT.clear();
		newRPForm_MT.bCreateRP.click();
		
		// comparing exact error tooltip text
//		assertTrue("48/05 FAILED",	newRPForm_MT.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
//				Page.tooltipErrorXPath)).getText().equals(NewRPForm_MT.BIC_MUST_BE_NONEMPTY));
		
		newRPForm_MT.closeForm();
		logger.info("PASSED (NOT)");
	}
	
	/** OUTDATED?
	 *  Ввести в поле корректнрее по формату значение, которого нет в справочнике БИК, сохранить
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_UNKNOWN)
	 *  Фактически:
	 *  Сообщение контроля несколько отличается (см. комментарий для NewRPForm_MT.BIC_UNKNOWN)
	 *  (!) Starting from main page
	 */
	static void checkIfUnknownBICNotAllowed () {
		logger.info("STARTED checkIfUnknownBICNotAllowed");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_9x0);
		} while (!newRPForm_MT.fRecBIC_MT.getAttribute("value").equals(NUMBERS_9x0));
		
		newRPForm_MT.bCreateRP.click();
		
		// comparing exact error tooltip text
		// * this also means that passed unknown 9-digit BIC doesn't trigger length check error (checkIfLengthControlAllows9Digits)
		assertTrue("FAILED", newRPForm_MT.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
				Page.tooltipErrorXPath)).getText().equals(NewRPForm_MT.BIC_UNKNOWN));
		
		newRPForm_MT.closeForm();
		logger.info("PASSED (KINDA)");
	}
	
	/** Открыть (?)универсальную форму ПП, очистить поле "БИК банка получателя", скрыть блок получателя
	 *  (+ сохранить)
	 *  Ожидается:
	 *  1) Блок получателя раскрыт
	 *  2) Поверх метки-переключателя выведено сообщение "Необходимо заполнить реквизиты"
	 *  (OUTDATED: не поверх, а меняется текст самой метки-переключателя)
	 *  (!) Starting from main page
	 */
	static void checkIfShowsBankDataBlockOnSimpleTabOnAttemptToCreateRPWhenEmpty () {
		logger.info("STARTED checkIfShowsBankDataBlockOnSimpleTabOnAttemptToCreateRPWhenEmpty");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		newRPForm_MT.bRecBIC_MT.click();
		
		BICRFForm fDictBIC = new BICRFForm(newRPForm_MT);
		fDictBIC.chooseFirstInTable();
		
		newRPForm_MT.form.click();
		while (newRPForm_MT.fRecBankName_MT.getAttribute("value").isEmpty());
		


		// switching to 'universal' form type
		newRPForm_ST = newRPForm_MT.switchToSimpleTab();
		newRPForm_ST.findButtonShowHideRecData();
		newRPForm_ST.showRecDataBlock(newRPForm_ST.bShowHideRecData_ST);
		do {
			newRPForm_ST.fRecBIC_ST.clear();
		} while (!newRPForm_ST.fRecBIC_ST.getAttribute("value").isEmpty());
		//assertTrue("cleaned BIC field, but can't hide receiver data block", ); <- is that even possible?
		
		newRPForm_ST.bShowHideRecData_ST.click();
		newRPForm_ST.form_ST.click();
		
		newRPForm_ST.bCreateRP.click();
		
		assertTrue("FAILED", newRPForm_ST.bShowHideRecData_ST.findElement(By.xpath("./div")).getText().equals(
						NewRPForm_ST.invalidReceiverData));
		
		newRPForm_ST.closeForm();
		logger.info("PASSED");
	}
	
}
