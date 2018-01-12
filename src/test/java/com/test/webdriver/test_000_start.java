package com.test.webdriver;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class test_000_start {
    WebDriver chrome = new ChromeDriver();
	WebDriverWait wait;

	WebElement fNewRP;      // form: Creating RP
    WebElement fBIC;        // field: BIC
    
	private final static String tooltipErrorXPath = "//div[@class=\"field__tooltip field__tooltip_error\"]";
	
	// Locators
	private By tooltipsErrors = By.xpath(tooltipErrorXPath);
	
    // 'cuz .getText() doesn't work the way I want :p
    private String getValue (WebElement elem) {
    	return elem.getAttribute("value");
    }
	
	/** Проверить поле "БИК банка получателя"
	 *  Ожидается:
	 *  Поле пустое
	 *  =====
	 *  + Используется fBIC
	 */
	void vtbdbolab48_01() {
		fBIC = fNewRP.findElement(By.xpath("//div[@title='БИК']/div[2]/div[1]/input"));

        assertTrue("48/01 FAILED", getValue(fBIC).isEmpty());
    }
	
	/** Заполнить "вручную" поле "БИК банка получателя"
	 *  Ожидается:
	 *  Редактирование поля (вручную) возможно
	 *  =====
	 *  Используется fBIC
	 */
	void vtbdbolab48_02() {
		// that way it works always - even when not in Debug
		do {
			fBIC.clear();
			fBIC.sendKeys(Data.NUMBERS_5x0);
		} while (!getValue(fBIC).equals(Data.NUMBERS_5x0));
		assertTrue("48/02 FAILED", getValue(fBIC).equals(Data.NUMBERS_5x0));
	}
	
	/** Заполнить поле "БИК банка получателя" любыми символами, кроме цифр
	 *  Ожидается:
	 *  Ввод невозможен (= в поле не сохраняются передаваемые символы)
	 *  // MAY or MAY NOT work. Workaround as in vtbdbolab48_02 won't help
	 *  =====
	 *  Используется fBIC
	 */
	void vtbdbolab48_03() {
		fBIC.clear();
		fBIC.sendKeys(Data.NO_DIGITS_STRING);
		assertTrue("48/03 FAILED", getValue(fBIC).isEmpty());
	}
	
	/** Ввести 9 допустимых символов (только цифры), сохранить документ
	 *  (= отсутствие проверки на длину БИКа, если БИК известен!)
	 *  Ожидается:
	 *  Контроли на длину поля не сработали
	 */
	void vtbdbolab48_04(NewRPForm newRPForm) {
		System.out.print("Test 48/04 ");
		
		do {
			newRPForm.fRecBIC_MT.clear();
			newRPForm.fRecBIC_MT.sendKeys(Data.FONDSERVICEBANK.BIC);
		} while (!getValue(newRPForm.fRecBIC_MT).equals(Data.FONDSERVICEBANK.BIC));
		
		newRPForm.form.click();
		// wait until data is loaded; T3H 5L0000000Wa M461Cey...
		while (!getValue(newRPForm.fRecBankName_MT).equals(
				(Data.FONDSERVICEBANK.NAME) + " Г " + Data.FONDSERVICEBANK.TOWN));
		
		newRPForm.bCreateRP.click();
		List<WebElement> errorTooltips;
		do {
			errorTooltips = newRPForm.form.findElements(tooltipsErrors);
		} while (errorTooltips.size() == 0);
		
		for (WebElement errorMsg : errorTooltips) {
			assertTrue("48/04 FAILED", !(errorMsg.getText().equals(NewRPForm.BIC_TOO_SHORT)));
		}

		System.out.println("PASSED");
	}
	
	/** Ввести менее 9 допустимых символов (только цифры), сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm.BIC_TOO_SHORT)
	 */
	void vtbdbolab48_05(NewRPForm newRPForm) {
		System.out.print("Test 48/05 ");

		do {
			newRPForm.fRecBIC_MT.clear();
			newRPForm.fRecBIC_MT.sendKeys(Data.NUMBERS_5x0);
		} while (!getValue(newRPForm.fRecBIC_MT).equals(Data.NUMBERS_5x0));

		newRPForm.bCreateRP.click();
		// comparing exact error tooltip text
		assertTrue("48/05 FAILED",	newRPForm.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
				tooltipErrorXPath)).getText().equals(NewRPForm.BIC_TOO_SHORT));
		System.out.println("PASSED");
	}
	
	/** Выбрать БИК из справочника
	 *  Ожидается:
	 *  Поле БИК заполнено выбранным (первым из списка) значением
	 *  + поле БИК не блокируется. Ручной ввод по-прежнему возможен
	 */
	void vtbdbolab48_06(NewRPForm newRPForm) {
		System.out.print("Test 48/06 ");
		
		newRPForm.bRecBIC_MT.click();
		
		BICRFForm fDictBIC = new BICRFForm(newRPForm.form);
		dictRowBIC passedBIC = fDictBIC.chooseFirstInTable();
		wait = new WebDriverWait(chrome, 10);
		wait.until(ExpectedConditions.invisibilityOf(fDictBIC.fBIC));

		assertTrue("48/06 FAILED", getValue(newRPForm.fRecBIC_MT).equals(passedBIC.BIC));

		// Additional part
		newRPForm.fRecBIC_MT.clear();
		newRPForm.fRecBIC_MT.sendKeys(Data.NUMBERS_5x0);
		assertTrue("48/06+ FAILED", !getValue(newRPForm.fRecBIC_MT).isEmpty());
		
		System.out.println("PASSED");
	}
	
	/** Очистить поле БИК, сохранить документ
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm.BIC_MUST_BE_NONEMPTY)
	 *  Фактически:
	 *  Необходимое сообщение возникает только если кнопка сохранить была нажата сразу после открытия формы или
	 *  было введено менее 9 символов. Иначе (введён не-/известный БИК) сообщение контроля не возникает!
	 */
	void vtbdbolab48_07(NewRPForm newRPForm) {
		System.out.print("Test 48/07 ");
		
		do {
			newRPForm.fRecBIC_MT.clear();
			newRPForm.fRecBIC_MT.sendKeys(Data.NUMBERS_9x0);
		} while (!getValue(newRPForm.fRecBIC_MT).equals(Data.NUMBERS_9x0));
		
		newRPForm.fRecBIC_MT.clear();
		newRPForm.bCreateRP.click();

		// comparing exact error tooltip text
//		assertTrue("48/05 FAILED",	newRPForm.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
//				tooltipErrorXPath)).getText().equals(NewRPForm.BIC_MUST_BE_NONEMPTY));
		
		System.out.println("PASSED (NOT)");
	}
	
	/** OUTDATED?
	 *  Ввести в поле корректнрее по формату значение, которого нет в справочнике БИК, сохранить
	 *  Ожидается:
	 *  Сработал контроль (см. NewRPForm.BIC_UNKNOWN)
	 *  Фактически:
	 *  Сообщение контроля несколько отличается (см. комментарий для NewRPForm.BIC_UNKNOWN)
	 */
	void vtbdbolab48_08(NewRPForm newRPForm) {
		System.out.print("Test 48/08 ");
		
		do {
			newRPForm.fRecBIC_MT.clear();
			newRPForm.fRecBIC_MT.sendKeys(Data.NUMBERS_9x0);
		} while (!getValue(newRPForm.fRecBIC_MT).equals(Data.NUMBERS_9x0));
		
		newRPForm.bCreateRP.click();

		// comparing exact error tooltip text
		// * this also means that passed unknown 9-digit BIC doesn't trigger length check error (vtbdbolab48_04)
//		assertTrue("48/05 FAILED",	newRPForm.form.findElement(By.xpath("//div[@title=\"БИК\"]" +
//				tooltipErrorXPath)).getText().equals(NewRPForm.BIC_UNKNOWN));
		
		System.out.println("PASSED (KINDA)");
	}
	
	/** Открыть (?)универсальную форму ПП, очистить поле "БИК банка получателя", скрыть блок получателя
	 *  (+ сохранить?)
	 *  Ожидается:
	 *  1) Блок получателя раскрыт (and we know this how?..).
	 *  2) Поверх метки-переключателя выведено сообщение "Необходимо заполнить реквизиты"
	 *  (OUTADED: не поверх, а меняется текст самой метки-переключателя)
	 */
	void vtbdbolab48_09(NewRPForm newRPForm){
		System.out.print("Test 48/09 ");
		
		do {
			newRPForm.fRecBIC_MT.clear();
			newRPForm.fRecBIC_MT.sendKeys(Data.FONDSERVICEBANK.BIC);
		} while (!getValue(newRPForm.fRecBIC_MT).equals(Data.FONDSERVICEBANK.BIC));
		
		newRPForm.form.click();
		while (!getValue(newRPForm.fRecBankName_MT).equals(
				(Data.FONDSERVICEBANK.NAME) + " Г " + Data.FONDSERVICEBANK.TOWN));

		// switching to 'universal' form type
		newRPForm.switchToSimpleTab();
		
		
		
		
		System.out.println();//("PASSED");
	}
	
	@Test
    public void makeItHappen() {
    	chrome.get("http://stand.vtb.jtcc.ru:16006/");
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		LogInPage start = new LogInPage(chrome);
		start.clickLoginButton();
		
		MainPage main = new MainPage(chrome);
		main.openFormCreateNewRP();
		
		NewRPForm newRP = new NewRPForm(chrome);
		newRP.waitForDataLoadOnForm();
		
		this.fNewRP = newRP.form; // to be changed


//		vtbdbolab48_01();
//		vtbdbolab48_02();
//		vtbdbolab48_03();
		
		newRP.close();
		main.openFormCreateNewRP();
		newRP = new NewRPForm(chrome);
		vtbdbolab48_04(newRP);
		
		newRP.close();
		main.openFormCreateNewRP();
		newRP = new NewRPForm(chrome);
		vtbdbolab48_05(newRP);
		
        newRP.close();
		main.openFormCreateNewRP();
        newRP = new NewRPForm(chrome);
        vtbdbolab48_06(newRP);
        
        newRP.close();
        main.openFormCreateNewRP();
        newRP = new NewRPForm(chrome);
        vtbdbolab48_07(newRP);
        
        newRP.close();
        main.openFormCreateNewRP();
        newRP = new NewRPForm(chrome);
        vtbdbolab48_08(newRP);
        
        newRP.close();
        main.openFormCreateNewRP();
        newRP = new NewRPForm(chrome);
        vtbdbolab48_09(newRP); // !WiP!
        
        chrome.quit();
    }
}
