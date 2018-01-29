package vtbStand.tests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import vtbStand.PropertyValues;
import vtbStand.pages.Page;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Tests {
	static WebDriver chrome;
	private static PropertyValues settings = new PropertyValues();
	static Logger logger = Logger.getLogger(Tests.class);

	@Before
	public void setUp() throws IOException {
		logger.info("STARTED");

		chrome = new ChromeDriver();
		chrome.get(settings.getStandURL());
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Page.initPage(chrome);
	}
		
	@After
	public void tearDown() {
		logger.info("PASSED");
		chrome.quit();
	}
}
