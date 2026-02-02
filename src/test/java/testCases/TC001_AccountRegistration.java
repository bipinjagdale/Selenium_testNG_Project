package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistration extends BaseClass {
		
	@Test(groups={"Regression","Master"})
	void verify_account_registration() throws InterruptedException
	{
		logger.info("******* Starting TC001_AccountRegistration *******");
		Thread.sleep(5);
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on my account");
		hp.clickRegister();
		logger.info("clicked on account registration");
		Thread.sleep(5);
		AccountRegistrationPage ar=new AccountRegistrationPage(driver);
		logger.info("providing customer details");
		ar.setFirstName(randomeString().toUpperCase());
		ar.setLasttName(randomeString().toUpperCase());		
		ar.setEmail(randomeString() + "@gmail.com");
		ar.setPass(randomeAplhaNumber());
		ar.terms_condtions();
		ar.continue_btn();
		String confirm=ar.getConfirmationmsg();
		
		Assert.assertEquals(confirm, "Your Account Has Been Created!");
		
	}
	catch(Exception e)
	{
		logger.info("Test Failed..");

		logger.debug("Debug logs..");

		Assert.fail();
	}
	
		logger.info("*****  Finished TC001_AccountRegistration  *****");

	
	
	}
	

}
