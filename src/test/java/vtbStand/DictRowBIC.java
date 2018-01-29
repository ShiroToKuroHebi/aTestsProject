package vtbStand;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
//Не понятно для чего  класс этот, чтобы один кас кликнуть по строке?

// (*) Изменил логику теста, данный класс больше не используется.
// Оставлен на случай, если понадобится в дальнейшем
public class DictRowBIC {
	public final String BIC;
	public final String NAME;
	public final String ACC;
	public final String TOWN;
	
	/**
	 *
	 * @param tableRow - expected element is located by xpath("//div[@class=\"table__body\"]/div[x]")
	 */
	public DictRowBIC (WebElement tableRow) {
		List<WebElement> list = tableRow.findElements(By.xpath(".//div/div"));
		
		// as there should be only 4 fields...
		BIC = list.get(0).findElement(By.xpath(".//span")).getText();
		NAME = list.get(1).findElement(By.xpath(".//span")).getText();
		ACC = list.get(2).findElement(By.xpath(".//span")).getText();
		TOWN = list.get(3).findElement(By.xpath(".//span")).getText();
	}
}
