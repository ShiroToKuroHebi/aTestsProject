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
    
    // 'cuz .getText() doesn't work
    public String getValue (WebElement elem) {
    	return elem.getAttribute("value");
    }

    public void goToStand() {
        chrome.get("http://stand.vtb.jtcc.ru:16006/");
	    chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("VTB DBO front", chrome.getTitle());
        System.out.println("- Got to VTB stand.");
    }

    public void logIn() {
        WebElement loginField = chrome.findElement(By.xpath("//input[@placeholder='Логин']"));
        WebElement passwordField = chrome.findElement(By.xpath("//input[@type='password']"));
        WebElement buttonLogIn = chrome.findElement(By.xpath("//button[text()='Войти']"));
	    
// autofill login fields? wtf? Even do+while loop can't help with that all the time!
//        do {
//        	loginField.clear();
//        } while (!loginField.getText().isEmpty());
//        System.out.println("- Got out of loop. Login field must be empty now.");
//        loginField.sendKeys(Data.CLIENT_LOGIN);
//        passwordField.clear();
//        passwordField.sendKeys(Data.CLIENT_PSWRD);
        buttonLogIn.click();
	    System.out.println("- Pressed button.");
    }

    public void createRP() {
        WebElement buttonCreate = chrome.findElement(By.xpath(".//*[text()='Создать ПП']/.."));

        buttonCreate.click();
	    System.out.println("- Pressed 'Create RP'.");
	
	    formRP = chrome.findElement(By.xpath("//*[@id=\"appframe\"]/form"));
	    System.out.println("- formRP received.");
	    
	    WebElement tmp = formRP.findElement(By.xpath("//div[@title=\"Расчетный счет плательщика\"]/div[2]/div[1]/input"));
	    // while text is number field is empty
	    while (getValue(tmp).isEmpty()) {
//		    System.out.println("'" + tmp.getText() + "'");
//		    System.out.println("'" + tmp.getAttribute("value") + "'");
//		    formRP = chrome.findElement(By.xpath("//*[@id=\"appframe\"]/form"));
//		    tmp = formRP.findElement(By.xpath("//div[@title=\"Номер\"]/div[2]/input"));
	    }
	    System.out.println("- while-loop is over; tmp value: " + getValue(tmp));
	    
//	    WebDriverWait timeout = new WebDriverWait(chrome, 10);
//	    timeout.until(ExpectedConditions.textMatches(
//                          By.xpath("//div[@title=\"Расчетный счет плательщика\"]/div[2]/input"),
//                          Pattern.compile("^.+\\.")));
	    
	    bicField = formRP.findElement(By.xpath("//div[@title='БИК']/div[2]/div[1]/input"));
        System.out.println("- Found bicField.");
    }
	
	/** Проверить поле "БИК банка получателя"
	 *  Ожидается:
	 *  Поле пустое
	 */
	public void vtbdbolab48_01() {
        //bicField = chrome.findElement(By.xpath("//div[@title='БИК']/div[2]/div[1]/input"));
        
        System.out.print("Test 48/01 ");
        assertTrue("48/01 FAILED", getValue(bicField).isEmpty());
        System.out.println("PASSED");
    }
	
	/** Заполнить "вручную" поле "БИК банка получателя"
	 *  Ожидается:
	 *  Редактирование поля (вручную) возможно
	 */
	public void vtbdbolab48_02() {
		//bicField = chrome.findElement(By.xpath("//div[@title='БИК']/div[2]/div[1]/input"));
		
		System.out.print("Test 48/02 ");
		
		bicField.click();
		bicField.sendKeys(Data.NUMBERS_5x0);
		
		//assertEquals("48/02 FAILED", "04000", bicField.getText());  // Y U NO work3N?!
		//assertTrue("48/02 FAILED", bicField.getText().equals("04000")); // u2 btch?
		assertTrue("48/02 FAILED", getValue(bicField).equals(Data.NUMBERS_5x0));
		System.out.println("PASSED");
	}
	
	/** Заполнить поле "БИК банка получателя" любыми символами, кроме цифр
	 *  Ожидается:
	 *  Ввод невозможен (= в поле не сохраняются передаваемые символы)
	 */
	public void vtbdbolab48_03() {
		//bicField = chrome.findElement()
		
		System.out.print("Test 48/03 ");
		
		bicField.clear();
		bicField.sendKeys(Data.NO_DIGITS_STRING);
		//assertTrue("48/03", bicField.getText().isEmpty());
		assertTrue("48/03 FAILED", getValue(bicField).isEmpty());
		System.out.println("PASSED");
	}
	
	@Test
    public void makeItHappen() {
        goToStand();
        logIn();
        
        
        
        createRP();
        vtbdbolab48_01();
		vtbdbolab48_02();
        vtbdbolab48_03();
        
        
        //chrome.quit();
    }
}
