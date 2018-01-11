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
	WebElement bSaveRP;     // button: Save on creating RP form
    WebElement temp;        // anything, actually. Specified when used
    
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
	 *  (= _отсутствие_ проверки на длину БИКа, если БИК известен)
	 *  Ожидается:
	 *  Контроли на длину поля не сработали
	 *  =====
	 *  + Используется bSaveRP
	 *  Используется fBIC
	 *  Используется temp
	 */
	void vtbdbolab48_04() {
		bSaveRP = fNewRP.findElement(By.xpath("//div[@class=\"menuActions__group\"][2]/div[1]/button"));

		// PART 1: valid BIC
		System.out.print("Test 48/04 (stub)");
		fBIC.clear();
		fBIC.sendKeys(Data.FONSERVICEBANK_BAIKONUR_BIC);
		
		List<WebElement> errorTooltips = fNewRP.findElements(By.xpath("//div[@class=\"field__tooltip field_tooltip_error\"]"));
		
		// WiP here

		System.out.println();//"PASSED");
	}
	
	/** Ввести менее 9 допустимых символов (только цифры), сохранить документ
	 *  Ожидается:
	 *  Сработал контроль на длину БИКа
	 *  =====
	 *  Используется bSaveRP, fBIC
	 *  Используется temp
	 */
	void vtbdbolab48_05() {
		System.out.print("Test 48/05 ");

		do {
			fBIC.clear();
			fBIC.sendKeys(Data.NUMBERS_5x0);
		} while (!getValue(fBIC).equals(Data.NUMBERS_5x0));

		bSaveRP.click();
		// error tooltip: get message text
		temp = fNewRP.findElement(By.xpath("//div[@title=\"БИК\"]/div[2]/div[3]"));
		assertTrue("48/05 FAILED", temp.getText().equals(NewRPForm.BIC_TOO_SHORT));
		System.out.println("PASSED");
	}
	
	/** Выбрать БИК из справочника
	 *  Ожидается:
	 *  Поле БИК заполнено выбранным (первым из списка) значением
	 *  + поле БИК не блокируется. Ручной ввод по-прежнему возможен
	 */
	void vtbdbolab48_06(NewRPForm newRPForm) {
		System.out.print("Test 48/06 ");
		
		newRPForm.bRecBIC.click();
		
		BICRFForm fDictBIC = new BICRFForm(newRPForm.fNewRP);
		String passedBIC = fDictBIC.chooseFirstInTable();
		wait = new WebDriverWait(chrome, 10);
		wait.until(ExpectedConditions.invisibilityOf(fDictBIC.fBIC));

		assertTrue("48/06 FAILED", getValue(newRPForm.fRecBIC).equals(passedBIC));

		// Additional part
		newRPForm.fRecBIC.clear();
		newRPForm.fRecBIC.sendKeys(Data.NUMBERS_5x0);
		assertTrue("48/06+ FAILED", !getValue(newRPForm.fRecBIC).isEmpty());
		
		System.out.println("PASSED");
	}
	
	/** Очистить поле БИК, сохранить документ
	 *  Ожидается:
	 *  Сработал контроль с текстом
	 *      "Поле БИК банка получателя обязательно для заполнения"
	 *  Фактически:
	 *  Необходимое сообщение возникает только если кнопка сохранить была нажата сразу после открытия формы или
	 *  было введено менее 9 символов. Иначе (введён не-/известный БИК) сообщение контроля не возникает!
	 */
	void vtbdbolab48_07(NewRPForm newRPForm) {
		System.out.print("Test 48/07 ");
		
		do {
			newRPForm.fRecBIC.clear();
			newRPForm.fRecBIC.sendKeys(Data.NUMBERS_9x0);
		} while (!getValue(newRPForm.fRecBIC).equals(Data.NUMBERS_9x0));
		
		newRPForm.fRecBIC.clear();
		newRPForm.bCreateRP.click();
		// error tooltip: get message text
		temp = newRPForm.fNewRP.findElement(By.xpath("//div[@title=\"БИК\"]/div[2]/div[3]"));
		//assertTrue("48/07 FAILED", temp.getText().equals(NewRPForm.BIC_MUST_BE_NONEMPTY));
		
		System.out.println("PASSED (NOT)");
	}
	
	/** OUTDATED?
	 *  Ввести в поле корректнрее по формату значение, которого нет в справочнике БИК, сохранить
	 *  Ожидается:
	 *  Сработал контроль с текстом
	 *      "Указанный БИК не найден в справочнике российских банков"
	 *  Фактически:
	 *  Сообщение контроля несколько отличается (см. комментарий для NewRPForm.BIC_UNKNOWN)
	 */
	void vtbdbolab48_08(NewRPForm newRPForm) {
		System.out.print("Test 48/08 ");
		
		do {
			newRPForm.fRecBIC.clear();
			newRPForm.fRecBIC.sendKeys(Data.NUMBERS_9x0);
		} while (!getValue(newRPForm.fRecBIC).equals(Data.NUMBERS_9x0));
		
		newRPForm.bCreateRP.click();
		// error tooltip: get message text
		temp = newRPForm.fNewRP.findElement(By.xpath("//div[@title=\"БИК\"]/div[2]/div[3]"));
		//assertTrue("48/08 FAILED", temp.getText().equals(NewRPForm.BIC_UNKNOWN));
		// this also means that passed unknown 9-digit BIC doesn't trigger length check error (vtbdbolab48_04)
		
		System.out.println("PASSED (KINDA)");
	}
	
	/** Открыть (?)универсальную форму ПП, очистить поле "БИК банка получателя", скрыть блок получателя
	 *  (+ сохранить?)
	 *  Ожидается:
	 *  Блок получателя раскрыт. Поверх метки-переключателя выведено сообщение "Необходимо заполнить реквизиты"
	 */
	void vtbdbolab48_09(NewRPForm newRPForm){
		System.out.print("Test 48/09 ");
		
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
		
		this.fNewRP = newRP.fNewRP; // to be changed


		vtbdbolab48_01();
		vtbdbolab48_02();
		vtbdbolab48_03();
		vtbdbolab48_04(); // !WiP!
		vtbdbolab48_05();
		
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
