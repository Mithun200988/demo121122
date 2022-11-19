package DemoProject.demo121122;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {

	public static void main(String[] args) {


		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("kumbharmithun@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mithun@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
		List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		List<WebElement> cartproduct=driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match=cartproduct.stream().anyMatch(s->s.getText().equals(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("input[placeholder*='Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		driver.findElement(By.cssSelector(".ta-item:last-child")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		String Msgtext=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", Msgtext);
		
		driver.findElement(By.cssSelector("button[routerlink*='myorders']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("tr td:nth-child(3)"))));
		List<WebElement>orderProducts=driver.findElements(By.cssSelector("tr td:nth-child(3)"));
		/*for(WebElement prod1:orderProducts)
		{
			System.out.println(prod1.getText());
		}*/
		boolean match1=orderProducts.stream().anyMatch(orderProduct->orderProduct.getText().equalsIgnoreCase(productName));
		System.out.println(match1);

	}

}
