package seleniumframeworkdesign;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class StandAloneTest3 extends BaseTest{
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		String productName="ADIDAS ORIGINAL";
		landingpage.landingApplication("ankit123456@gmail.com", "Abcd@1234");
		System.out.println(landingpage.getErrorMessage());
		Assert.assertEquals("Incorrect email password.", landingpage.getErrorMessage());
	}
	@Test
	public void submitOrder() throws IOException {
		String productName="ADIDAS ORIGINAL";
		landingpage.landingApplication("ankit1234@gmail.com", "Abcd@1234");
		ProductCataloge productcataloge=new ProductCataloge(driver);
		List<WebElement> products=productcataloge.getProductList();
		productcataloge.addToCart(productName);
		productcataloge.goToCart();
		CartItem cartitem=new CartItem(driver);
		boolean value= cartitem.cartSection("ADIDAS ORIGINAL 1");
		Assert.assertTrue(value);
	}
}
