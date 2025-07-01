package seleniumframeworkdesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class ProductCataloge extends AbstractComponent {
	WebDriver driver;
	public ProductCataloge(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".mb-3")
	List<WebElement> productList;
	@FindBy(css=".ng-animating")
	WebElement animating;
	By productPath=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector("div[class='card-body'] button:last-of-type");
	By totastMessage=By.cssSelector("#toast-container");
	public List<WebElement> getProductList() {
		waitforElementToAppear(productPath);
		return productList;
		
	}
	public WebElement getProductToCart(String productName) {
		WebElement product=getProductList().stream().filter(product1->
		product1.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return product;
	}
	public void addToCart(String productName) {
		WebElement product=getProductToCart(productName);
		product.findElement(addToCart).click();
		waitforElementToAppear(totastMessage);
		waitforElementToDisappear(animating);
		
	}
	
}