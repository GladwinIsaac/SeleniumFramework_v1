package swaglabs.Testcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import swaglabs.POM.LoginPage;

public class BaseTest {

	public WebDriver driver ;
	public LoginPage login;
	public WebDriver initializedriver() throws IOException 
	{	
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\swaglabs\\Resources\\Globaldata.properties");
		prop.load(file);
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Unable to find the mentioned Browser !!!");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException
	{
		driver = initializedriver();
		login = new LoginPage(driver);
		// browser navigates to Swag Labs url
		login.navigateTo();
		return login;
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown()
	{
		driver.quit();
	}
}
