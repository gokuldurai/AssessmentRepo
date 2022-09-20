package selenium;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCart {

	@Test
	public void AddToCartProduct() {
	
		
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("https://www.flipkart.com/");
	 System.out.println("flipkart opened successfully");
	
	driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
	driver.findElement(By.name("q")).sendKeys("winter heater");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	driver.findElement(By.partialLinkText("DARSHANAM WORLD ")).click();
	
	
	Set<String> windowIds= driver.getWindowHandles();
	
	Iterator<String> it=windowIds.iterator();
	
	while(it.hasNext()) {
		String winId=it.next();
		
		String currentTitle=driver.switchTo().window(winId).getTitle();
		if(currentTitle.contains("DARSHANAM WORLD")) {
			break;
		}
	}
	String actualProduct = driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
	driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
    String expectedProduct=driver.findElement(By.xpath("//a[@class='_2Kn22P gBNbID']")).getText();
    Assert.assertEquals(actualProduct, expectedProduct);
    System.out.println("product added to cart successfully");
    driver.quit();
	}
	
}


