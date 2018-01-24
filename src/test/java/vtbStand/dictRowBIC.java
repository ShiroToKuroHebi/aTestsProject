package vtbStand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
//Название класса в верблюжем формате, не надо начинать классы с маленькой буквы
//Не понятно для чего  класс этот, чтобы один кас кликнуть по строке?
class dictRowBIC {
	final String BIC;
	final String NAME;
	final String ACC;
	final String TOWN;
	
	/**
	 *
	 * @param tableRow - expected element is located by xpath("//div[@class=\"table__body\"]/div[x]")
	 */
	dictRowBIC(WebElement tableRow) {
		List<WebElement> list = tableRow.findElements(By.xpath(".//div/div"));
		
		// as there should be only 4 fields...
		BIC = list.get(0).findElement(By.xpath(".//span")).getText();
		NAME = list.get(1).findElement(By.xpath(".//span")).getText();
		ACC = list.get(2).findElement(By.xpath(".//span")).getText();
		TOWN = list.get(3).findElement(By.xpath(".//span")).getText();
	}
	
	dictRowBIC(String BIC, String NAME, String ACC, String TOWN) {
		this.BIC = BIC;
		this.NAME = NAME;
		this.ACC = ACC;
		this.TOWN = TOWN;
	}
	
	void print() {
		System.out.println("BIC : " + BIC);
		System.out.println("Name: " + NAME);
		System.out.println("Acc : " + ACC);
		System.out.println("Town: " + TOWN);
	}
}
