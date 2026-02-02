package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/* Data is valid - login success -test pass -logout
 Data is valid - login failed - test fail
 
 Data is invalid - login success - test fail -logout
 Data is invalid - login failed - test pass 
 */

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven") //getting data provider from DataProviders class.
	public void verify_loginDDT(String email, String pass, String exp)
	{
		logger.info("*****  Starting TC003_LoginDDT Started *****");
		
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
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				mcc.clicklLogout();
				mcc.clickContinue();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if((targetPage==true))
			{
				mcc.clicklLogout();
				mcc.clickContinue();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
				
		}
		
		logger.info("*****  TC003_LogigDDT finished  *****");
			
	}catch(Exception e)
		{
			Assert.fail();
		}
		}
	
	
	

		
}
