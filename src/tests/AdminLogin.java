package tests;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AccountConfigurationPage;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;
import BasePage.TestBase;

import com.relevantcodes.extentreports.LogStatus;

public class AdminLogin  extends TestBase{


	AccountConfigurationPage acpage= new AccountConfigurationPage();
	TestUtil report = new TestUtil();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "AccountConfiguration")) {
			throw new SkipException("Runmode set to No");
		}
	}



	@Test(dataProvider = "getData", priority = 1)
	public void PleaseSelectAccBilRev(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Admin Login");
		
		
		wait(2);
		//Change User to Admin
		WebElement username = driver.findElement(By.xpath(FBConstants.Username));
		String User = username.getText();
		wait(1);
		System.out.println(User);
		if(User.equals(FBConstants.OMUser))
		{
			acpage.changeUser(data.get("OM"), data.get("Admin"));
		}
		if(User.equals(FBConstants.TLUser))
		{
			acpage.changeUser(data.get("TL"), data.get("Admin"));
		}
		if(User.equals(FBConstants.SDHUser))
		{
			acpage.changeUser(data.get("SDH"), data.get("Admin"));
		}
		
		
		extentTest.log(LogStatus.INFO, "Login As Admin");
		report.takeScreenShot();
	
		driver.get(FBConstants.LoginURL);
		
	}




	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "AccountConfiguration");
	}
}




