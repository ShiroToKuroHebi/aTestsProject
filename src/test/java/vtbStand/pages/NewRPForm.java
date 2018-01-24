package vtbStand.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewRPForm extends Page {
	
	@FindBy(xpath = "//button[contains(@class,'CloseBtn')]")
	@CacheLookup
			private WebElement buttonCloseForm;
	
	@FindBy(xpath = "//i[contains(@class,'groupColumn')]/ancestor::button")
	@CacheLookup
			private WebElement buttonMainTabForm;
	
	@FindBy(xpath = "//i[contains(@class,'groupList')]/ancestor::button")
	@CacheLookup
			private WebElement buttonSimpleTabForm;
	
	@FindBy(xpath = "//div[@class='menuActions']//div[text()='Сохранить']/ancestor::button")
	@CacheLookup
			private WebElement buttonCreateRP;
	

	
	private By mainTab = By.xpath("//div[contains(@class, 'PayDocRu__mainTab')]");
	
	private By simpleTab = By.xpath("//div[contains(@class, 'PayDocRu__simpleTab')]");
	
	
	
	/** Close the form
	 */
	public MainPage closeForm () {
		buttonCloseForm.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("appframe")));
		return Page.mainPage();
	}
	
	public NewRPForm_MT switchToMainTab() {
		buttonMainTabForm.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(mainTab));
		return Page.newRPForm_MT();
	}
	
	public NewRPForm_ST switchToSimpleTab() {
		buttonSimpleTabForm.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(simpleTab));
		return Page.newRPForm_ST();
	}
	
	public void clickCreateRP() {
		buttonCreateRP.click();
	}
	
}
