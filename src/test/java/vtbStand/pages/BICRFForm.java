package vtbStand.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import vtbStand.DictRowBIC;

import java.util.List;

/** DICTIONARY!!!
 *  This is 'BIC RF Form'
 *  Actually, this one is not a 'form' but an 'overlap'.
 */
public class BICRFForm extends Page {
	private final static String HEADER = "Российские банки (БИК РФ)";
	
	@FindBy(xpath = "//div[text()='" + HEADER + "']/ancestor::div[contains(@class,'Overlap__base')]")
	@CacheLookup
			private WebElement form;
	
	@FindBy(xpath = "//div[contains(@class,'OverlapFooter')]//div[text()='Применить']/ancestor::button")
	@CacheLookup
			private WebElement buttonApply;
	
	By tableRows = By.xpath("//div[contains(@class,'table__row')]");
	


	/** Closes the overlay because 'apply()' is used
	 *  TODO More work to do?
	 */
	public DictRowBIC chooseFirstInTable() {
		WebElement row = getTableRows().get(0);

		DictRowBIC result = new DictRowBIC(row);
		
		row.click();

		apply();

		return result;
	}
	
	/** Gets presented number of rows in table (20 by default?)
	 */
	public List<WebElement> getTableRows() {
		return form.findElements(tableRows);
	}
	
	/** Clicks 'Apply', thus:
	 *  passing chosen table row from dictionary to parent form
	 *  AND
	 *  closing the overlay
	 */
	// Doesn't return NewPRForm/_MT/_ST. Not sure if it should.
	public void apply () {
		buttonApply.click();
		wait.until(ExpectedConditions.invisibilityOf(form));
	}
	
	public WebElement getForm() {	return form;	}
	
}
