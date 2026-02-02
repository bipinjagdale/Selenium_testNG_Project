package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	
	}
	
	@FindBy(xpath="//h1[normalize-space()='My Account']") WebElement msgHeading;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnklogout;
	@FindBy(xpath="//a[@class=\"btn btn-primary\"][normalize-space()=\"Continue\"]") WebElement lnkContinue;
	
	public boolean getHeading()
	{
		try
		{
		return msgHeading.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clicklLogout()
	{
		lnklogout.click();
	}
	
	public void clickContinue()
	{
		lnkContinue.click();
	}

}
