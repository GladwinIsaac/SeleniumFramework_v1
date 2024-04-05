package swaglabs.POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swaglabs.Abstractcomp.AbstractComp;

public class Shoppingcart extends AbstractComp {

	WebDriver driver;
	

	public Shoppingcart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement shoppingcart_label;

	@FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item']")
	WebElement listofprod_cart;

	@FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item']")
	List<WebElement> prod_cart_list;

	

	@FindBy(id = "checkout")
	WebElement checkout_button;

	By product_cartBy = By.xpath("//div[@class='cart_list']/div[@class='cart_item']");

	public boolean shoppingcart_func(String product_buy) {
		shoppingcart_label.click();
		visibilityofElement(product_cartBy);
		// Collect the list of products
		boolean shoppingcart_product = product_check(product_buy);
		return shoppingcart_product;
	}

	public CheckoutPage checkout_link() {
		checkout_button.click();
		CheckoutPage checkout = new CheckoutPage(driver);
		return checkout;
	}

	public boolean product_check(String product_buy) {
		boolean prod_data = driver.findElement(By.xpath("//div[contains(text(),'" + product_buy + "')]")).isDisplayed();
		return prod_data;
	}

}
