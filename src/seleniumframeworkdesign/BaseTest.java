package seleniumframeworkdesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	WebDriver driver;
	LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException {
		FileInputStream fileStream=new FileInputStream("C:\\Users\\anujt\\OneDrive\\Documents\\Selenium Project\\SeleniumFrameworkDesign\\src\\seleniumframeworkdesign\\GlobalData.properties");
		Properties props=new Properties();
		props.load(fileStream);
		String browser=System.getProperty("browser")!=null?System.getProperty("browser"):props.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
		driver= new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("chromeheadless")) {
			ChromeOptions option=new ChromeOptions();
			option.addArguments("headless");
			driver= new ChromeDriver(option);
			}
		else if(browser.equalsIgnoreCase("firefox")) {
			//edge
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
//	C:\Users\anujt\OneDrive\Documents\Selenium Project\SeleniumFrameworkDesign\src\seleniumframeworkdesign\PurchaseOrder.Json
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//Read Json to String
		File file=new File(filePath);
		String jsonContent=FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		//String to HashMap Jackson Databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot Ts= (TakesScreenshot)driver;
		File source=Ts.getScreenshotAs(OutputType.FILE);
		File file=new File("C:\\Users\\anujt\\OneDrive\\Documents\\Selenium Project\\SeleniumFrameWorkDesign\\Reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return "C:\\Users\\anujt\\OneDrive\\Documents\\Selenium Project\\SeleniumFrameworkDesign\\Reports" + testCaseName + ".png";
	}
	@BeforeMethod(alwaysRun=true)
	public void launchApplication(ITestContext context) throws IOException {
		driver=initializeDriver();
		landingpage=new LandingPage(driver);
		landingpage.goTo();
		context.setAttribute("driver", driver);
	}
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.close();
	}
}
