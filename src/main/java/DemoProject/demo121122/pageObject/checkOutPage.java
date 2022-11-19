package DemoProject.demo121122.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents {
	
	WebDriver driver;
	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder*='Country']")
	WebElement country;
	
	@FindBy(css=".ta-results")
	WebElement countyresult;
	
	@FindBy(css=".ta-item:last-child")
	WebElement actualcountry;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	
	public void selectcountry(String countryName)
	{
		country.sendKeys(countryName);
		visibilityOfWebElement(countyresult);
		actualcountry.click();
		
	}
	
	public ConfirmationPage submitOrder()
	{
		placeOrderBtn.click();
		return new ConfirmationPage(driver);
		
	}
	

}
