package swaglabs.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swaglabs.Abstractcomp.AbstractComp;

public class CheckoutPage extends AbstractComp {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastname;

	@FindBy(css = "#postal-code")
	WebElement postal_code;

	@FindBy(xpath = "//input[@id='continue']")
	WebElement checkout;

	@FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item']")
	WebElement listofprod_cart;

	@FindBy(xpath = "//div[text()='Swag Labs']")
	WebElement checkout_header;

	@FindBy(id = "finish")
	WebElement finish_button;

	@FindBy(className = "complete-header")
	WebElement thankyou_message;

	By product_cartBy = By.xpath("//div[@class='cart_list']/div[@class='cart_item']");
	By checkout_headerBy = By.xpath("//div[text()='Swag Labs']");

	public void deliverydetails(String fname, String lname, String pcode) {
		invisibilityofElement(product_cartBy);
		firstName.sendKeys(fname);
		lastname.sendKeys(lname);
		postal_code.sendKeys(pcode);
		checkout.click();
	}

	public boolean header_validation() {
		visibilityofElement(checkout_headerBy);
		Boolean checkout_page = checkout_header.isDisplayed();
		return checkout_page;
	}

	public String order_page(String product_buy) {
		String ordered_product = prod_name(product_buy).getText();
		return ordered_product;
	}

	public void complete_order() {
		finish_button.click();
	}

	public WebElement prod_name(String product_buy) {
		return driver.findElement(By.xpath("//div[text()='" + product_buy + "']"));
	}

	public String checkout_message() {
		String confirm_message = thankyou_message.getText();
		return confirm_message;
	}
}
