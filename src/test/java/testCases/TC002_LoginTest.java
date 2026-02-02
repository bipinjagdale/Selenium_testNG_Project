package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verify_login() throws InterruptedException
	{
		logger.info("******* TC002_LogiTest *******");
		
		try {
			HomePage hp =new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			LoginPage lp=new LoginPage(driver);
			
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLoginbt();
			
			MyAccountPage mcc=new MyAccountPage(driver);
			
			boolean targetPage = mcc.getHeading();
			
			Assert.assertEquals(targetPage, true);
			}
		
			catch(Exception e)
			{
				Assert.fail();
			}
		
		logger.info("*****  Finished TC002__LoginTest ********");
		
	
	
	}	
	

}
