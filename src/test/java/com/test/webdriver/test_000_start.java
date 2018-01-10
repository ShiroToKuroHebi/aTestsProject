package com.test.webdriver;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class test_000_start {

    private WebElement formNewRP;   // form: Creating RP
    private WebElement fBIC;        // field: BIC
	private WebElement bSaveRP;     // button: Save on creating RP form
    private WebElement temp;        // anything, actually. Specified when used
    
    // 'cuz .getText() doesn't work the way I want :p
    private String getValue (WebElement elem) {
    	return elem.getAttribute("value");
    }
	
	/** Проверить поле "БИК банка получателя"
	 *  Ожидается:
	 *  Поле пустое
	 *  + Используется fBIC
	 */
	void vtbdbolab48_01() {
		fBIC = formNewRP.findElement(By.xpath("//div[@title='БИК']/div[2]/div[1]/input"));
		System.out.print("Test 48/01 ");
        assertTrue("48/01 FAILED", getValue(fBIC).isEmpty());
        System.out.println("PASSED");
    }
	
	/** Заполнить "вручную" поле "БИК банка получателя"
	 *  Ожидается:
	 *  Редактирование поля (вручную) возможно
	 */
	void vtbdbolab48_02() {
		System.out.print("Test 48/02 ");
		// that way it works always - even when not in Debug
		do {
			fBIC.clear();
			fBIC.sendKeys(Data.NUMBERS_5x0);
		} while (!getValue(fBIC).equals(Data.NUMBERS_5x0));
		assertTrue("48/02 FAILED", getValue(fBIC).equals(Data.NUMBERS_5x0));
		System.out.println("PASSED");
	}
	
	/** Заполнить поле "БИК банка получателя" любыми символами, кроме цифр
	 *  Ожидается:
	 *  Ввод невозможен (= в поле не сохраняются передаваемые символы)
	 *  // MAY or MAY NOT work. Workaround as in vtbdbolab48_02 won't help
	 */
	void vtbdbolab48_03() {
		System.out.print("Test 48/03 ");
		fBIC.clear();
		fBIC.sendKeys(Data.NO_DIGITS_STRING);
		assertTrue("48/03 FAILED", getValue(fBIC).isEmpty());
		System.out.println("PASSED");
	}
	
	/** Ввести 9 допустимых символов (только цифры) + сохранить документ
	 *  (= _отсутствие_ проверки на длину БИКа)
	 *  Ожидается:
	 *  1) Контроли на длину поля не сработали (= ?..)
	 *  2) Сработал контроль на несуществующий БИК
	 *  + Используется bSaveRP
	 *  * Используется temp
	 */
	void vtbdbolab48_04() {
		bSaveRP = formNewRP.findElement(By.xpath("//div[@class=\"menuActions__group\"][2]/div[1]/button"));

		// PART 1: valid BIC
		System.out.print("Test 48/04_1 (stub) ");
		fBIC.clear();
		fBIC.sendKeys(Data.FONSERVICEBANK_BAIKONUR_BIC);
		
		List<WebElement> errorTooltips = formNewRP.findElements(By.xpath("//div[@class=\"field__tooltip field_tooltip_error\"]"));
		
		// WiP here

		System.out.println();//"PASSED");

		
		
		// PART 2: invalid/unknown BIC; might be the VTBDBOLAB48_08
		System.out.print("Test 48/04_2 ");
		do {
			fBIC.clear();
			fBIC.sendKeys(Data.NUMBERS_9x0);
		} while (!getValue(fBIC).equals(Data.NUMBERS_9x0));
		
		bSaveRP.click();
		// error tooltip: get message text
		temp = formNewRP.findElement(By.xpath("//div[@title=\"БИК\"]/div[2]/div[3]"));
		assertTrue("48/04_2 FAILED", temp.getText().equals(Data.BIC_UNKNOWN));
		
		System.out.println("PASSED");
	}
	
	@Test
    public void makeItHappen() {
    
		LogInPage start = new LogInPage("http://stand.vtb.jtcc.ru:16006/", "VTB DBO front");
		start.logIn();
		formNewRP = start.newRP(); // not sure if it should be in LogInPage class...



        vtbdbolab48_01();
		vtbdbolab48_02();
        vtbdbolab48_03();
        vtbdbolab48_04();
        
        
        //chrome.quit();
    }
}
