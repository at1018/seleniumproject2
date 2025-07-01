package seleniumframeworkdesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StandAloneTest2 extends BaseTest{
//	String productName="ADIDAS ORIGINAL";
//  This is without Hash map data set
//	@Test(dataProvider="getData", groups= {"purchase"})	
//	public void submitOrder(String email, String password, String productName) throws IOException {
//		WebDriver driver= new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(9));
//		LandingPage landingpage=new LandingPage(driver);
//		landingpage.goTo();
//		launchApplication();
//		landingpage.landingApplication(email, password);
//		ProductCataloge productcataloge=new ProductCataloge(driver);
//		List<WebElement> products=productcataloge.getProductList();
//		productcataloge.addToCart(productName);
//		productcataloge.goToCart();
//		CartItem cartitem=new CartItem(driver);
//		boolean value= cartitem.cartSection(productName);
//		Assert.assertTrue(value);
//		cartitem.clickToProceedButton();
//		CheckOutPage checkout=new CheckOutPage(driver);
//		checkout.selectCountry("india");
//		checkout.clickOnPlaceOrderButton();
//		ConfirmMessage confirmmessage=new ConfirmMessage(driver);
//		String confirmationMessage=confirmmessage.getMessage();
//		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
//	}
// This is with Hash map data set
	@Test(dataProvider="getData", groups= {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
//		WebDriver driver= new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(9));
//		LandingPage landingpage=new LandingPage(driver);
//		landingpage.goTo();
//		launchApplication();
		landingpage.landingApplication(input.get("email"), input.get("password"));
		ProductCataloge productcataloge=new ProductCataloge(driver);
		List<WebElement> products=productcataloge.getProductList();
		productcataloge.addToCart(input.get("productName"));
		productcataloge.goToCart();
		CartItem cartitem=new CartItem(driver);
		boolean value= cartitem.cartSection(input.get("productName"));
		Assert.assertTrue(value);
		cartitem.clickToProceedButton();
		CheckOutPage checkout=new CheckOutPage(driver);
		checkout.selectCountry("india");
		checkout.clickOnPlaceOrderButton();
		ConfirmMessage confirmmessage=new ConfirmMessage(driver);
		String confirmationMessage=confirmmessage.getMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	@Test(dependsOnMethods= {"submitOrder"}, dataProvider="getData")
	public void orderHistory(HashMap<String, String> input) {
		landingpage.landingApplication(input.get("email"), input.get("password"));
		OrderHistoryPage orderhistory=new OrderHistoryPage(driver);
		orderhistory.goToOrderHistory();
		Assert.assertTrue(orderhistory.myOrders(input.get("productName")));
		
	}
//	@Test(dependsOnMethods= {"submitOrder"}, dataProvider="getData")
//	public void orderHistory(String productName) {
//		landingpage.landingApplication("ankit1234@gmail.com", "Abcd@1234");
//		OrderHistoryPage orderhistory=new OrderHistoryPage(driver);
//		orderhistory.goToOrderHistory();
//		Assert.assertTrue(orderhistory.myOrders(productName));
//		
//	}
//	public String getScreenshot(String testCaseName) throws IOException {
//		TakesScreenshot Ts= (TakesScreenshot) driver;
//		File source=Ts.getScreenshotAs(OutputType.FILE);
//		File file=new File("C:\\Users\\anujt\\OneDrive\\Documents\\Selenium Project\\SeleniumFrameworkDesign\\Reports" + testCaseName + ".png");
//		FileUtils.copyFile(source, file);
//		return "C:\\Users\\anujt\\OneDrive\\Documents\\Selenium Project\\SeleniumFrameworkDesign\\Reports" + testCaseName + ".png";
//	}
	//Using Json file
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\seleniumframeworkdesign\\PurchaseOrder.Json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
//  Using normal data set
//	@DataProvider
//	public Object[][] getData(){
//		return new Object[][] {{"ankit1234@gmail.com","Abcd@1234","ADIDAS ORIGINAL"},{"ankit12345@gmail.com","Abcd@1234","IPHONE 13 PRO"}};
//	}
//  Using Hash Map data set
//	@DataProvider
//	public Object[][] getData(){
//		HashMap <String, String> map=new HashMap<String, String>();
//		map.put("email", "ankit1234@gmail.com");
//		map.put("password", "Abcd@1234");
//		map.put("productName", "ADIDAS ORIGINAL");
//		HashMap <String, String> map1=new HashMap<String, String>();
//		map1.put("email", "ankit12345@gmail.com");
//		map1.put("password", "Abcd@1234");
//		map1.put("productName", "IPHONE 13 PRO");
//		return new Object[][] {{map},{map1}};
//	}
