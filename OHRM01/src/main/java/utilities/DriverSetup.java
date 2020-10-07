package utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup
{
	static WebDriver driver;

	public static WebDriver getWebDriver(String browsername) 
	{
		if (browsername.equals("CHROME")) {
			driver = setChromeDriver();
		} 
		else if (browsername.equals("FIREFOX"))
		{
			driver = setFirefoxDriver();
		} 
		else {
			System.out.println("Invalid Input");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};	
		return  driver;
	}

	public static WebDriver setChromeDriver() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		return driver;
	}

	public static WebDriver setFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "F:\\OHRM01\\drivers\\geckodriver.exe");
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		//firefoxBinary.addCommandLineOptions("--headless");
		FirefoxProfile profile=new FirefoxProfile();
		profile.setPreference("marionette.logging", "TRACE");
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setBinary(firefoxBinary);
		firefoxOptions.setProfile(profile);
		driver=new FirefoxDriver(firefoxOptions);
		return driver;
	}
}