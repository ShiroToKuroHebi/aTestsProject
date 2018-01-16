package com.test.webdriver;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class test_000_start implements Data{
    private WebDriver chrome = new ChromeDriver();
    
    private void testBICField(MainPage main) {
	    main.openFormCreateNewRP();
	    BICFieldTests.vtbdbotlab48_01(chrome);
	
	    main.openFormCreateNewRP();
	    BICFieldTests.vtbdbotlab48_02(chrome);
	
	    main.openFormCreateNewRP();
	    BICFieldTests.vtbdbotlab48_03(chrome);
	
	    main.openFormCreateNewRP();
	    BICFieldTests.vtbdbotlab48_04(chrome);
	
	    main.openFormCreateNewRP();
	    BICFieldTests.vtbdbotlab48_05(chrome);
	
	    main.openFormCreateNewRP();
	    BICFieldTests.vtbdbotlab48_06(chrome);
	
	    main.openFormCreateNewRP();
	    BICFieldTests.vtbdbotlab48_07(chrome);
	
	    main.openFormCreateNewRP();
	    BICFieldTests.vtbdbotlab48_08(chrome);
	
	    main.openFormCreateNewRP();
	    BICFieldTests.vtbdbotlab48_09(chrome);
    }
    
    private void testMsg4BankField(MainPage main) {
    
    }
	
	@Test
    public void makeItHappen() {
    	chrome.get(standURL);
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		LogInPage start = new LogInPage(chrome);
		start.clickLoginButton();
		
		MainPage main = new MainPage(chrome);
		
		testBICField(main);
  
        testMsg4BankField(main);
        
//      chrome.quit();
    }
}
