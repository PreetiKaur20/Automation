package tests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

import pages.AccountConfigurationPage;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;
import BasePage.TestBase;

import com.relevantcodes.extentreports.LogStatus;

public class AdminConfigCancel extends TestBase {

	AccountConfigurationPage acpage= new AccountConfigurationPage();
	TestUtil report = new TestUtil();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "AccountConfiguration")) {
			throw new SkipException("Runmode set to No");
		}
	}



	@Test(dataProvider = "getData", priority = 1)
	public void AdminConfigCancel(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Account configuration");
		

		driver.get(FBConstants.LoginURL);
		wait(2);
		driver.get(FBConstants.ApplicationURL);
		if(isElementPresent_xpath(FBConstants.Admin)){
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		wait(1);
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		driver.get(FBConstants.AccountConfig);
		wait(2);

		extentTest.log(LogStatus.INFO, "Navigate to Account Configuration Page");
		
		//select Account Type Billing type and Revenue Type and click on Show Button
	WebElement AccountName = driver.findElement(By.xpath(FBConstants.Account_Name));
	AccountName.sendKeys(data.get("AccountName"));
	WebElement BillingType = driver.findElement(By.xpath(FBConstants.Billing_Type));
	BillingType.sendKeys(data.get("BillingType"));
	WebElement RevenueType = driver.findElement(By.xpath(FBConstants.Revenue_Type));
	RevenueType.sendKeys(data.get("RevenueType"));

	
	wait(3);
	driver.findElement(By.xpath(FBConstants.Show_button)).click();
	report.takeScreenShot();
	extentTest.log(LogStatus.INFO, "Click on Show Button");
	
	wait(2);
	//Click on Cancel Button
	driver.findElement(By.xpath(FBConstants.CancelButton)).click();

	report.takeScreenShot();
	extentTest.log(LogStatus.INFO, "Click on Cancel Button");



report.takeScreenShot();
extentTest.log(LogStatus.INFO, "Account,Billing Type and Revenue Type select to Default");
	


		}
}





	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "AccountConfiguration");
	}
}




