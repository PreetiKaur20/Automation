package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.LogStatus;

import util.FBConstants;
import util.TestUtil;
import BasePage.TestBase;



public class LoginPage extends TestBase{
	
	
	@FindBy(xpath = FBConstants.Login)
	public WebElement login;
	@FindBy(xpath = FBConstants.LOGIN_USERNAME)
	public WebElement email;
	@FindBy(xpath = FBConstants.LOGIN_PASSWORD)
	public WebElement paswd;
	@FindBy(xpath = FBConstants.Select_Client)
	public WebElement client;
	@FindBy(xpath = FBConstants.login_button)
	public WebElement loginbutton;
	@FindBy(xpath = FBConstants.LogOut)
	public WebElement logout;
	
	 TestUtil report = new TestUtil();
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 
  public void dologin(String username,String password) throws InterruptedException{
	  extentTest = extentReports.startTest("Login to Asper Application");
	  
	  
	  extentTest.log(LogStatus.INFO, "Login with Username" + username + "password "
				+ password);
		//driver.get(FBConstants.Loginlink);
		email.sendKeys(username);
		paswd.sendKeys(password);
        report.takeScreenShot();


js.executeScript("arguments[0].click();", loginbutton);
	  
	  extentTest.log(LogStatus.INFO,"Logged in");
	  report.takeScreenShot();
	  wait(1);
	  client.click();
  }


  public void doLogout()
  {	  extentTest = extentReports.startTest("LogOut to Asper Application");
  extentTest.log(LogStatus.PASS,"Clicked on Logout Button");
	  js.executeScript("arguments[0].click();", logout);
	  extentTest.log(LogStatus.PASS, "Logout Successfully");
	  report.takeScreenShot();
	  
  }
  
  
}
