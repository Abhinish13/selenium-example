

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

//[root@a42c68e72450docker pull selenium/standalone-chrome

public class SimpleTest {

	@Test
	public void test1() throws InterruptedException {
		
		WebDriver driver;
		boolean headless = true;
		
		String strOS = System.getProperty("os.name");
		System.out.println("OS : " + strOS);
		
		if (strOS.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
		}else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver");
		}

		ChromeOptions options = new ChromeOptions();
		
		if(headless) {
			options.addArguments("--window-size=1920,1200");
			options.addArguments("--headless");
			options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
			options.addArguments("start-maximized"); // open Browser in maximized mode
			options.addArguments("disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			options.addArguments("--disable-gpu"); // applicable to windows os only
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			options.addArguments("--no-sandbox"); // Bypass OS security model
		}

		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		String strURL = "https://www.google.co.in";
		
		System.out.println("-----Started");
		System.out.println("-----Loading page - " + strURL);
		driver.get(strURL);
		
		String strSearchFieldTitle = driver.findElement(By.xpath("//input[@name='q']")).getAttribute("title");
		System.out.println("-----Search Field Title - " + strURL);
		
		Assert.assertEquals(strSearchFieldTitle, "Search");
		System.out.println("-----Asserted the Search Field Title");
		
		driver.quit();
		System.out.println("-----Closing page - " + strURL);
	}
}
