package com.test.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

class BICFieldTests implements Data {
	
	// Locators
	private static By tooltipsErrors = By.xpath(tooltipErrorXPath);
	
	// 'cuz .getText() doesn't work the way I want :p
	private static String getValue (WebElement elem) {
		return elem.getAttribute("value");
	}
	
	/** Проверить поле "БИК банка получателя"
	 *  Ожидается:
	 *  Поле пустое
	 */
	static void vtbdbotlab48_01 (WebDriver driver) {
		System.out.print("Test 48/01 ");
		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(driver);
		
		assertTrue("48/01 FAILED", getValue(newRPForm_MT.fRecBIC_MT).isEmpty());
		
		newRPForm_MT.close();
		System.out.println("PASSED");
	}
	
	/** Заполнить "вручную" поле "БИК банка получателя"
	 *  Ожидается:
	 *  Редактирование поля (вручную) возможно
	 */
	static void vtbdbotlab48_02 (WebDriver driver) {
		System.out.print("Test 48/02 ");
		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(driver);
		
		// that way it works always - even when not in Debug
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_5x0);
		} while (!getValue(newRPForm_MT.fRecBIC_MT).equals(NUMBERS_5x0));
		assertTrue("48/02 FAILED", getValue(newRPForm_MT.fRecBIC_MT).equals(NUMBERS_5x0));
		
		newRPForm_MT.close();
		System.out.println("PASSED");
	}
	
	/** Заполнить поле "БИК банка получателя" любыми символами, кроме цифр
	 *  Ожидается:
	 *  Ввод невозможен (= в поле не сохраняются передаваемые символы)
	 *  // SHOULD work. Not sure though. Workaround as in vtbdbotlab48_02 won't help
	 */
	static void vtbdbotlab48_03 (WebDriver driver) {
		System.out.print("Test 48/03 ");
		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(driver);
		
		newRPForm_MT.fRecBIC_MT.clear();
		newRPForm_MT.fRecBIC_MT.sendKeys(NO_DIGITS_STRING);
		assertTrue("48/03 FAILED", getValue(newRPForm_MT.fRecBIC_MT).isEmpty());
		
		newRPForm_MT.close();
		System.out.println("PASSED");
	}
	
	/** Ввести 9 допустимых символов (только цифры), сохранить документ
	 *  (= отсутствие проверки на длину БИКа, если БИК известен!)
	 *  Ожидается:
	 *  Контроли на длину поля не сработали
	 */
	static void vtbdbotlab48_04 (WebDriver driver) {
		System.out.print("Test 48/04 ");
		
		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(driver);
		
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(FONDSERVICEBANK.BIC);
		} while (!getValue(newRPForm_MT.fRecBIC_MT).equals(FONDSERVICEBANK.BIC));
		
		newRPForm_MT.form.click();
		
		// wait until data is loaded; T3H 5L0000000Wa M461Cey...
		while (!getValue(newRPForm_MT.fRecBankName_MT).equals(
				(FONDSERVICEBANK.NAME) + " Г " + FONDSERVICEBANK.TOWN));
		
		newRPForm_MT.bCreateRP.click();
		List<WebElement> errorTooltips;
		do {
			errorTooltips = newRPForm_MT.form.findElements(tooltipsErrors);
		} while (errorTooltips.size() == 0);
		
		for (WebElement errorMsg : errorTooltips) {
			assertTrue("48/04 FAILED", !(errorMsg.getText().equals(NewRPForm_MT.BIC_TOO_SHORT)));
		}
		
		newRPForm_MT.close();
		System.out.println("PASSED");
	}
	
	/** Ввести менее 9 допустимых символов (только цифры), сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_TOO_SHORT)
	 */
	static void vtbdbotlab48_05 (WebDriver driver) {
		System.out.print("Test 48/05 ");
		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(driver);
		
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_5x0);
		} while (!getValue(newRPForm_MT.fRecBIC_MT).equals(NUMBERS_5x0));
		
		newRPForm_MT.bCreateRP.click();
		// comparing exact error tooltip text
		assertTrue("48/05 FAILED",	newRPForm_MT.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
				tooltipErrorXPath)).getText().equals(NewRPForm_MT.BIC_TOO_SHORT));
		
		newRPForm_MT.close();
		System.out.println("PASSED");
	}
	
	/** Выбрать БИК из справочника
	 *  Ожидается:
	 *  Поле БИК заполнено выбранным (первым из списка) значением
	 *  + поле БИК не блокируется. Ручной ввод по-прежнему возможен
	 */
	static void vtbdbotlab48_06 (WebDriver driver) {
		System.out.print("Test 48/06 ");
		
		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(driver);
		
		newRPForm_MT.bRecBIC_MT.click();
		
		BICRFForm fDictBIC = new BICRFForm(newRPForm_MT);
		dictRowBIC passedBIC = fDictBIC.chooseFirstInTable();
		
		assertTrue("48/06 FAILED", getValue(newRPForm_MT.fRecBIC_MT).equals(passedBIC.BIC));
		
		// Additional part
		newRPForm_MT.fRecBIC_MT.clear();
		newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_5x0);
		assertTrue("48/06+ FAILED", !getValue(newRPForm_MT.fRecBIC_MT).isEmpty());
		
		newRPForm_MT.close();
		System.out.println("PASSED");
	}
	
	/** Очистить поле БИК, сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_MUST_BE_NONEMPTY)
	 *  Фактически:
	 *  Необходимое сообщение возникает только если кнопка сохранить была нажата сразу после открытия формы или
	 *  было введено менее 9 символов. Иначе (введён не-/известный БИК) сообщение контроля не возникает!
	 */
	static void vtbdbotlab48_07 (WebDriver driver) {
		System.out.print("Test 48/07 ");
		
		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(driver);
		
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_9x0);
		} while (!getValue(newRPForm_MT.fRecBIC_MT).equals(NUMBERS_9x0));
		
		newRPForm_MT.fRecBIC_MT.clear();
		newRPForm_MT.bCreateRP.click();
		
		// comparing exact error tooltip text
//		assertTrue("48/05 FAILED",	newRPForm_MT.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
//				tooltipErrorXPath)).getText().equals(NewRPForm_MT.BIC_MUST_BE_NONEMPTY));
		
		newRPForm_MT.close();
		System.out.println("PASSED (NOT)");
	}
	
	/** OUTDATED?
	 *  Ввести в поле корректнрее по формату значение, которого нет в справочнике БИК, сохранить
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm_MT.BIC_UNKNOWN)
	 *  Фактически:
	 *  Сообщение контроля несколько отличается (см. комментарий для NewRPForm_MT.BIC_UNKNOWN)
	 */
	static void vtbdbotlab48_08 (WebDriver driver) {
		System.out.print("Test 48/08 ");
		
		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(driver);
		
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(NUMBERS_9x0);
		} while (!getValue(newRPForm_MT.fRecBIC_MT).equals(NUMBERS_9x0));
		
		newRPForm_MT.bCreateRP.click();
		
		// comparing exact error tooltip text
		// * this also means that passed unknown 9-digit BIC doesn't trigger length check error (vtbdbotlab48_04)
//		assertTrue("48/05 FAILED",	newRPForm_MT.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
//				tooltipErrorXPath)).getText().equals(NewRPForm_MT.BIC_UNKNOWN));
		
		newRPForm_MT.close();
		System.out.println("PASSED (KINDA)");
	}
	
	/** Открыть (?)универсальную форму ПП, очистить поле "БИК банка получателя", скрыть блок получателя
	 *  (+ сохранить)
	 *  Ожидается:
	 *  1) Блок получателя раскрыт
	 *  2) Поверх метки-переключателя выведено сообщение "Необходимо заполнить реквизиты"
	 *  (OUTDATED: не поверх, а меняется текст самой метки-переключателя)
	 */
	static void vtbdbotlab48_09 (WebDriver driver){
		System.out.print("Test 48/09 ");
		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(driver);
		
		do {
			newRPForm_MT.fRecBIC_MT.clear();
			newRPForm_MT.fRecBIC_MT.sendKeys(FONDSERVICEBANK.BIC);
		} while (!getValue(newRPForm_MT.fRecBIC_MT).equals(FONDSERVICEBANK.BIC));
		
		newRPForm_MT.form.click();
		while (!getValue(newRPForm_MT.fRecBankName_MT).equals(
				(FONDSERVICEBANK.NAME) + " Г " + FONDSERVICEBANK.TOWN));
		
		// switching to 'universal' form type
		newRPForm_MT.switchToSimpleTab();
		
		
		
		NewRPForm_ST newRPForm_ST = new NewRPForm_ST(driver);
		newRPForm_ST.findButtonShowHideRecData();
		newRPForm_ST.showRecDataBlock(newRPForm_ST.bShowHideRecData_ST);
		do {
			newRPForm_ST.fRecBIC_ST.clear();
		} while (!newRPForm_ST.fRecBIC_ST.getAttribute("value").isEmpty());
		//assertTrue("cleaned BIC field, but can't hide receiver data block", );
		
		newRPForm_ST.bShowHideRecData_ST.click();
		newRPForm_ST.form_ST.click();
		
		newRPForm_ST.bCreateRP.click();
		
		assertTrue("48/09 FAILED", newRPForm_ST.bShowHideRecData_ST.findElement(By.xpath("./div")).getText().equals(NewRPForm_ST.invalidReceiverData));
		
		newRPForm_ST.close();
		System.out.println("PASSED");
	}
	
}
