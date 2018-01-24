package vtbStand.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	public static WebDriver drvr;
	public static WebDriverWait wait;
	
	// Errors may appear on any page or form
	public static String tooltipErrorXPath = "//div[@class=\"field__tooltip field__tooltip_error\"]";
	public static By tooltipsErrors = By.xpath(Page.tooltipErrorXPath);
	
	public Page(){
		// stub
	}
	
	public Page(WebDriver driver) {
		drvr = driver;
		wait = new WebDriverWait(drvr, 10);
	}
	
	public static LogInPage logInPage() {
		LogInPage result = new LogInPage();
		PageFactory.initElements(drvr, result);
		
		return result;
	}

	public static MainPage mainPage() {
		MainPage result = new MainPage();
		PageFactory.initElements(drvr, result);
		
		return result;
	}
	
	public static NewRPForm newRPForm() {
		NewRPForm result = new NewRPForm(drvr);
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	
	public static NewRPForm_ST newRPForm_ST() {
		NewRPForm_ST result = new NewRPForm_ST(drvr);
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	
	public static NewRPForm_MT newRPForm_MT() {
		NewRPForm_MT result = new NewRPForm_MT(drvr);
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	
	public static BICRFForm bicRFForm() {
		BICRFForm result = new BICRFForm(drvr);
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	
	public static Msg4BankForm msg4BankForm() {
		Msg4BankForm result = new Msg4BankForm(drvr);
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	
}
