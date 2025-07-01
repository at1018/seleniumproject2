package seleniumframeworkdesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement userEmails=driver.findElement(By.id("userEmail"));
	//Above code can be written in below way it can initalized in using the above pagefactory method
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPassword;
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css="[class*='toast-message']")
	WebElement errorMessage;
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public void landingApplication(String email, String Password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(Password);
		submit.click();
	}
	public String getErrorMessage() {
		return errorMessage.getText();
		
	}

}
