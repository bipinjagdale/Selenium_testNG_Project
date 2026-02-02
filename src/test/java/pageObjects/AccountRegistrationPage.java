package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	

	@FindBy(xpath="//input[@id='input-firstname']") WebElement text_fname;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement text_lname;
	@FindBy(xpath="//input[@id='input-email']") WebElement text_email;
	@FindBy(xpath="//input[@id='input-password']") WebElement text_pass;
	@FindBy(xpath="//input[@name='agree']") WebElement agree_terms;
	@FindBy(xpath="//button[normalize-space()='Continue']") WebElement btncontinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msg_confirmation;
	
	
	public void setFirstName(String fname)
	{
		text_fname.sendKeys(fname);
	}
	
	public void setLasttName(String lname)
	{
		text_lname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		text_email.sendKeys(email);
	}
	
	public void setPass(String pass)
	{
		text_pass.sendKeys(pass);
	}
	
	public void terms_condtions()
	{
		agree_terms.click();
	}
	
	public void continue_btn()
	{
		btncontinue.click();
	}
	
	
	public String getConfirmationmsg()
	{
		try {
			return (msg_confirmation.getText());
		} catch(Exception e) {
			return (e.getMessage());
		}
	}

}
