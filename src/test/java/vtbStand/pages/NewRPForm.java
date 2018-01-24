package vtbStand.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewRPForm extends Page {
	
	@FindBy(xpath = "//button[contains(@class,'CloseBtn')]")
	@CacheLookup
			WebElement buttonCloseForm;
	
	@FindBy(xpath = "//i[contains(@class,'groupColumn')]/ancestor::button")
	@CacheLookup
			WebElement buttonMainTabForm;
	
	@FindBy(xpath = "//i[contains(@class,'groupList')]/ancestor::button")
	@CacheLookup
			WebElement buttonSimpleTabForm;
	
	
	//Поменяй локаторы, елси эти элеемнты видны на странице, почему не вынес в поля класса, с FindBy
	// Page-specific locators
	final static By formNewRPRoot = By.xpath("//form");
	final static By formNewRPMainTab = By.xpath("//div[@class=\"PayDocRu__tabContent--muDt2 PayDocRu__mainTab--3jpGE\"]");
	final static By formNewRPSimpleTab = By.xpath("//div[@class=\"PayDocRu__tabContent--muDt2 PayDocRu__simpleTab--1ZXGr\"]");
	
	final static By buttonCreateRP = By.xpath(".//div[@class=\"menuActions__group\"][2]/div[1]/button");
	
	// Elements
	public WebElement form;
	public WebElement bCreateRP;
	
	public NewRPForm () {
		//stub
	}
	
	public NewRPForm (WebDriver driver) {
		form = drvr.findElement(formNewRPRoot);
	}
	
	/** Close the form
	 */
	public MainPage closeForm () {
		//buttonCloseForm = drvr.findElement(By.xpath("//button[contains(@class,'CloseBtn')]"));
		buttonCloseForm.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("appframe")));
		return Page.mainPage();
	}
	
	public NewRPForm_MT switchToMainTab() {
		buttonMainTabForm = drvr.findElement(By.xpath("//i[contains(@class,'groupColumn')]/ancestor::button"));
		buttonMainTabForm.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(formNewRPMainTab));
		return Page.newRPForm_MT();
	}
	
	public NewRPForm_ST switchToSimpleTab() {
		buttonSimpleTabForm = drvr.findElement(By.xpath("//i[contains(@class,'groupList')]/ancestor::button"));
		buttonSimpleTabForm.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(formNewRPSimpleTab));
		return Page.newRPForm_ST();
	}
}
