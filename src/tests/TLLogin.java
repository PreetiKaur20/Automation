package tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.tools.ant.taskdefs.Exit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.ValidateDataEntryPage;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.TestBase;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;

public class TLLogin extends TestBase {
	ValidateDataEntryPage page = new ValidateDataEntryPage();
	TestUtil report = new TestUtil();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "CurrentDayRecord")) {
			throw new SkipException("Runmode set to No");
		}
	}


	@Test(dataProvider = "getData", priority = 1)
	public void CurrentDayRecord(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Login TO OMH Application with TL Role");
		
		//Change User to TL
		WebElement username = driver.findElement(By.xpath(FBConstants.Username));
		String User = username.getText();
		wait(1);
		System.out.println(User);
		if(User.equals(FBConstants.OMUser))
		{
			page.changeUser(data.get("OM"), data.get("TL"));
		}
		if(User.equals(FBConstants.AdminUser))
		{
			page.changeUser(data.get("Admin"), data.get("TL"));
		}
		if(User.equals(FBConstants.SDHUser))
		{
			page.changeUser(data.get("SDH"), data.get("TL"));
		}
		
		
		
	 wait(2);
	 driver.get(FBConstants.LoginURL);
	 report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Logged in Successfully as TL");
	
	
		 
	

	}
	


	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "CurrentDayRecord");
	}
}
