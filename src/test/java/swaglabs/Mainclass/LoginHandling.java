package swaglabs.Mainclass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import swaglabs.Testcomponents.BaseTest;

public class LoginHandling extends BaseTest {

	@Test
	public void SwagLabs_Main() throws InterruptedException, IOException 
	{
		// Loginto the Sauce labs ->
		String product_buy = "Sauce Labs Fleece Jacket";
		// Login page -> userid & pwd -> Login
		login.Userlogin("standard_u", "secret_sauce");
		Thread.sleep(2000);
		String error_message = login.incorrect_userid();
		Assert.assertEquals(error_message, "Epic sadface: Username and password do not match any user in this service");
	}

}
