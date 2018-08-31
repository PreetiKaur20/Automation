package tests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
import pages.ValidateDataEntryPage;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;
import BasePage.TestBase;

import com.relevantcodes.extentreports.LogStatus;

public class AccountConfiguration extends TestBase {
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
	public void AccountConfiguration(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Account Aconfiguration");
	
	
		driver.get(FBConstants.ApplicationURL);
		driver.get(FBConstants.ApplicationURL);
		wait(2);
		if(isElementPresent_xpath(FBConstants.Admin)){
		
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		driver.get(FBConstants.AccountConfig);
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Navigate to Account Configuration Page");
		wait(2);
		//select Account Type Billing type and Revenue Type and click on Show Button
		if(isElementPresent_xpath(FBConstants.Account_Name)){
	WebElement AccountName = driver.findElement(By.xpath(FBConstants.Account_Name));
	AccountName.sendKeys(data.get("AccountName"));
	WebElement BillingType = driver.findElement(By.xpath(FBConstants.Billing_Type));
	BillingType.sendKeys(data.get("BillingType"));
	WebElement RevenueType = driver.findElement(By.xpath(FBConstants.Revenue_Type));
	RevenueType.sendKeys(data.get("RevenueType"));
	report.takeScreenShot();
	extentTest.log(LogStatus.INFO, "Select Value of Account Name, Billing Type,Revenue Type");
		
	driver.findElement(By.xpath(FBConstants.Show_button)).click();
	report.takeScreenShot();
	extentTest.log(LogStatus.INFO, "Click on Show Button");
	driver.findElement(By.xpath(FBConstants.unitRate)).clear();
	driver.findElement(By.xpath(FBConstants.unitRate)).sendKeys(data.get("UnitRate"));
	//Select Billing Type
	 BillingType = driver.findElement(By.xpath(FBConstants.Billing_Type));
		BillingType.sendKeys(data.get("BillingType"));
		 RevenueType = driver.findElement(By.xpath(FBConstants.Revenue_Type));
		RevenueType.sendKeys(data.get("RevenueType"));
		
	driver.findElement(By.xpath(FBConstants.Submit)).click();

	extentTest.log(LogStatus.INFO, "Entered Unit Rate and click on Submit Button");
		}
	WebDriverWait wait = new WebDriverWait(driver, 300 /*timeout in seconds*/);
	if(wait.until(ExpectedConditions.alertIsPresent())==null)
	    System.out.println("alert was not present");
	else
	{ System.out.println("alert was present");
	driver.switchTo().alert().accept();}

 wait(2);
 driver.findElement(By.xpath(FBConstants.SearchBox)).clear();
	driver.findElement(By.xpath(FBConstants.SearchBox)).sendKeys(data.get("AccountName"));
	report.takeScreenShot();
	extentTest.log(LogStatus.INFO, "Search Records with account Name");
	
	
	//Verify records with Unit Rate
List<WebElement>allacount= driver.findElements(By.xpath("//*[@id='ctl00_CphBody_GrdUnitRateNotNull']/tbody/tr/td[2]"));
List<WebElement>unitrate = driver.findElements(By.xpath("//*[@id='ctl00_CphBody_GrdUnitRateNotNull']/tbody/tr/td[3]"));
for(int i=0;i<=allacount.size()-1;i++)
{
	System.out.println(allacount.get(i).getText());
	if(allacount.get(i).getText().equals(data.get("AccountName")))
	{
		Assert.assertTrue(unitrate.get(i).getText().contains(data.get("UnitRate")), "Records Not Updated in Configuration Table");
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Records Updated in Configuration Table");
	}
}

		
		}

	}




	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "AccountConfiguration");
	}
}



