package vtbStand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewRPForm extends Page{
	
	@FindBy(xpath = "//button[contains(@class,'CloseBtn')]")
	@CacheLookup
			private WebElement buttonCloseForm;
	
	@FindBy(xpath = "//i[contains(@class,'groupColumn')]/ancestor::button")
	@CacheLookup
			private WebElement buttonMainTabForm;
	
	@FindBy(xpath = "//i[contains(@class,'groupList')]/ancestor::button")
	@CacheLookup
			private WebElement buttonSimpleTabForm;
	
	
	
	// Page-specific locators
	final static By formNewRPRoot = By.xpath("//form");
	final static By formNewRPMainTab = By.xpath("//div[@class=\"PayDocRu__tabContent--muDt2 PayDocRu__mainTab--3jpGE\"]");
	final static By formNewRPSimpleTab = By.xpath("//div[@class=\"PayDocRu__tabContent--muDt2 PayDocRu__simpleTab--1ZXGr\"]");
	
	final static By buttonCreateRP = By.xpath(".//div[@class=\"menuActions__group\"][2]/div[1]/button");
	
	// Elements
	WebElement form;
	WebElement bCreateRP;
	
	public NewRPForm () {
		//stub
	}
	
	public NewRPForm (WebDriver driver) {
		this.drvr = driver;
		this.wait = new WebDriverWait(this.drvr,10);
		
		form = this.drvr.findElement(formNewRPRoot);
	}
	
	/** Close the form
	 */
	public MainPage closeForm () {
		buttonCloseForm = this.drvr.findElement(By.xpath("//button[contains(@class,'CloseBtn')]"));
		buttonCloseForm.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("appframe")));
		return new MainPage(this.drvr);
	}
	
	public NewRPForm_MT switchToMainTab() {
		buttonMainTabForm = this.drvr.findElement(By.xpath("//i[contains(@class,'groupColumn')]/ancestor::button"));
		buttonMainTabForm.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(formNewRPMainTab));
		return new NewRPForm_MT(this.drvr);
	}
	
	public NewRPForm_ST switchToSimpleTab() {
		buttonSimpleTabForm = this.drvr.findElement(By.xpath("//i[contains(@class,'groupList')]/ancestor::button"));
		buttonSimpleTabForm.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(formNewRPSimpleTab));
		return new NewRPForm_ST(this.drvr);
	}
}
