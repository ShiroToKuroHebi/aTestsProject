package vtbStand.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import vtbStand.DictRowBIC;

/** DICTIONARY!!!
 *  This is 'BIC RF Form'
 *  Actually, this one is not a 'form' but an 'overlap'.
 */
public class BICRFForm extends Page{
	
	private final static String fHeader = "Российские банки (БИК РФ)";
	
	//By formBIC = By.xpath("//div[text()=\""+fHeader+"\"]/ancestor::?..");

	//Поменяй локатор
	private final static By formBIC = By.xpath("//div[text()=\"" + fHeader + "\"]/../../.."); //3rd parent from header - the form we need

	//Лучше найти все строки таблицы, потом уже выбирать перую из List

	private final static By elementFirstRowInTable = By.xpath(".//div[@class=\"table__body\"]/div[1]");

	//Это плохой локатор, используй contains в xpath к примеру
	private final static By buttonApply = By.xpath(".//button[@class=\"Button__base--3ZP3W Button__basePrimary--3ryz2\"]");
	
	private WebElement fBIC;
	
	/** When called from creating new RP form (MT/ST)
	 */
	public BICRFForm(WebDriver driver) {
		fBIC = drvr.findElement(formBIC);
	}
	
	/** Closes the overlay when 'Apply' is pressed
	 */
	public DictRowBIC chooseFirstInTable(){
		DictRowBIC result;
		WebElement row = fBIC.findElement(elementFirstRowInTable);

		result = new DictRowBIC(row);
		
		row.click();
		fBIC.findElement(buttonApply).click();
		wait.until(ExpectedConditions.invisibilityOf(fBIC));
		
		return result;
	}
}
