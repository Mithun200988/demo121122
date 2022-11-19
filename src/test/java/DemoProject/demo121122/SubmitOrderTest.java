package DemoProject.demo121122;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BasePackage.Base;
import DemoProject.demo121122.pageObject.CartPage;
import DemoProject.demo121122.pageObject.ConfirmationPage;
import DemoProject.demo121122.pageObject.OrderPage;
import DemoProject.demo121122.pageObject.ProductCatologPage;
import DemoProject.demo121122.pageObject.checkOutPage;
import Utilities.JsonReader;



public class SubmitOrderTest extends Base {

	String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData", groups="purches")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatologPage productcatalogpage=landingpage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products=productcatalogpage.getproductList();
		productcatalogpage.addToCart(input.get("productname"));
		CartPage cartpage= productcatalogpage.goToCartPage();
		
		boolean match=cartpage.verifyCartProduct(input.get("productname"));
		 Assert.assertTrue(match);
		 checkOutPage checkoutpage=cartpage.gotoCheckout();
		 checkoutpage.selectcountry("India");
		 
		 ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		 String Msgtext= confirmationpage.verifyConfirmMsg();
		 
		 Assert.assertEquals("THANKYOU FOR THE ORDER.", Msgtext);
		
		
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatologPage productcatalogpage=landingpage.loginApplication("kumbharmithun@gmail.com", "Mithun@123");
		OrderPage orderpage=productcatalogpage.goToOrderPage();
		Assert.assertTrue(orderpage.verifyorderProduct(productName));
		//Assert.assertTrue(match);
	}
	 
	@DataProvider
	public Object[][] getData() throws IOException
	{
		JsonReader jsonreader=new JsonReader();
		List<HashMap<String, String>> data=jsonreader.getJesonData(System.getProperty("user.dir")+"/src/test/java/resources/purchesorderdata.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	

	/*HashMap<String,String> map= new HashMap<String,String>();
	map.put("email", "kumbharmithun@gmail.com");
	map.put("password", "Mithun@123");
	map.put("productname", "ZARA COAT 3");
	
	HashMap<String,String> map1= new HashMap<String,String>();
	
	map1.put("email", "rajashrikumbhar92@gmail.com");
	map1.put("password", "Rajashri@123");
	map1.put("productname", "ADIDAS ORIGINAL");
	return new Object[][] {{map},{map1}};*/

}
