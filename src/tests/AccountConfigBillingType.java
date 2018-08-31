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

public class AccountConfigBillingType extends TestBase {

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
				.startTest("Account configuration Verification Missing Billing Type ");
		acpage.changeUser(data.get("TL"), data.get("Admin"));
		wait(1);
	
		extentTest.log(LogStatus.INFO, "Changed User from TL To Admin");
	

		driver.get(FBConstants.LoginURL);
		wait(1);
		driver.get(FBConstants.ApplicationURL);
		wait(1);
if(isElementPresent_xpath(FBConstants.Admin)){
	
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		extentTest.log(LogStatus.INFO, "Clicking on Admin");
		wait(1);
		driver.get(FBConstants.AccountConfig);
		wait(2);
		report.takeScreenShot();
		extentTest
				.log(LogStatus.INFO, "Navigate to Account Configuration Page");
		

		// Select Account and Revenue and missing billing type
		

		
		acpage.selectAccountName(data.get("AccountName"));
		acpage.selectRevenueType(data.get("RevenueType"));
		wait(3);
		WebElement BillingType = driver.findElement(By
				.xpath(FBConstants.Billing_Type));
		BillingType.click();
		 BillingType = driver.findElement(By
					.xpath(FBConstants.Billing_Type));
		 BillingType.click();
		Select sc = new Select(BillingType);
		
		sc.selectByVisibleText("Select");
		wait(1);
		acpage.clickShowButton();
		wait(2);
		if(isElementPresent_xpath(FBConstants.BillingError)){
		try {
			String expectedBilResult = driver.findElement(
					By.xpath(FBConstants.BillingError)).getText();
			Assert.assertTrue(("Please select a billing type")
					.equalsIgnoreCase(expectedBilResult),
					"Billing Error Message not Appearing");
		} catch (Exception e) {

		}
		
		extentTest
				.log(LogStatus.INFO,
						"Navigate to Account Configuration Screen and Entered Account Name,Revenue Type and Missing Billing Type");
		report.takeScreenShot();

		// select accountName ,Billing and miss revenue
		/*
		wait(2);
		acpage.selectAccountName(data.get("AccountName"));
		acpage.selectBillingType(data.get("BillingType"));
		wait(1);
		WebElement RevenueType = driver.findElement(By
				.xpath(FBConstants.Revenue_Type));
		RevenueType.sendKeys("Select");
		wait(2);
		acpage.clickShowButton();
		wait(2);
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
		wait(1);

		acpage.changeUser(data.get("Admin"), data.get("TL"));
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Changed User From Admin To TL");*/
		}}
	}


	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "AccountConfiguration");
	}
}
