package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DemoProject.demo121122.pageObject.CartPage;
import DemoProject.demo121122.pageObject.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderButton;
	
	public CartPage goToCartPage()
	{
		cartButton.click();
		CartPage cartpage= new CartPage(driver);
		return cartpage;
	}
	
	
	public OrderPage goToOrderPage()
	{
		orderButton.click();
		OrderPage orderpage= new OrderPage(driver);
		return orderpage;
	}

	public void visibilityOfWebElement(WebElement ele)
	{
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(ele));
	
	}
	
	public void inVisibilityOfWebElement(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
	//WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	//wait.until(ExpectedConditions.invisibilityOf(ele));
	
	}
}
