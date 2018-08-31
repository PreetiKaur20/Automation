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

public class TLConfigurationScreen extends TestBase {

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
	public void TLConfigurationScreen(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Should able to view all the TL configured details in Grid");
		driver.get(FBConstants.LoginURL);
	wait(2);
	driver.get(FBConstants.ApplicationURL);
	wait(2);
	if(isElementPresent_xpath(FBConstants.Admin)){
	driver.findElement(By.xpath(FBConstants.Admin)).click();
	wait(1);
	driver.findElement(By.xpath(FBConstants.TLConfig)).click();
	
	extentTest
			.log(LogStatus.INFO, "Navigate to TL Configuration Page");
		

		// Selecting Vertical
	
   if(isElementPresent_xpath(FBConstants.VerticalSearch)){
	WebElement 	vertical = driver.findElement(By.xpath(FBConstants.VerticalSearch));
	vertical.sendKeys("RCM");
   }
	WebElement showbutton =driver.findElement(By.xpath(FBConstants.Show_button));
	report.takeScreenShot();
	extentTest
			.log(LogStatus.INFO, "Select Records and click on Show Button ");

WebDriverWait wait = new WebDriverWait(driver,40);
wait.until(ExpectedConditions.elementToBeClickable(showbutton));
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Selecting Vertical");
		//Click on Show Button
		showbutton.click();
		//Verify Filter Records
	wait(1);
		try{
	WebElement records =driver.findElement(By.xpath("//*[@id='ctl00_CphBody_dvTLGrid']"));
	Assert.assertTrue(isElementPresent_xpath("//*[@id='ctl00_CphBody_dvTLGrid']"), "Records not found");
	report.takeScreenShot();
	extentTest
			.log(LogStatus.INFO, "Verify Records exist");}catch(Exception e){}
		
	
	


	}
  
	
		
	
		
	
		
		

	
		
		
	
		

	}

	public boolean retryclicking(String xpath,String data){
	boolean result =false;
	int i=0;
	while(i<=1)
	{
		try{
			 wait(2);
			 
		WebElement ele = driver.findElement(By.xpath(xpath));
		
	Select	sc = new Select(ele);
	ele = driver.findElement(By.xpath(xpath));
 
		sc.selectByVisibleText(data);
		result =true;
		break;}catch(StaleElementReferenceException e){
	}i++;
	}
	return result;
	}






	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "TLConfiguration");
	}
}
