//Data isalready updated and waiting for approval and message Appears than approve Stats in SDH
//Your entries not yet approved by your SDH for the selected date. So you wil be notable to see the entered numbers and will be not allowed to enter the numbers until it approves.

package tests;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class PendingStatusVerified extends TestBase {

	TestUtil report = new TestUtil();
	String cureentprevfour = report.seletCurrentDate(-4);
	ValidateDataEntryPage page = new ValidateDataEntryPage();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "PendingStatusVerified")) {
			throw new SkipException("Runmode set to No");
		}}



	@Test(dataProvider = "getData", priority = 1)
	public void PendingStatusVerified(Hashtable<String, String> data)
			throws InterruptedException, IOException {
		
		extentTest = extentReports
				.startTest("Search previous 4 or 5 days record and verified Pendind Status than Login as SDH and Approved and verified Approved Status");
	
		page.dataEntrySearch();
		WebElement ele = driver.findElement(By.xpath(FBConstants.Calendar));
		page.setDate(ele, cureentprevfour);
		report.takeScreenShot();
		page.clickSearchButton();
		extentTest.log(LogStatus.INFO, "Clicked on Search Button");
		wait(3);
		String expectedmsg = "Your entries not yet approved by your SDH for the selected date. So you wil be notable to see the entered numbers and will be not allowed to enter the numbers until it approves.";

		if (isElementPresent_xpath("//*[@id='ctl00_CphBody_lblPendingMessage']")) {
			WebElement pendingmsg = driver.findElement(By
					.xpath("//*[@id='ctl00_CphBody_lblPendingMessage']"));
			if (pendingmsg.getText().equals(expectedmsg)) {
				extentTest.log(LogStatus.INFO,
						"Data Already Updated and Waiting for SDH Approval");
				report.takeScreenShot();

				// Navigate to SDH and Approve the Records

				page.changeUser(data.get("TL"), data.get("SDH"));
				extentTest.log(LogStatus.INFO,
						"Login TO Application AS SDH");
				report.takeScreenShot();
				// Approved Records
				driver.get(FBConstants.Approval);
				WebElement Transaction = driver.findElement(By
						.xpath(FBConstants.Trasction));
				wait(1);
				Transaction.click();
				// navigate to approval
				driver.get(FBConstants.Approval);
				wait(1);
				// Select number of recoords and filter with TL and Approve

				WebElement records = driver.findElement(By
						.xpath(FBConstants.Record_List));
				Select rec = new Select(records);
				rec.selectByIndex(3);

				wait(1);
				driver.findElement(By.xpath("//*[@id='selectAll']")).click();
				driver.findElement(By.xpath(FBConstants.TL_Filter)).click();
				driver.findElement(By.xpath(FBConstants.Approved_Button)).click();
				driver.switchTo().alert().accept();
				extentTest.log(LogStatus.INFO,
						"Records Approved BY SDH");
				report.takeScreenShot();
			
				page.changeUser(data.get("SDH"), data.get("TL"));
				wait(1);
				driver.get(FBConstants.Data_View_Entry);
				driver.get("http://10.1.2.85/OMH/Home.aspx");
				driver.get(FBConstants.Data_View_Entry);
			 wait(2);
				extentTest.log(LogStatus.INFO,
						"Navigate To Data Entry View Page");
				report.takeScreenShot();
				WebElement datefrom = driver.findElement(By
						.xpath(FBConstants.From_Date));
				WebElement dateTo = driver.findElement(By.xpath(FBConstants.To_Date));
				page.setDate(datefrom, cureentprevfour);
				page.setDate(dateTo, cureentprevfour);
				page.clickSearchButton();
				extentTest.log(LogStatus.INFO,
						" Dates Entered and clicked on Search Button");
				report.takeScreenShot();
				wait(3);
				try{
				String actualstatus = driver.findElement(By.xpath(FBConstants.Status))
						.getText();
				Assert.assertTrue(actualstatus.equals("Approved"),
						"Status is in Pending not Approved");
				extentTest.log(LogStatus.INFO,
						"Login TO Application As TL and Verified Status Should be Approved");
				report.takeScreenShot();
				}catch(Exception e){}
				
			}}
		}

	

	



	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "PendingStatusVerified");

	}
}
