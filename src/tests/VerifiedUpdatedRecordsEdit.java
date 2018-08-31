package tests;

import static org.testng.Assert.assertTrue;

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

public class VerifiedUpdatedRecordsEdit extends TestBase {
	ValidateDataEntryPage page = new ValidateDataEntryPage();
	TestUtil report = new TestUtil();
	String currentdate = report.seletCurrentDate(-4);

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "CurrentDayRecord")) {
			throw new SkipException("Runmode set to No");
		}
	}



	@Test(dataProvider = "getData", priority = 1)
	public void VerifiedUpdatedRecordsEdit(Hashtable<String, String> data)
			throws InterruptedException {

		extentTest = extentReports.startTest("Verified Pendind Records Waiting for SDH Approval");
		wait(1);
		driver.get(FBConstants.LoginURL);
		wait(2);
		SearchRecord();
		loadwait(2000, By.id("loading"));
		// Enter dta in Recieved EditBox
		wait(3);
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Enter data and Saved Records");
		page.receiveddata(data.get("received"));
	    wait(3);
		page.clickSaveButton();
		extentTest.log(LogStatus.INFO, "Clicked on Saved Button");
		wait(2);
		
		waitAlert(8000);
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
		}
		SearchRecord();
	
		extentTest.log(LogStatus.INFO, "After Saving ,Enter Same Date ,Unable to Edit Data");
		// Enter data in Recieved EditBox
		try{
			Assert.assertTrue(isElementPresent_xpath("//*[@id='ctl00_CphBody_lblPendingMessage']"),"Not waiting for SDH approval ");
		}catch(Exception e){
			
		}
		report.takeScreenShot();
	}



	public void SearchRecord() {
		wait(1);
		driver.get(FBConstants.LoginURL);
		wait(2);
		ValidateDataEntryPage page = new ValidateDataEntryPage();
		  wait(1);
			report.takeScreenShot();
			extentTest.log(LogStatus.INFO, "Clicked on Transaction");
		  driver.findElement(By.xpath(FBConstants.Trasction)).click(); 
		  
		page.dataEntrySearch();
		WebElement ele = driver.findElement(By.xpath(FBConstants.Calendar));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
		js.executeScript(
				"arguments[0].setAttribute(arguments[1], arguments[2]);", ele,
				"value", currentdate);
		loadwait(2000, By.id("loading"));
		report.takeScreenShot();
		wait(3);
		page.clickSearchButton();
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Entering Date and Clicked on Search Button");
	}


	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "CurrentDayRecord");
	}
}
