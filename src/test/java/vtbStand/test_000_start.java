package vtbStand;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class test_000_start implements Data {
	private WebDriver chrome = new ChromeDriver();
    private static Logger logger = Logger.getLogger(test_000_start.class);
    private PropertyValues settings = new PropertyValues();
    
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
    
    private void testMsg4BankField(NewRPForm_MT newRPForm_MT) {
	    newRPForm_MT.showMsg4BankBlock();
	    newRPForm_MT.bMsg4B_MT.click();
	    
	    Msg4BankFieldTests.vtbdbotlab5357_01(chrome);
	
	    Msg4BankFieldTests.vtbdbotlab5357_02(chrome);
	
	    Msg4BankFieldTests.vtbdbotlab5357_03(chrome);
	
	    Msg4BankFieldTests.vtbdbotlab5357_04(chrome);
	
	    Msg4BankFieldTests.vtbdbotlab5357_05(chrome);
	
	    Msg4BankFieldTests.vtbdbotlab5357_06(chrome);
	
	    Msg4BankFieldTests.vtbdbotlab5357_07(chrome);
	
	    Msg4BankFieldTests.vtbdbotlab5357_08(chrome);
    }
	
	@Test
    public void makeItHappen() throws IOException {
		logger.info("-==| START |==-");
		
    	chrome.get(settings.getStandURL());
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LogInPage start = new LogInPage(chrome);
		start.clickLoginButton();

		MainPage main = new MainPage(chrome);
		testBICField(main);

//		main.openFormCreateNewRP();
//		NewRPForm_MT newRPForm_MT = new NewRPForm_MT(chrome);
//      testMsg4BankField(newRPForm_MT);
		
		logger.info("-==|  END  |==-");
		chrome.quit();
    }
}
