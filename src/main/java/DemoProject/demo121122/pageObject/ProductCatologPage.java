package DemoProject.demo121122.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponents;

public class ProductCatologPage extends AbstractComponents {
	
	WebDriver driver;
	public ProductCatologPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	List<WebElement>products;
	
	@FindBy(css=".mb-3")
	WebElement products1;
	
	@FindBy(id="toast-container")
	WebElement toastmsg;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getproductList()
	{
		visibilityOfWebElement(products1);
		return products;
		
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod=getproductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		visibilityOfWebElement(toastmsg);
		inVisibilityOfWebElement(spinner);
	}
	
	
	
}
