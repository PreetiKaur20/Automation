
//Your entries not yet approved by your SDH for the selected date. So you wil be notable to see the entered numbers and will be not allowed to enter the numbers until it approves.

package tests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

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

public class VerifyReportsTL extends TestBase {

	TestUtil report = new TestUtil();
	String cureentprevfour = report.seletCurrentDate(-1);
	ValidateDataEntryPage page = new ValidateDataEntryPage();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "EditDataPendingStatus")) {
			throw new SkipException("Runmode set to No");
		}
	}



	@Test(dataProvider = "getData", priority = '1')
	public void VerifyReportsTL(Hashtable<String, String> data) throws IOException {
		extentTest = extentReports
				.startTest("Logged in as TL, Navigate to Production Report, verified Show Report button is Enable and Verified Reports");
		wait(1);
		driver.get(FBConstants.LoginURL);
		extentTest.log(LogStatus.INFO, "Navigate to Applicatiion");
		driver.get(FBConstants.LoginURL);
		wait(2);
	
		if(isElementPresent_xpath(FBConstants.Reports)){
			System.out.println("Clicked on Report");
		driver.findElement(By.xpath(FBConstants.Reports)).click();
		extentTest.log(LogStatus.INFO, "Clicked on Report");
		report.takeScreenShot();
		wait(1);
		driver.findElement(By.xpath(FBConstants.Production_Report)).click();
		report.takeScreenShot();
		}
			
		
	


if(isElementPresent_xpath(FBConstants.Show_Report)){
	WebElement showReport=		driver.findElement(By.xpath(FBConstants.Show_Report));
// call the executeScript method
//js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", showReport);

 showReport.click();
	extentTest.log(LogStatus.INFO, "Clicked on Production and Verified Report");
	
}










		}

	






	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "EditDataPendingStatus");

	}
}
