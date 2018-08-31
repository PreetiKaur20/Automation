//Data isalready updated and waiting for approval and message Appears than approve Stats in SDH
//Your entries not yet approved by your SDH for the selected date. So you wil be notable to see the entered numbers and will be not allowed to enter the numbers until it approves.

package tests;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

import pages.ValidateDataEntryPage;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;
import BasePage.NtlgReplace;
import BasePage.TestBase;

import com.relevantcodes.extentreports.LogStatus;

public class RejectRecords extends TestBase {

	TestUtil report = new TestUtil();
	String cureentprevfour = report.seletCurrentDate(-6);
	ValidateDataEntryPage page = new ValidateDataEntryPage();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "EditDataPendingStatus")) {
			throw new SkipException("Runmode set to No");
		}
	}

	

	@Test(dataProvider = "getData", priority = '1')
	public void RejectRecords(Hashtable<String, String> data) throws IOException {
		extentTest = extentReports
				.startTest("TL able to Edit ,Rejected Records BY SDH");
    wait(1);
		driver.findElement(By.xpath(FBConstants.Trasction)).click();
	   
		wait(2);
		// click on Data Entry
		driver.get(FBConstants.Data_Entry);
		extentTest.log(LogStatus.INFO, "Navigating To Data Entry Page");
	
 wait(2);
		WebElement ele = driver.findElement(By.xpath(FBConstants.Calendar));
		page.setDate(ele, cureentprevfour);
		loadwait(2000, By.id("loading"));
		
		page.clickSearchButton();
		loadwait(2000, By.id("loading"));
	
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Clicked on Search Button");
          
		if (!isElementPresent_xpath("//*[@id='ctl00_CphBody_lblPendingMessage']")){
			report.takeScreenShot();
			extentTest.log(LogStatus.INFO, "Verified Able to Edit Data and Saved ");
			loadwait(2000, By.id("loading"));
		page.receiveddata(data.get("received"));

		
		
		page.clickSaveButton();
	
		waitAlert(8000);
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
		}
		
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Clicked on saved Button and alert");
	
String actualstatus=		dataViewEntryStatus();
		Assert.assertTrue(actualstatus.equals("Pending for approval"),
				"Status is Approved Status not in Pending Status");
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Verified Status Should be Pending for approval");
		//Navigate to SDH and approve 
		page.changeUser(data.get("TL"),data.get("SDH"));
	wait(1);
	driver.get(FBConstants.LoginURL);
		//Reject Records
		page.SDHrejection();
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Login TO Application AS SDH and Rejects Records");
		
		wait(2);
		page.changeUser(data.get("SDH"),data.get("TL"));
	wait(3);
		driver.get("http://PreetiK:Happy@123@10.1.2.85/OMH/");
		
wait(2);
		
	
		
		driver.findElement(By.xpath(FBConstants.Trasction)).click();
		
	
		// click on Data Entry
		driver.get(FBConstants.Data_Entry);
		extentTest.log(LogStatus.INFO, "Navigating To Data Entry Page");
	

		WebElement ele1 = driver.findElement(By.xpath(FBConstants.Calendar));
		page.setDate(ele1, cureentprevfour);

		loadwait(2000, By.id("loading"));
		
		page.clickSearchButton();
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Clicked on Search Button");
		loadwait(2000, By.id("loading"));
		if (!isElementPresent_xpath("//*[@id='ctl00_CphBody_lblPendingMessage']")){
			report.takeScreenShot();
			extentTest.log(LogStatus.INFO, "Verified Able to Edit Data and Saved ");
			loadwait(2000, By.id("loading"));
		page.receiveddata(data.get("received"));

		
	
		extentTest.log(LogStatus.INFO, "Able to Update Records");
		report.takeScreenShot();
		wait(1);
		
		}}
	}
	
	


	public String dataViewEntryStatus()
	{driver.get(FBConstants.Data_View_Entry);
		wait(1);

		WebElement datefrom = driver.findElement(By
				.xpath(FBConstants.From_Date));
		WebElement dateTo = driver.findElement(By.xpath(FBConstants.To_Date));
		page.setDate(datefrom, cureentprevfour);
		page.setDate(dateTo, cureentprevfour);
		page.clickSearchButton();
		wait(1);
		String actualstatus = driver.findElement(By.xpath(FBConstants.Status))
				.getText();
		return actualstatus;
	
	}
	

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "EditDataPendingStatus");

	}
}
