package swaglabs.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swaglabs.Abstractcomp.AbstractComp;

public class ProductView extends AbstractComp {

	WebDriver driver;

	public ProductView(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//String product_buy = "Sauce Labs Fleece Jacket";

	@FindBy(xpath = "//span[text()='Products']")
	WebElement product_label;

	//By product = By.xpath("//div[contains(text(),'" + product_buy + "')]");
	
	//@FindBy(xpath = "//div[contains(text(),'" + product_buy + "')]")
	//WebElement product;
	// WebElement product =
	// driver.findElement(By.xpath("//div[contains(text(),'"+product_buy+"')]"));

	@FindBy(xpath = "//div[@class='inventory_details_name large_size']")
	WebElement product_name;

	@FindBy(xpath = "//div[@class='inventory_details_name large_size']")
	WebElement product_display;

	By productBy = By.xpath("//span[text()='Products']");

	public boolean product_display() {
		visibilityofElement(productBy);
		Boolean flag = product_label.isDisplayed();
		return flag;
	}

	public String product_select(String product_buy) {
		product_click(product_buy);
		String product_name = product_display.getText();
		return product_name;
	}

	public void product_click(String product_buy) {
		driver.findElement(By.xpath("//div[contains(text(),'" + product_buy + "')]")).click();
	}
	
	public ProductBuy product_addTocart(String product_buy)
	{
		ProductBuy buy = new ProductBuy(driver, product_buy);
		return buy;
	}

}
