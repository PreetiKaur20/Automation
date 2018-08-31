package tests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AccountConfigurationPage;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.TestBase;

public class AccountConfigRevenue extends TestBase {

	AccountConfigurationPage acpage = new AccountConfigurationPage();
	TestUtil report = new TestUtil();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "AccountConfiguration")) {
			throw new SkipException("Runmode set to No");
		}
	}



	@Test(dataProvider = "getData", priority = 1)
	public void AccountConfigScreen(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports
				.startTest("Account configuration Verification Missing Revenvue Type Popup");
	
		
		acpage.changeUser(data.get("TL"), data.get("Admin"));
		wait(1);
	
		extentTest.log(LogStatus.INFO, "Changed User from TL To Admin");
		report.takeScreenShot();
		wait(1);
		driver.get(FBConstants.LoginURL);

		driver.get(FBConstants.ApplicationURL);
		wait(2);
if(isElementPresent_xpath(FBConstants.Admin)){
	wait(2);
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		extentTest.log(LogStatus.INFO, "Clicking on Admin");
		wait(1);
		driver.get(FBConstants.AccountConfig);
		wait(2);
	
		extentTest
				.log(LogStatus.INFO, "Navigate to Account Configuration Page");
		

		

		// select accountName ,Billing and miss revenue
	
		wait(2);
		acpage.selectAccountName(data.get("AccountName"));
		acpage.selectBillingType(data.get("BillingType"));
		wait(3);
		WebElement RevenueType = driver.findElement(By
				.xpath(FBConstants.Revenue_Type));
		RevenueType.click();
		RevenueType.sendKeys("Select");
		RevenueType.click();
		RevenueType.sendKeys("Select");
		wait(3);
		acpage.clickShowButton();
		wait(2);
		if(isElementPresent_xpath(FBConstants.RevenueError)){
		try {
			String expectRevResult = driver.findElement(
					By.xpath(FBConstants.RevenueError)).getText();
			Assert.assertTrue(("Please select a revenue type")
					.equalsIgnoreCase(expectRevResult),
					"Revenue Error Message not Appearing");
		} catch (Exception e) {
		}

		report.takeScreenShot();
		extentTest
				.log(LogStatus.INFO,
						"Navigate to Account Configuration Screen and Entered Account Name,Billig Type and Missing RevenueType");
		report.takeScreenShot();

		
	}}
	}


	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "AccountConfiguration");
	}
}
