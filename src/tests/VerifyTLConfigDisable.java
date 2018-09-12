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

public class VerifyTLConfigDisable extends TestBase {

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
	public void VerifyTLConfigDisable(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Verified TL configuration Disable Link");
		//Change User to TL
	  wait(1);
				WebElement username = driver.findElement(By.xpath(FBConstants.Username));
				String User = username.getText();
				wait(1);
				System.out.println(User);
				if(User.equals(FBConstants.OMUser))
				{
					acpage.changeUser(data.get("OM1"), data.get("Admin"));
				}
				if(User.equals(FBConstants.TLUser))
				{
					acpage.changeUser(data.get("TL1"), data.get("Admin"));
				}
				if(User.equals(FBConstants.SDHUser))
				{
					acpage.changeUser(data.get("SDH"), data.get("Admin"));
				}
				
				
				
			
				driver.get(FBConstants.LoginURL);
				wait(3);
			

				driver.get(FBConstants.LoginURL);
				wait(2);
			     driver.get(FBConstants.ApplicationURL);
			    wait(2);
			    if(isElementPresent_xpath(FBConstants.Admin)){
				driver.findElement(By.xpath(FBConstants.Admin)).click();
			     wait(1);
			     driver.findElement(By.xpath(FBConstants.TLConfig)).click();
			     wait(1);
				driver.findElement(By.xpath(FBConstants.Admin)).click();
			
				driver.findElement(By.xpath(FBConstants.TLConfig)).click();

				extentTest
						.log(LogStatus.INFO, "Navigate to Account Configuration Page");
				
				wait(2);

		// Selecting Vertical
	
		WebElement showbutton =driver.findElement(By.xpath(FBConstants.Show_button));
		report.takeScreenShot();
		extentTest
				.log(LogStatus.INFO, "Select Records and click on Show Button ");
		wait(1);
		showbutton.click();
		//click on status
		driver.findElement(By.xpath(FBConstants.StatusFilter)).click();
WebElement box = driver.findElement(By.xpath("//*[@id='ctl00_CphBody_grdTLMapping']/tbody/tr/td[11]"));
List<WebElement> all=box.findElements(By.tagName("a"));
for(int i =0; i<=all.size()-1;i++){
	System.out.println(all.get(i).getText());
	if(all.get(i).getText().equals("Disable"))
	{
		System.out.println("Found");
		all.get(i).click();
	
		extentTest
				.log(LogStatus.INFO, "Clicked on Disable Button");
		break;
	}
}
			    }

extentTest
		.log(LogStatus.INFO, "Click on Disable Button ");
try{
WebDriverWait wait = new WebDriverWait(driver, 5000);

wait.until(ExpectedConditions.alertIsPresent());
Alert al = driver.switchTo().alert();
System.out.println(al.getText());
wait(2);
Assert.assertTrue(al.getText().equals("Are you sure you want to disable selected Process?"), "Process not disable");
al.accept();

driver.switchTo().alert().accept();}catch(Exception e){}



	}
  
	
		
	
		
	
		
		

	
		
		
	
		


	


	



	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "TLConfiguration");
	}
}
