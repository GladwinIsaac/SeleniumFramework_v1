package swaglabs.Abstractcomp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComp {

	WebDriver driver;

	public AbstractComp(WebDriver driver) {
		this.driver = driver;
	}

	public void visibilityofElement(By productBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(productBy));
	}

	public void invisibilityofElement(By productBy) {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait1.until(ExpectedConditions.invisibilityOfElementLocated(productBy));
	}
	
	public void visibilityofWebElement(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
}
