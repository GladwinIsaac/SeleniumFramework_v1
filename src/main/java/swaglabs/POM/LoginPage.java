package swaglabs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swaglabs.Abstractcomp.AbstractComp;

public class LoginPage extends AbstractComp {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement username = driver.findElement(By.cssSelector("input#user-name"));
	// driver.findElement(By.id("password"))
	// driver.findElement(By.name("login-button"))

	@FindBy(css = "input#user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(name = "login-button")
	WebElement login;
	
	@FindBy(xpath = "//div[@class = 'error-message-container error']")
	WebElement error_message;

	public void Userlogin(String user_id, String pwd) {
		username.sendKeys(user_id);
		password.sendKeys(pwd);
		login.click();	
	}

	public String incorrect_userid() throws InterruptedException
	{
		visibilityofWebElement(error_message);
		return error_message.getText();
	}
	
	public ProductView prod_catalogue()
	{
		ProductView prod = new ProductView(driver);
		return prod;
	}
	
	public void navigateTo() {
		driver.get("https://www.saucedemo.com/");
	}

}
