package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ScreenShot;

public class LoginPage {

	WebDriver driver;

	@FindBy (xpath="//input[@id='txtUsername']")
	public WebElement txtUsername;

	@FindBy (xpath="//input[@id='txtPassword']")
	public WebElement txtPassword;

	@FindBy (xpath="//input[@id='btnLogin']")
	public WebElement btnLogin;

	@FindBy (xpath="//h1[contains(text(),'Dashboard')]")
	public WebElement titleofPageafterlogin;

	@FindBy (linkText="Forgot your password?")
	public WebElement txtofsamepage;

	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public String loginwithvaliddata()
	{
		enterUsername();
		enterPassword();
		clickbtnLogin();

		ScreenShot s=new ScreenShot(driver);
		s.captureScreenShot();

		return titleofPageafterlogin.getText();

	}

	private void clickbtnLogin() {
		btnLogin.click();
	}

	private void enterPassword() {
		txtPassword.clear();
		txtPassword.sendKeys("admin123");
	}

	private void enterUsername() {
		txtUsername.clear();
		txtUsername.sendKeys("Admin");
	}

	public String loginwithinvaliddata(String name, String pwd) {
		txtUsername.clear();
		txtUsername.sendKeys(name);
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
		btnLogin.click();

		ScreenShot s=new ScreenShot(driver);
		s.captureScreenShot();
		return txtofsamepage.getText();
	}
}