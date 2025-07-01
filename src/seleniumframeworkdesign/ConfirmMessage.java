package seleniumframeworkdesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class ConfirmMessage extends AbstractComponent{
	WebDriver driver;
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	public ConfirmMessage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public String getMessage() {
		String message=confirmationMessage.getText();
		return message;
	}

}
