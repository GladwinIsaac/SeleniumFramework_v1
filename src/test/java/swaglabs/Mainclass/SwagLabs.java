package swaglabs.Mainclass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swaglabs.POM.CheckoutPage;
import swaglabs.POM.ProductBuy;
import swaglabs.POM.ProductView;
import swaglabs.POM.Shoppingcart;
import swaglabs.Testcomponents.BaseTest;

public class SwagLabs extends BaseTest {

	@Test(dataProvider = "getdata",groups = {"Smoke"})
	public void SwagLabs_Main(HashMap<String, String> input) throws InterruptedException, IOException 
	{
		// Loginto the Sauce labs ->
		//String product_buy = "Sauce Labs Fleece Jacket";
		// Login page -> userid & pwd -> Login
		login.Userlogin(input.get("username"), input.get("pwd"));
		ProductView prod = login.prod_catalogue();
		// Navigate to the Product catalogue page
		boolean flag = prod.product_display();
		Assert.assertTrue(flag);
		// click the product(if its available)
		String product_name = prod.product_select(input.get("product_buy"));
		// Check whether the selected product and available product is same
		Assert.assertEquals(product_name, input.get("product_buy"));
		// Buy & Fetch the price of the product
		ProductBuy buy = prod.product_addTocart(input.get("product_buy"));
		buy.fetch_price();
		// Click the buy link
		buy.prod_buy();
		Thread.sleep(2000);
		Boolean remove_flag = buy.removeflag_check();
		Assert.assertTrue(remove_flag);
		// Click on the shopping cart -> Collect the list of products -> Click on the checkout button
		// Shoppingcart shop = new Shoppingcart(driver, product_buy);
		Shoppingcart shop = buy.cartPage();
		boolean shoppingcart_product = shop.shoppingcart_func(input.get("product_buy"));
		Assert.assertTrue(shoppingcart_product);
		CheckoutPage checkout = shop.checkout_link();
		// Checkout Information page - Delivery details
		checkout.deliverydetails("Gladwin", "Isaac", "627007");
		boolean checkout_page = checkout.header_validation();
		Assert.assertTrue(checkout_page);
		String ordered_product = checkout.order_page(input.get("product_buy"));
		Assert.assertEquals(ordered_product, input.get("product_buy"));
		if (ordered_product.equalsIgnoreCase(input.get("product_buy"))) {
			System.out.println(ordered_product + " Product Ready to Checkout");
		}
		checkout.complete_order();
		String confirmation_message = checkout.checkout_message();
		System.out.println(confirmation_message);
		
	}
	
	@DataProvider
	public Object[][] getdata()
	{
		HashMap<String, String> hash = new HashMap();
		hash.put("username", "standard_user");
		hash.put("pwd", "secret_sauce");
		hash.put("product_buy", "Sauce Labs Fleece Jacket");
		
		HashMap<String, String> hash1 = new HashMap();
		hash1.put("username", "standard_user");
		hash1.put("pwd", "secret_sauce");
		hash1.put("product_buy", "Sauce Labs Bike Light");
		
		return new Object[][] {{hash},{hash1}};
	}

	
	public void getScreenshotas(String testcasename) throws IOException
	{
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//Screenshots//"+testcasename+""+timestamp()+".png");
		FileUtils.copyFile(src, dest);
	}

	public String timestamp() 
	{
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	
}
