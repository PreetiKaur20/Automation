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

public class dailyEntryViewEditLink extends TestBase {

	TestUtil report = new TestUtil();
	String cureentprevfour = report.seletCurrentDate(0);
	ValidateDataEntryPage page = new ValidateDataEntryPage();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "EditDataPendingStatus")) {
			throw new SkipException("Runmode set to No");
		}
	}


	@Test(dataProvider = "getData", priority = '1')
	public void dailyEntryViewEditLink(Hashtable<String, String> data) throws IOException {
		extentTest = extentReports
				.startTest("Verified Edit Link on Daily View Entry Page");
	
		driver.get(FBConstants.LoginURL);
		wait(1);
		driver.get(FBConstants.LoginURL);
		wait(1);
		driver.findElement(By.xpath(FBConstants.Trasction)).click();
		wait(1);
		driver.findElement(By.xpath(FBConstants.Trasction)).click(); 
		driver.get(FBConstants.Data_View_Entry);
		extentTest.log(LogStatus.INFO, "Clicked on Transaction and Navigating to Daily View Entry Page and Search Records ");
		report.takeScreenShot();
	
		


driver.findElement(By.xpath(FBConstants.EditLink)).click();
extentTest.log(LogStatus.INFO, "Click on Edit Link");
loadwait(9000, By.id("loading"));
wait(2);

page.clickSaveButton();

extentTest.log(LogStatus.INFO, "Clicked on Saved Button and able to Edit Record");


		}

	





	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "EditDataPendingStatus");

	}
}
