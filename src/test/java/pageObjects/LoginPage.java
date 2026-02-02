package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement ent_email;
	@FindBy(xpath="//input[@id='input-password']") WebElement ent_pass;
	@FindBy(xpath="//button[normalize-space()='Login']") WebElement clkLogin;

	public void setEmail(String email)
	{
		ent_email.sendKeys(email);
	}

	public void setPassword(String pass)
	{
		ent_pass.sendKeys(pass);
	}

	public void clickLoginbt()
	{
		clkLogin.click();
	}

}
