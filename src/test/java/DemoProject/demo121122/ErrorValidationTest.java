package DemoProject.demo121122;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BasePackage.Base;
import BasePackage.Retry;
import DemoProject.demo121122.pageObject.CartPage;
import DemoProject.demo121122.pageObject.ProductCatologPage;

public class ErrorValidationTest extends Base {
	
	
	@Test(retryAnalyzer=Retry.class)
	public void loginErrorValidation()
	{

	landingpage.loginApplication("kumbharmithun@gmail.com", "Mithun@1234");
	String loginErrorMsg=landingpage.getLoginError();
	Assert.assertEquals("Incorrect email or password.", loginErrorMsg);
	}
	
	@Test(groups= {"ErrorValidation"})
	public void productErrorvalidation() throws InterruptedException
	{
		String productName="ZARA COAT 3";
		ProductCatologPage productcatalogpage=landingpage.loginApplication("rajashrikumbhar92@gmail.com", "Rajashri@123");
		
		List<WebElement> products=productcatalogpage.getproductList();
		productcatalogpage.addToCart(productName);
		CartPage cartpage= productcatalogpage.goToCartPage();
		
		boolean match=cartpage.verifyCartProduct("ZARA COAT 33");
		 Assert.assertFalse(match);
	}
	
}
