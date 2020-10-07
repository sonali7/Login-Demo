package testCases.loginPage;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.DriverSetup;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Loginwithinvaliddata {
	WebDriver driver=null;

	@SuppressWarnings("resource")
	@DataProvider
	public Object[][] getData() throws IOException {
		//Rows - Number of times your test has to be repeated.
		//Columns - Number of parameters in test data.

		FileInputStream fileInputStream = new FileInputStream("F:\\OHRM01\\resources\\invalidlogindata.xls");

		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet sheet= workbook.getSheetAt(0);

		int rowCount= sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) {
			HSSFRow row = sheet.getRow(i);
			try {
				HSSFCell name = row.getCell(0);
				if (name == null)
					data[i][0] = "";
				else {
					name.setCellType(CellType.STRING);
					data[i][0] = name.getStringCellValue();
				}
			}
			catch(Exception e)
			{
				data[i][0] = "";
			}

			try {
				HSSFCell pwd = row.getCell(1);
				if (pwd == null)
					data[i][1] = "";
				else {
					pwd.setCellType(CellType.STRING);
					data[i][1] = pwd.getStringCellValue();
				}
			}
			catch(Exception e)
			{
				data[i][1] = "";
			}
		}

		return data;
	}
	
	@Test(dataProvider="getData")
	public void LoginTest(String name, String pwd)
	{
		LoginPage fp=new LoginPage(driver);
		String actual=fp.loginwithinvaliddata(name,pwd);

		String expected="Forgot your password?";

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(actual, expected,"Test case failed");
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