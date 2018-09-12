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

public class EditDataPendingStatus extends TestBase {

	TestUtil report = new TestUtil();
	String cureentprevfour = report.seletCurrentDate(-4);
	ValidateDataEntryPage page = new ValidateDataEntryPage();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "EditDataPendingStatus")) {
			throw new SkipException("Runmode set to No");
		}
	}

	@Test(dataProvider = "getData", priority = '1')
	public void EditDataPendingStatus(Hashtable<String, String> data)
			throws IOException {
		extentTest = extentReports
				.startTest("Updating Data in Daily Entry Page BY TL  ,Approved by SDH and verified status changed to Approved from Pending from Approval");
		
		page.dataEntrySearch();
		WebElement ele = driver.findElement(By.xpath(FBConstants.Calendar));
		page.setDate(ele, cureentprevfour);
		report.takeScreenShot();
		page.clickSearchButton();
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Clicked on Search Button");

		if (!isElementPresent_xpath("//*[@id='ctl00_CphBody_lblPendingMessage']")) {
			report.takeScreenShot();
			extentTest.log(LogStatus.INFO,
					"Verified Able to Edit Data and Saved ");
			loadwait(2000, By.id("loading"));
			page.receiveddata(data.get("received"));

			page.clickSaveButton();

			wait(2);
			waitAlert(8000);
			try {
				if (isAlertPresent()) {
					driver.switchTo().alert().accept();
				}
			} catch (Exception e) {
			}
			report.takeScreenShot();
			extentTest.log(LogStatus.INFO, "Clicked on saved Button and alert");

			String actualstatus = dataViewEntryStatus();
			Assert.assertTrue(actualstatus.equals("Pending for approval"),
					"Status is Approved Status not in Pending Status");
			report.takeScreenShot();
			extentTest.log(LogStatus.INFO,
					"Verified Status Should be Pending for approval");
			// Navigate to SDH and approve
			page.changeUser(data.get("TL"), data.get("SDH"));

			driver.get(FBConstants.LoginURL);
			// Approved Records
			page.SDHApproval();
			report.takeScreenShot();
			extentTest.log(LogStatus.INFO,
					"Login TO Application AS SDH and Approved Records");
			wait(1);
			page.changeUser(data.get("SDH"), data.get("TL"));
			wait(2);
			driver.get(FBConstants.LoginURL);
			wait(2);
			actualstatus = dataViewEntryStatus();
			Assert.assertTrue(actualstatus.equals("Approved"),
					"Status is in Pending not Approved");
			
			extentTest.log(LogStatus.INFO,
					"Login TO Application AS TL and Verified Approved Status");
		}
	}

	public String dataViewEntryStatus() {
		driver.get(FBConstants.Data_View_Entry);
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
