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

public class SDHAPPROVAL extends TestBase {

	TestUtil report = new TestUtil();
	String cureentprevfour = report.seletCurrentDate(-4);
	ValidateDataEntryPage page = new ValidateDataEntryPage();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "SDHAPPROVAL")) {
			throw new SkipException("Runmode set to No");
		}}


	@Test(dataProvider = "getData", priority = 1)
	public void SDHAPPROVALVERIFICATION(Hashtable<String, String> data)
			throws InterruptedException, IOException {
		
		extentTest = extentReports
				.startTest("SDH APPROVAL");

		
wait(2);
				// Navigate to SDH and Approve the Records

				page.changeUser(data.get("TL"), data.get("SDH"));
				driver.get(FBConstants.LoginURL);
				wait(2);
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

				
				wait(1);
				
				if(isElementPresent_xpath("//*[@id='selectAll']")){
					WebElement records = driver.findElement(By
							.xpath(FBConstants.Record_List));
					Select rec = new Select(records);
					rec.selectByIndex(3);

				driver.findElement(By.xpath("//*[@id='selectAll']")).click();
				driver.findElement(By.xpath(FBConstants.TL_Filter)).click();
				driver.findElement(By.xpath(FBConstants.Approved_Button)).click();
				driver.switchTo().alert().accept();
				extentTest.log(LogStatus.INFO,
						"Records Approved BY SDH");
				}
			
				page.changeUser(data.get("SDH"), data.get("TL"));
		}

	

	


	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "SDHAPPROVAL");

	}
}
