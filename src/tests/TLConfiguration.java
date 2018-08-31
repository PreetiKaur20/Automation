package tests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class TLConfiguration extends TestBase {

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
	public void TLConfiguration(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Account Aconfiguration");
		acpage.changeUser(data.get("TL1"), data.get("Admin"));
		wait(2);
		
		extentTest.log(LogStatus.INFO, "Changed User from TL To Admin");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(FBConstants.ApplicationURL);
		driver.findElement(By.xpath(FBConstants.Admin)).click();
		driver.findElement(By.xpath(FBConstants.TLConfig)).click();
		report.takeScreenShot();
		extentTest
				.log(LogStatus.INFO, "Navigate to Account Configuration Page");
		driver.findElement(By.xpath(FBConstants.ADDTL_Button)).click();
 wait(3);
		// Selecting Vertical
		WebElement vertical = driver.findElement(By.xpath(FBConstants.Vertical));
		vertical.click();
	
		vertical = driver.findElement(By.xpath(FBConstants.Vertical));
		Select sc = new Select(vertical);
		sc.selectByVisibleText(data.get("Vertical"));
	

		
		

	
		retryclicking(FBConstants.Client,data.get("Client"));
		retryclicking(FBConstants.Project,data.get("Project"));
		retryclicking(FBConstants.Process,data.get("Process"));
		retryclicking(FBConstants.SDH,data.get("SDH"));
		if(isElementPresent_xpath(FBConstants.SM)){
		retryclicking(FBConstants.SM,data.get("SM"));}
		if(isElementPresent_xpath(FBConstants.OM)){
		retryclicking(FBConstants.OM,data.get("OM"));}
		if(isElementPresent_xpath(FBConstants.TL)){
	retryclicking(FBConstants.TL,data.get("TL"));}
		if(isElementPresent_xpath(FBConstants.Location)){
	retryclicking(FBConstants.Location,data.get("Location"));
	
wait(2);
	driver.findElement(By.xpath(FBConstants.Submit_Button)).click();}
			
	
	

		
	if(isAlertPresent()){	
	System.out.println("Alert Found");
  Alert al = driver.switchTo().alert();

  String text = al.getText();
  System.out.println(text);
//  Assert.assertTrue(text.equals("Selected teamlead already exists for the selected process and location. Please map with another teamlead or location."), "TL Configured firt Time");
  al.accept();
	  

  acpage.changeUser(data.get("Admin"), data.get("LoginTL"));

  driver.get(FBConstants.LoginURL);
	driver.findElement(By.xpath(FBConstants.Reports)).click();
	driver.get(FBConstants.LoginURL);
	String SDH = driver.findElement(By.xpath("//*[@id='ctl00_CphBody_ddlSDH']")).getText();
	System.out.println(SDH);
	Assert.assertTrue(SDH.equals(data.get("SDH")));
	  acpage.changeUser(data.get("LoginTL"), data.get("TL1"));
	
		
	}
	}
	
  

		
	
		

/*
//Selecting Client
		WebElement client = driver.findElement(By.xpath(FBConstants.Client));
		client.click();
		wait(3);
		client = driver.findElement(By.xpath(FBConstants.Client));
		 sc = new Select(client);
		sc.selectByVisibleText(data.get("Client"));
		wait(3);
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Selecting Client");

		//Selecting Project
		WebElement project = driver.findElement(By.xpath(FBConstants.Project));
		project.click();
		wait(3);
		sc = new Select(project);
		project = driver.findElement(By.xpath(FBConstants.Project));
		sc.selectByVisibleText(data.get("Project"));
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Selecting Project");

		
		//Selecting Process
		wait(3);
		WebElement process = driver.findElement(By.xpath(FBConstants.Process));
		process.click();
		wait(3);
		sc = new Select(process);
		process = driver.findElement(By.xpath(FBConstants.Project));
		sc.selectByIndex(1);
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Selecting Process");

		//Selecting SDH
		wait(3);
		WebElement sdh = driver.findElement(By.xpath(FBConstants.SDH));
		sdh.click();
		wait(3);
		sc = new Select(sdh);
		sdh = driver.findElement(By.xpath(FBConstants.SDH));
		sc.selectByVisibleText(data.get("SDH"));
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Selecting SDH");

		
		//Selecting SM
		wait(3);
		WebElement sm = driver.findElement(By.xpath(FBConstants.SM));
		sm.click();
		wait(1);
		sc = new Select(sm);
		sm = driver.findElement(By.xpath(FBConstants.SM));
		sc.selectByVisibleText(data.get("SM"));
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Selecting SM");

		//Selecting OM
		wait(3);
		WebElement om = driver.findElement(By.xpath(FBConstants.OM));
		om.click();
		wait(1);
		sc = new Select(om);
		om = driver.findElement(By.xpath(FBConstants.OM));
		sc.selectByVisibleText(data.get("OM"));
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Selecting OM");
		
		//Selecting TL
		wait(3);
		WebElement tl = driver.findElement(By.xpath(FBConstants.TL));
		tl.click();
		wait(1);
		sc = new Select(tl);
		tl = driver.findElement(By.xpath(FBConstants.TL));
		sc.selectByVisibleText(data.get("TL"));
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Selecting TL");

		
		//Selecting Location 
		wait(3);
		WebElement loc = driver.findElement(By.xpath(FBConstants.Location));
		loc.click();
		wait(1);
		sc = new Select(loc);
		loc = driver.findElement(By.xpath(FBConstants.Location));
		sc.selectByVisibleText(data.get("Location"));
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Selecting Location");
		
		wait(2);*/
		
	
		
		
		
	
		
		
	
		



	public boolean retryclicking(String xpath,String data){
	boolean result =false;
	int i=0;
	while(i<=2)
	{
		try{
			
	wait(2); 
		WebElement ele = driver.findElement(By.xpath(xpath));
		ele.click();
	
	Select	sc = new Select(ele);
	ele = driver.findElement(By.xpath(xpath));
wait(1);
		sc.selectByVisibleText(data);
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
