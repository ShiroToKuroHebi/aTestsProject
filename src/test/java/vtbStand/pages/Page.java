package vtbStand.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	public static WebDriver drvr;
	public static WebDriverWait wait;
	
	// Errors may appear on any page or form
	public static String tooltipErrorXPath = "//div[@class=\"field__tooltip field__tooltip_error\"]";
	
	public static void initPage(WebDriver driver) {
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
		NewRPForm result = new NewRPForm();
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	
	public static NewRPForm_ST newRPForm_ST() {
		NewRPForm_ST result = new NewRPForm_ST();
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	
	public static NewRPForm_MT newRPForm_MT() {
		NewRPForm_MT result = new NewRPForm_MT();
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	
	public static BICRFForm bicRFForm() {
		BICRFForm result = new BICRFForm();
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	
	public static Msg4BankForm msg4BankForm() {
		Msg4BankForm result = new Msg4BankForm();
		PageFactory.initElements(drvr,result);
		
		return result;
	}
	


	// Fields present on many pages
	/** Pass the %value to %field (number of attempts = 5),
	 *  As a result %field will contain given %value,
	 *  Returns true, if successful, otherwise - false.
	 */
	public static boolean fillFieldWithValidValue (WebElement field, String value) {
		for (int i = 0; i < 5; i++) {
			field.clear();
			field.sendKeys(value);
			if (field.getText().equals(value) || field.getAttribute("value").equals(value)) {
				return true;
			}
		}
		
		return false;
	}
	
}
