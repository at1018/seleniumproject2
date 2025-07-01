package seleniumframeworkdesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="//button[contains(@class,'ta-item')]")
	List<WebElement> cartItemList;
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	By result=By.cssSelector(".ta-results");
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void selectCountry(String countryName) {
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitforElementToAppear(result);
		WebElement item=cartItemList.stream().filter(product->product.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		item.click();
	}
	
	public void clickOnPlaceOrderButton() {
		placeOrder.click();
		
	}
}
