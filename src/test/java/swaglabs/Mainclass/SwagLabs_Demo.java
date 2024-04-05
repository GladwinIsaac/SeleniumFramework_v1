package swaglabs.Mainclass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import swaglabs.POM.LoginPage;

public class SwagLabs_Demo {

	public static void main(String[] args) throws Exception 
	{
		
		//Loginto the Sauce labs -> 
		WebDriver driver;
		String product_buy = "Sauce Labs Fleece Jacket";
		
		
		driver = new ChromeDriver();
		LoginPage login = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.cssSelector("input#user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.name("login-button")).click();
		
		WebElement product_label = driver.findElement(By.xpath("//span[text()='Products']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(product_label));
		Boolean flag =  product_label.isDisplayed();
		Assert.assertTrue(flag);
		
		driver.findElement(By.xpath("//div[contains(text(),'"+product_buy+"')]")).click();
		String product_name = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']")).getText();
		
		Assert.assertEquals(product_name, product_buy);
		
		if(!product_buy.equalsIgnoreCase(product_name))
		{
			System.err.println("------Fail !!------");
		}
		
		String price = driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText();
		//try catch
		if(price.isEmpty())
		{
			System.err.println("Price for "+product_buy+ " : " +price);
			Assert.assertFalse(price.isEmpty());
		}
		
		System.out.println("Price for "+product_buy+ " : " +price);
		
		driver.findElement(By.xpath("//div[@class='inventory_details_price']/following::button")).click();
		Thread.sleep(2000);
		//try catch
		Boolean remove_flag= driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).isDisplayed();
		if(!remove_flag)
		{
			Assert.assertTrue(remove_flag);
		}
		
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart_list']/div[@class='cart_item']")));
		List<WebElement> lst = driver.findElements(By.xpath("//div[@class='cart_list']/div[@class='cart_item']"));
		
		boolean shoppingcart_product = driver.findElement(By.xpath("//div[contains(text(),'"+product_name+"')]")).isDisplayed();
		
		if(shoppingcart_product)
		{
			driver.findElement(By.id("checkout")).click();
		}
		
		
		//Checkout Information page
		WebElement firstName = driver.findElement(By.id("first-name"));
		WebElement lastname = driver.findElement(By.xpath("//input[@id='last-name']"));
		WebElement postal_code = driver.findElement(By.cssSelector("#postal-code"));
		WebElement checkout = driver.findElement(By.xpath("//input[@id='continue']"));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='cart_list']/div[@class='cart_item']")));
		firstName.sendKeys("Gladwin");
		lastname.sendKeys("Isaac");
		postal_code.sendKeys("627007");
		
		
		
		checkout.click();
		
		WebElement checkut_header = driver.findElement(By.xpath("//div[text()='Swag Labs']"));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Swag Labs']")));
		boolean checkout_page = checkut_header.isDisplayed();
		
		if(!checkout_page)
		{
			System.out.println("Checkout page Failed ");
		}
		
		
		String ordered_product = driver.findElement(By.xpath("//div[text()='"+product_buy+"']")).getText();//Sauce Labs Fleece Jacket']
		
		if(ordered_product.equalsIgnoreCase(product_buy))
		System.out.println(ordered_product+" Product Ready to Checkout");
		
		driver.findElement(By.id("finish")).click();
		
		driver.quit();
	}

}
