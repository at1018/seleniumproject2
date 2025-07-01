package seleniumframeworkdesign;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {
	WebDriver driver;
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orderHistoryPage;
	@FindBy(css=".totalRow button")
	WebElement proceedButton;
	public OrderHistoryPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	public boolean myOrders(String productName) {
		boolean value=orderHistoryPage.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return value;
	}
}
