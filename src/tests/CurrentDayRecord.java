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

public class CurrentDayRecord extends TestBase {
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

		extentTest = extentReports.startTest("Verified approved status for Current day");
		
		//Change User to TL
		WebElement username = driver.findElement(By.xpath(FBConstants.Username));

		
		driver.get(FBConstants.LoginURL);
		wait(2);
	
		  driver.findElement(By.xpath(FBConstants.Trasction)).click(); 
		page.dataEntrySearch();
	//	int date = Integer.parseInt(data.get("Date"));
		String currentdate = report.seletCurrentDate(0);
		if(isElementPresent_xpath(FBConstants.Calendar)){
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
		extentTest.log(LogStatus.INFO, "Clicked on Search Button");
		loadwait(2000, By.id("loading"));
		// Enter dta in Recieved EditBox
		wait(3);
		page.receiveddata(data.get("received"));
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Entered Data");

		page.clickSaveButton();
		
		wait(3);
		waitAlert(8000);
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
		}
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Clicked on Save Button");
		// Navigate to Data View Entry
		wait(3);
		driver.findElement(By.xpath(FBConstants.Trasction)).click();
		wait(1);
		driver.get(FBConstants.Data_View_Entry);
		extentTest.log(LogStatus.INFO, "Navigate to Data View Entry");
		wait(2);
		String status = driver
				.findElement(
						By.xpath("//*[@id='ctl00_CphBody_GrdMonthlyResults']/tbody/tr[1]/td[8]"))
				.getText();

		Assert.assertTrue(status.equals("Approved"), "Status Not matching");
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Status Approved for Current Date");
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Navigate to Daily View Entry Page and Verified Status");
	
}
	}



	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "CurrentDayRecord");
	}
}
