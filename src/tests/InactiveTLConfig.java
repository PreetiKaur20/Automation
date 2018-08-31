package tests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class InactiveTLConfig extends TestBase {

	AccountConfigurationPage acpage = new AccountConfigurationPage();
	TestUtil report = new TestUtil();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "TLConfiguration")) {
			throw new SkipException("Runmode set to No");
		}
	}


	@Test(dataProvider = "getData", priority = 1)
	public void InactiveTLConfig(Hashtable<String, String> data)
			throws InterruptedException, IOException {

	
		extentTest = extentReports
				.startTest("Verify InActive Status on TL Configuration Page");
	
	
	   
		driver.get(FBConstants.ApplicationURL);
		wait(1);
		driver.get(FBConstants.ApplicationURL);
	
	    System.out.println("InACTIVE");
	    wait(2);
		if(isElementPresent_xpath(FBConstants.Admin)){
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		wait(1);
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		driver.findElement(By.xpath(FBConstants.TLConfig)).click();

		extentTest.log(LogStatus.INFO, "Click on Admin , Navigate to TL Configuration Page");
		
		wait(1);
		

		// Selecting Vertical
	
		WebElement showbutton =driver.findElement(By.xpath(FBConstants.Show_button));
		
		extentTest.log(LogStatus.INFO, "Able to Clic Show Button");
		report.takeScreenShot();

		showbutton.click();
		//click on status
		driver.findElement(By.xpath(FBConstants.StatusFilter)).click();
		driver.findElement(By.xpath(FBConstants.StatusFilter)).click();
	String status = driver.findElement(By.xpath("//*[@id='ctl00_CphBody_grdTLMapping']/tbody/tr[1]/td[10]")).getText();
		System.out.println(status);
		Assert.assertTrue(status.equals("InActive"), "Status should not be Active");
		extentTest.log(LogStatus.INFO, "Veified Inactive Status");
		report.takeScreenShot();
		}



		





	}
  
	
		
	
		
	
		
		

	
		
		
	
		







	@AfterMethod
	public void aftermethod() {
		System.out.println("after method");

		extentReports.endTest(extentTest);

		extentReports.flush();
		extentReports.close();

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "TLConfiguration");
	}
}
