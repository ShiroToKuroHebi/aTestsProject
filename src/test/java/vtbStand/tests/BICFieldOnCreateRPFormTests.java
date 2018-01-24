package vtbStand.tests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import vtbStand.*;
import vtbStand.pages.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
/*
Инициализацию  элементов страницы через PageFactory лучше сделать в конструкторе абсрактной страницы Page,
В Page лучше создать также объект драйвера, что он был во всех классах- страницах доступен и не кидать его в параметрах методов разных
 */
public class BICFieldOnCreateRPFormTests {
	private static WebDriver chrome = new ChromeDriver();
	private static PropertyValues settings = new PropertyValues();
	private static Logger logger = Logger.getLogger(BICFieldOnCreateRPFormTests.class);


	private static LogInPage login;
	private static MainPage main;
	private static NewRPForm_MT newRPForm_MT;
	private static NewRPForm_ST newRPForm_ST;
	
	@Before
	public void setUp() throws IOException {
		chrome.get(settings.getStandURL());
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Page.initPage(chrome);
		
		login = Page.logInPage();
//		login.typeUsername(ClientData.BILALOVA_L_R.getPassword());
//		login.typePassword(ClientData.BILALOVA_L_R.getUsername());
		
		main = login.clickLoginButton();
	}

	

	/** Проверить поле "БИК банка получателя"
	 *  Ожидается:
	 *  Поле пустое
	 */
	@Test
	public void checkIfEmptyByDefault() {
		logger.info("STARTED checkIfEmptyByDefault");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
//		newRPForm_MT.waitForDataLoadOnForm();
		
		assertTrue("FAILED", newRPForm_MT.getFieldRecBIC().getAttribute("value").isEmpty());
		
		logger.info("PASSED");
	}
	
	/** Заполнить "вручную" поле "БИК банка получателя"
	 *  Ожидается:
	 *  Редактирование поля (вручную) возможно
	 *  TODO CHANGE IT!
	 */
	@Test
	public void checkIfEditable() {
		logger.info("STARTED checkIfEditable");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		// that way it works always - even when not in Debug

		//А если условие выхода из цикла никонда не выполнится, он тут так и будет висеть?
		do {
			newRPForm_MT.getFieldRecBIC().clear();
			newRPForm_MT.getFieldRecBIC().sendKeys(BankData.NUMBERS_5x0);
		} while (!newRPForm_MT.getFieldRecBIC().getAttribute("value").equals(BankData.NUMBERS_5x0));
		assertTrue("FAILED", newRPForm_MT.getFieldRecBIC().getAttribute("value").equals(BankData.NUMBERS_5x0));
		
		logger.info("PASSED");
	}
	
	/** Заполнить поле "БИК банка получателя" любыми символами, кроме цифр
	 *  Ожидается:
	 *  Ввод невозможен (= в поле не сохраняются передаваемые символы)
	 *  // SHOULD work. Not sure though. Workaround as in "checkIfEditable(...)" won't help
	 */
	@Test
	public void checkIfOnlyDigitsAllowed() {
		logger.info("STARTED checkIfOnlyDigitsAllowed");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		newRPForm_MT.getFieldRecBIC().sendKeys(BankData.NO_DIGITS_STRING);
		assertTrue("FAILED", newRPForm_MT.getFieldRecBIC().getAttribute("value").isEmpty());
		
		logger.info("PASSED");
	}
	
	/** Ввести 9 допустимых символов (только цифры), сохранить документ
	 *  (= отсутствие проверки на длину БИКа, если БИК известен!)
	 *  Ожидается:
	 *  Контроли на длину поля не сработали
	 *  TODO CHANGE IT!
	 */
	@Test
	public void checkIfLengthControlAllows9Digits() {
		logger.info("STARTED checkIfLengthControlAllows9Digits");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();

		//Не надо использовать циклы для ожиданий событий со страницей!
		do {
			newRPForm_MT.getFieldRecBIC().clear();
			newRPForm_MT.getFieldRecBIC().sendKeys(BankData.FONDSERVICEBANK.getBIC());
		} while (!newRPForm_MT.getFieldRecBIC().getAttribute("value").equals(BankData.FONDSERVICEBANK.getBIC()));
		
		newRPForm_MT.getForm().click();
		
		// wait until data is loaded
		newRPForm_MT.wait.until(ExpectedConditions.textToBePresentInElement(
				newRPForm_MT.getFieldRecBankName(),
				BankData.FONDSERVICEBANK.getName() + " Г " + BankData.FONDSERVICEBANK.getTown()));
		
		newRPForm_MT.clickCreateRP();
		List<WebElement> errorTooltips;

		//Не надо использовать циклы для ожиданий событий со страницей!
		do {
			errorTooltips = newRPForm_MT.getForm().findElements(By.xpath(Page.tooltipErrorXPath));
		} while (errorTooltips.size() == 0);
		
		for (WebElement errorMsg : errorTooltips) {
			assertTrue("FAILED", !(errorMsg.getText().equals(NewRPForm_MT.BIC_TOO_SHORT)));
		}
		
		logger.info("PASSED");
	}
	
	/** Ввести менее 9 допустимых символов (только цифры), сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_TOO_SHORT)
	 *  (!) Ноль символов - тоже меньше 9, но проверяется другим тестом и
	 *  использует другое сообщение об ошибке
	 *  TODO CHANGE IT!
	 */
	@Test
	public void checkIfLessThan9DigitsIsNotAllowed() {
		logger.info("STARTED checkIfLessThan9DigitsIsNotAllowed");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		//Не надо использовать циклы для ожиданий событий со страницей!
		do {
			newRPForm_MT.getFieldRecBIC().clear();
			newRPForm_MT.getFieldRecBIC().sendKeys(BankData.NUMBERS_5x0);
		} while (!newRPForm_MT.getFieldRecBIC().getAttribute("value").equals(BankData.NUMBERS_5x0));
		
		newRPForm_MT.clickCreateRP();
		// comparing exact error tooltip text
		assertTrue("FAILED", newRPForm_MT.getForm().findElement(By.xpath("//div[@title=\"БИК\"]" +
				Page.tooltipErrorXPath)).getText().equals(NewRPForm_MT.BIC_TOO_SHORT));
		
		logger.info("PASSED");
	}
	
	/** Выбрать БИК из справочника
	 *  Ожидается:
	 *  Поле БИК заполнено выбранным (первым из списка) значением
	 *  + поле БИК не блокируется. Ручной ввод по-прежнему возможен
	 */
	@Test
	public void checkIfCanBeFilledFromDictionaryAndStillEditable() {
		logger.info("STARTED checkIfCanBeFilledFromDictionaryAndStillEditable");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		BICRFForm fDictBIC = newRPForm_MT.openBICDictionary();
		DictRowBIC passedBIC = fDictBIC.chooseFirstInTable();
		
		assertTrue("FAILED", newRPForm_MT.getFieldRecBIC().getAttribute("value").equals(passedBIC.BIC));
		
		// Additional part
		newRPForm_MT.getFieldRecBIC().clear();
		newRPForm_MT.getFieldRecBIC().sendKeys(BankData.NUMBERS_5x0);
		assertTrue("+ FAILED", !newRPForm_MT.getFieldRecBIC().getAttribute("value").isEmpty());
		
		logger.info("PASSED");
	}
	
	/** Очистить поле БИК, сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_MUST_BE_NONEMPTY)
	 *  Фактически:
	 *  Необходимое сообщение возникает только если кнопка сохранить была нажата сразу после открытия формы или
	 *  было введено менее 9 символов. Иначе (введён не-/известный БИК) сообщение контроля не возникает!
	 *  TODO CHANGE IT!
	 */
	@Test
	public void checkIfMustBeFilledWithValidValue() {
		logger.info("STARTED checkIfMustBeFilledWithValidValue");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		//Не надо использовать циклы для ожиданий событий со страницей!
		do {
			newRPForm_MT.getFieldRecBIC().clear();
			newRPForm_MT.getFieldRecBIC().sendKeys(BankData.NUMBERS_9x0);
		} while (!newRPForm_MT.getFieldRecBIC().getAttribute("value").equals(BankData.NUMBERS_9x0));
		
		newRPForm_MT.getFieldRecBIC().clear();
		newRPForm_MT.clickCreateRP();
		
		// comparing exact error tooltip text
//		assertTrue("48/05 FAILED",	newRPForm_MT.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
//				Page.tooltipErrorXPath)).getText().equals(NewRPForm_MT.BIC_MUST_BE_NONEMPTY));
		
		logger.info("PASSED (NOT)");
	}
	
	/** OUTDATED?
	 *  Ввести в поле корректнрее по формату значение, которого нет в справочнике БИК, сохранить
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_UNKNOWN)
	 *  Фактически:
	 *  Сообщение контроля несколько отличается (см. комментарий для NewRPForm_MT.BIC_UNKNOWN)
	 *  TODO CHANGE IT!
	 */
	@Test
	public void checkIfUnknownBICNotAllowed() {
		logger.info("STARTED checkIfUnknownBICNotAllowed");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		do {
			newRPForm_MT.getFieldRecBIC().clear();
			newRPForm_MT.getFieldRecBIC().sendKeys(BankData.NUMBERS_9x0);
		} while (!newRPForm_MT.getFieldRecBIC().getAttribute("value").equals(BankData.NUMBERS_9x0));
		
		newRPForm_MT.clickCreateRP();
		
		// comparing exact error tooltip text
		// * this also means that passed unknown 9-digit BIC doesn't trigger length check error (checkIfLengthControlAllows9Digits)
		assertTrue("FAILED", newRPForm_MT.getForm().findElement(By.xpath("//div[@title=\"БИК\"]" +
				Page.tooltipErrorXPath)).getText().equals(NewRPForm_MT.BIC_UNKNOWN));
		
		logger.info("PASSED (KINDA)");
	}
	
	/** Открыть (?)универсальную форму ПП, очистить поле "БИК банка получателя", скрыть блок получателя
	 *  (+ сохранить)
	 *  Ожидается:
	 *  1) Блок получателя раскрыт
	 *  2) Поверх метки-переключателя выведено сообщение "Необходимо заполнить реквизиты"
	 *  (OUTDATED: не поверх, а меняется текст самой метки-переключателя)
	 *  TODO CHANGE IT!
	 */
	@Test
	public void checkIfShowsBankDataBlockOnSimpleTabOnAttemptToCreateRPWhenEmpty() {
		logger.info("STARTED checkIfShowsBankDataBlockOnSimpleTabOnAttemptToCreateRPWhenEmpty");
		
		newRPForm_MT = main.openFormCreateNewRP().switchToMainTab();
		
		BICRFForm fDictBIC = newRPForm_MT.openBICDictionary();
		fDictBIC.chooseFirstInTable();
		
		newRPForm_MT.getForm().click();
		while (newRPForm_MT.getFieldRecBankName().getAttribute("value").isEmpty());
		


		// switching to 'universal' form type
		newRPForm_ST = newRPForm_MT.switchToSimpleTab();
		newRPForm_ST.findButtonShowHideRecData();
		newRPForm_ST.showRecDataBlock(newRPForm_ST.bShowHideRecData_ST);
		//Не надо использовать циклы для ожиданий событий со страницей!
		do {
			newRPForm_ST.fRecBIC_ST.clear();
		} while (!newRPForm_ST.fRecBIC_ST.getAttribute("value").isEmpty());
		//assertTrue("cleaned BIC field, but can't hide receiver data block", ); <- is that even possible?
		
		newRPForm_ST.bShowHideRecData_ST.click();
		newRPForm_ST.getForm().click();
		
		newRPForm_ST.clickCreateRP();
		
		assertTrue("FAILED", newRPForm_ST.bShowHideRecData_ST.findElement(By.xpath("./div")).getText().equals(
						NewRPForm_ST.invalidReceiverData));
		
		logger.info("PASSED");
	}
	
	@After
	public void tearDown() {
		logger.info("-------");
		chrome.quit();
	}
}
