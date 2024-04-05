package swaglabs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductBuy {

	WebDriver driver;
	String product_buy;

	public ProductBuy(WebDriver driver, String product_buy) {
		this.driver = driver;
		this.product_buy = product_buy;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='inventory_details_price']")
	WebElement price_label;

	@FindBy(xpath = "//div[@class='inventory_details_price']/following::button")
	WebElement buy_label;

	@FindBy(xpath = "//button[contains(text(),'Remove')]")
	WebElement remove_label;

	

	public void fetch_price() {

		String price = price_label.getText();

		// Validate if the price is empty or available
		// try catch
		if (price.isEmpty()) {
			System.err.println("Price data unavailable for " + product_buy + " : " + price);
		}

		System.out.println("Price for " + product_buy + " : " + price);
	}

	public void prod_buy() {
		buy_label.click();
	}

	public Boolean removeflag_check() {
		Boolean remove_flag = remove_label.isDisplayed();
		return remove_flag;
	}

	public Shoppingcart cartPage() {
		Shoppingcart shop = new Shoppingcart(driver);
		return shop;
	}

}
