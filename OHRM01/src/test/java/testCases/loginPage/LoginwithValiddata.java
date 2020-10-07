package testCases.loginPage;

import utilities.DriverSetup;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginwithValiddata {

	WebDriver driver= null;

	@Test
	public void verifyloginwithvaliddata()
	{
		LoginPage sp= new LoginPage(driver);  

		String expectedvalue="Dashboard";
		String actualvalue=sp.loginwithvaliddata();
		Assert.assertEquals(actualvalue, expectedvalue,"Test case failed");
	}

	@BeforeClass(alwaysRun = true)
	public void beforem()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Properties prop=null;
		try {
			prop = utilities.ReadPropertiesfile.readPropertiesFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		driver=DriverSetup.getWebDriver(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));

		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass(alwaysRun = true)
	public void afterm()
	{
		driver.close();
	}
}