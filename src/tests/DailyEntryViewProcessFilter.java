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

public class DailyEntryViewProcessFilter extends TestBase {

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
	public void DailyEntryViewProcessFilter(Hashtable<String, String> data) throws IOException {
		extentTest = extentReports
				.startTest("Searching Records with Process and Client");
		driver.get(FBConstants.LoginURL);
		wait(2);
		try{
		driver.findElement(By.xpath(FBConstants.Trasction)).click();
		extentTest.log(LogStatus.INFO, "clicked on Transaction");
		report.takeScreenShot();
		driver.get(FBConstants.Data_View_Entry);
		extentTest.log(LogStatus.INFO, "Navigating Daily View entry");
wait(2);
		WebElement datefrom = driver.findElement(By
				.xpath(FBConstants.From_Date));
		WebElement dateTo = driver.findElement(By.xpath(FBConstants.To_Date));
		page.setDate(datefrom, cureentprevfour);
		page.setDate(dateTo, cureentprevfour);
WebElement client=		driver.findElement(By.xpath(FBConstants.ClientDrop));

extentTest.log(LogStatus.INFO, "Selecting  Client");
Select sc = new Select(client);
sc.selectByIndex(1);
report.takeScreenShot();

WebElement process=		driver.findElement(By.xpath(FBConstants.Processdrop));
extentTest.log(LogStatus.INFO, "Selecting Process and Search");
sc = new Select(process);
sc.selectByIndex(1);
page.clickSearchButton();}catch(Exception e){}



		}

	






	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "EditDataPendingStatus");

	}
}
