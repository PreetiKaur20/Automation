//Verify Status Remark Balance fields

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

public class VerifyRemarkBalnceSatuseField extends TestBase {

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
	public void VerifyRemarkBalnceSatuseField(Hashtable<String, String> data) throws IOException {
		extentTest = extentReports
				.startTest("To verify Status and remarks fields will be come after Balance in View Daily entry");

	
		extentTest.log(LogStatus.INFO, "Navigating to Daily View Entry Page and Verified Status ,Balance and Remark");
		wait(2);
		driver.get(FBConstants.Data_View_Entry);
		if(isElementPresent_xpath(FBConstants.StatisHeader)){
String status = driver.findElement(By.xpath(FBConstants.StatisHeader)).getText();
String Balance = driver.findElement(By.xpath(FBConstants.BalanceHeader)).getText();
String remark = driver.findElement(By.xpath(FBConstants.RemarkHeader)).getText();
System.out.println(status);
System.out.println(Balance);
System.out.println(remark);
		Assert.assertTrue(status.equals("Status"));
		Assert.assertTrue(Balance.equals("Balance"));
		Assert.assertTrue(remark.equals("Remarks"));
		}
	
		extentTest.log(LogStatus.INFO, "Verified Status ,Balance and Remark");
		report.takeScreenShot();
		
		
		
	
	}
	

	




	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "EditDataPendingStatus");

	}
}
