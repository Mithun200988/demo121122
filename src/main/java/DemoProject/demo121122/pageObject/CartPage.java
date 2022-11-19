package DemoProject.demo121122.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> cartproduct;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButtton;
	
	public boolean verifyCartProduct(String productName)
	{
		boolean match=cartproduct.stream().anyMatch(s->s.getText().equals(productName));
		return match;
	}
	
	public checkOutPage gotoCheckout()
	{
		checkOutButtton.click();
		checkOutPage checkoutpage= new checkOutPage(driver);
		return checkoutpage;
	}

}
