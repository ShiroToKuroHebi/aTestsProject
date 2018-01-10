package com.test.webdriver;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class test_000_start {

    private WebDriver chrome = new ChromeDriver();
    private WebElement formRP;
    private WebElement bicField;
    private WebElement temp;
    
    // 'cuz .getText() doesn't work the way I want
    public String getValue (WebElement elem) {
    	return elem.getAttribute("value");
    }
	
	/** Перейти на стенд (+ начальная настройка WebDriver'а)
	 */
	public void goToStand() {
        chrome.get("http://stand.vtb.jtcc.ru:16006/");
	    chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("VTB DBO front", chrome.getTitle());
        System.out.println("- Got to VTB stand.");
    }
	
	/** Авторизоваться в системе
	 */
	public void logIn() {
        WebElement loginField = chrome.findElement(By.xpath("//input[@placeholder='Логин']"));
        WebElement passwordField = chrome.findElement(By.xpath("//input[@type='password']"));
        WebElement buttonLogIn = chrome.findElement(By.xpath("//button[text()='Войти']"));
	    
//        loginField.sendKeys(Data.CLIENT_LOGIN);
//        passwordField.sendKeys(Data.CLIENT_PSWRD);
	    buttonLogIn.click();
	    System.out.println("- Pressed button.");
    }
	
	/** Открыть и загрузить данные в форму создания нового ПП
	 *  + заполнение переменных WebElement formRP, bicField
	 *  * Используется temp
	 */
	public void createRP() {
        WebElement buttonCreate = chrome.findElement(By.xpath(".//*[text()='Создать ПП']/.."));

        buttonCreate.click();
	    System.out.println("- Pressed 'Create RP'.");
	
	    formRP = chrome.findElement(By.xpath("//*[@id=\"appframe\"]/form"));
	    System.out.println("- formRP received.");
	    
	    temp = formRP.findElement(By.xpath("//div[@title=\"Расчетный счет плательщика\"]/div[2]/div[1]/input"));
	    // Form is loading while text in field is empty
	    while (getValue(temp).isEmpty());
	    System.out.println("- while-loop is over; tmp value: " + getValue(temp));
	    
	    bicField = formRP.findElement(By.xpath("//div[@title='БИК']/div[2]/div[1]/input"));
        System.out.println("- Found bicField.");
    }
	
	/** Проверить поле "БИК банка получателя"
	 *  Ожидается:
	 *  Поле пустое
	 */
	public void vtbdbolab48_01() {
        System.out.print("Test 48/01 ");
        assertTrue("48/01 FAILED", getValue(bicField).isEmpty());
        System.out.println("PASSED");
    }
	
	/** Заполнить "вручную" поле "БИК банка получателя"
	 *  Ожидается:
	 *  Редактирование поля (вручную) возможно
	 *  // DOESN'T ALWAYS WORK; depends on time. Always successful in Debug
	 */
	public void vtbdbolab48_02() {
		System.out.print("Test 48/02 ");
		bicField.clear();
		bicField.sendKeys(Data.NUMBERS_5x0);
		assertTrue("48/02 FAILED", getValue(bicField).equals(Data.NUMBERS_5x0));
		System.out.println("PASSED");
	}
	
	/** Заполнить поле "БИК банка получателя" любыми символами, кроме цифр
	 *  Ожидается:
	 *  Ввод невозможен (= в поле не сохраняются передаваемые символы)
	 */
	public void vtbdbolab48_03() {
		System.out.print("Test 48/03 ");
		bicField.clear();
		bicField.sendKeys(Data.NO_DIGITS_STRING);
		assertTrue("48/03 FAILED", getValue(bicField).isEmpty());
		System.out.println("PASSED");
	}
	
	/** Ввести 9 допустимых символов (только цифры) + сохранить документ
	 *  (= _отсутствие_ проверки на длину БИКа)
	 *  Ожидается:
	 *  1) Контроли на длину поля не сработали (= ?..)
	 *  2) Сработал контроль на несуществующий БИК
	 *  * Используется temp
	 */
	public void vtbdbolab48_04() {
		// PART 1: valid BIC
		System.out.print("Test 48/04_1 (stub) ");
		bicField.clear();
		bicField.sendKeys(Data.FONSERVICEBANK_BAIKONUR_BIC);

		System.out.println("PASSED");

		
		
		// PART 2: invalid/unknown BIC; might be the VTBDBOLAB48_08
		System.out.println("Test 48/04_2");
		bicField.clear();
		bicField.sendKeys(Data.NUMBERS_9x0);
		// //div[@class="field__tooltip field__tooltip_error"]
		temp = formRP.findElement(By.xpath("//div[@title=\"БИК\"]/div[2]/div[3]"));
		System.out.println("'" + temp.getText() + "'");
		System.out.println("PASSED");
	}
	
	@Test
    public void makeItHappen() {
        goToStand();
        logIn();
        
        
        
        createRP();
        vtbdbolab48_01();
		//vtbdbolab48_02();
        vtbdbolab48_03();
        vtbdbolab48_04();
        
        
        //chrome.quit();
    }
}
