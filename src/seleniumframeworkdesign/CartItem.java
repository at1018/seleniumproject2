package seleniumframeworkdesign;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class CartItem extends AbstractComponent {
	WebDriver driver;
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItem;
	@FindBy(css=".totalRow button")
	WebElement proceedButton;
	public CartItem(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	public boolean cartSection(String productName) {
		boolean value=cartItem.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return value;
	}
	public void clickToProceedButton() {
		proceedButton.click();
	}
}
