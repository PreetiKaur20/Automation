package tests;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
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

import pages.AccountConfigurationPage;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;
import BasePage.TestBase;

import com.relevantcodes.extentreports.LogStatus;

public class PleaseSelectAccBilRev  extends TestBase{


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
	public void PleaseSelectAccBilRev(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Account Configuration Verification");
		
		driver.get(FBConstants.ApplicationURL);
		if(isElementPresent_xpath(FBConstants.Admin)){
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		wait(1);
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		driver.get(FBConstants.AccountConfig);
		report.takeScreenShot();
    extentTest.log(LogStatus.INFO, "Navigate to Account Configuration Page");
    wait(2);
    acpage.clickShowButton();
	try{
String expectedAcResult=driver.findElement(By.xpath(FBConstants.AccountError)).getText();
Assert.assertTrue(("Please select an account").equalsIgnoreCase(expectedAcResult), "Account Error Message not Appearing");}catch(Exception e){

}
	try{
		String expectedBilResult=driver.findElement(By.xpath(FBConstants.BillingError)).getText();
		Assert.assertTrue(("Please select a billing type").equalsIgnoreCase(expectedBilResult), "Billing Error Message not Appearing");}
		catch(Exception e){
			
		}
	
	try{
		String expectRevResult=driver.findElement(By.xpath(FBConstants.RevenueError)).getText();
		Assert.assertTrue(("Please select a revenue type").equalsIgnoreCase(expectRevResult), "Revenue Error Message not Appearing");}
		catch(Exception e){}
			

report.takeScreenShot();
extentTest.log(LogStatus.INFO, "Navigate to Account Configuration Screen and Missing account name, Billing Type, Revenue Type");
		}
	}




	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "AccountConfiguration");
	}
}




