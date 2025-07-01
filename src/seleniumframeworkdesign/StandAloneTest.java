package seleniumframeworkdesign;

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

public class StandAloneTest {

	public static void main(String[] args) {
		String productName="ADIDAS ORIGINAL";
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(9));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("ankit1234@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abcd@1234");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> itemList=driver.findElements(By.cssSelector(".mb-3"));
		System.out.println(itemList);
		WebElement prod=itemList.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		System.out.println(prod);
		prod.findElement(By.cssSelector("div[class='card-body'] button:last-of-type")).click();
//		Assert.assertTrue(value);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']"))).click();
//		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
//		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
//		driver.findElement(By.cssSelector("//button[@routerlinks='/dashboard/cart']")).click();
		List<WebElement> cartItem=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean value=cartItem.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(value);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a=new Actions(driver);
//		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
		
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		List<WebElement> cartItemList=driver.findElements(By.xpath("//button[contains(@class,'ta-item')]"));
		WebElement item=cartItemList.stream().filter(product->product.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		item.click();
		//		for(WebElement item:cartItemList) {
//			if(item.getText().equalsIgnoreCase("india")) {
//				item.click();
//				break;
//			}
//		}
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	}

}
