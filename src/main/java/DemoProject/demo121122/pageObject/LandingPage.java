package DemoProject.demo121122.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginErrormsg;
	
	public ProductCatologPage loginApplication(String uname, String pass)
	{
		userEmail.sendKeys(uname);
		password.sendKeys(pass);
		submit.click();
		ProductCatologPage productcatalogpage= new ProductCatologPage(driver);
		return productcatalogpage;
		
	}
	
	public String getLoginError()
	{
		visibilityOfWebElement(loginErrormsg);
		return loginErrormsg.getText();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

}
