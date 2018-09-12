package tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.tools.ant.taskdefs.Exit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
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

import pages.ValidateDataEntryPage;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.TestBase;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;

public class OperationManagerPending extends TestBase {

	TestUtil report = new TestUtil();
	ValidateDataEntryPage page = new ValidateDataEntryPage();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "OperationManagerPending")) {
			throw new SkipException("Runmode set to No");
		}
	}


	@Test(dataProvider = "getData", priority = 1)
	public void OperationManagerPending(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Logged in as OM User and Able to Select TL and Future date ,Verified Status should be Pending for Approval");

	
		if(isElementPresent_xpath(FBConstants.Trasction)){
			   driver.get(FBConstants.LoginURL);
				  driver.findElement(By.xpath(FBConstants.Trasction)).click(); }
		else{
			driver.findElement(By.xpath(FBConstants.Trasction)).click(); 
		}
	
		page.dataEntrySearch();
	
		String currentdate = report.seletCurrentDate(-4);
		if(isElementPresent_xpath(FBConstants.Calendar)){

		WebElement ele = driver.findElement(By.xpath(FBConstants.Calendar));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";

		js.executeScript(
				"arguments[0].setAttribute(arguments[1], arguments[2]);", ele,
				"value", currentdate);

WebElement TL=		driver.findElement(By.xpath(FBConstants.TLDrop));
Select sc = new Select(TL);
sc.selectByIndex(1);

loadwait(2000,By.id("loading"));
report.takeScreenShot();
page.clickSearchButton();
report.takeScreenShot();
extentTest.log(LogStatus.INFO, "Clicked on Search Button");

loadwait(2000,By.id("loading"));
if (!isElementPresent_xpath("//*[@id='ctl00_CphBody_lblPendingMessage']")){
//Enter Data in Recieved
WebElement recieved =driver.findElement(By.xpath(FBConstants.Recieved_OM));
recieved.clear();
recieved.sendKeys(data.get("Received"));
report.takeScreenShot();
extentTest.log(LogStatus.INFO, "Edit Data ");

page.clickSaveButton();

wait(3);
waitAlert(8000);
try{
driver.switchTo().alert().accept();}catch(Exception e){}


// Navigate to Data View Entry
driver.get(FBConstants.LoginURL);
wait(1);
driver.findElement(By.xpath(FBConstants.Trasction)).click();
wait(1);
page.DataViewEntryNavigation();
extentTest.log(LogStatus.INFO, "Navigate to Data View Entry");
driver.get(FBConstants.Data_View_Entry);
report.takeScreenShot();
extentTest.log(LogStatus.INFO, "Navigate to Data Entry View Page");


WebElement datefrom = driver.findElement(By
		.xpath(FBConstants.From_Date));
WebElement dateTo = driver.findElement(By.xpath(FBConstants.To_Date));
page.setDate(datefrom, currentdate);
page.setDate(dateTo, currentdate);
 TL=		driver.findElement(By.xpath(FBConstants.TLDrop));
 sc = new Select(TL);
sc.selectByIndex(1);

extentTest.log(LogStatus.INFO, "Enter Datae from , Date To and TL");

page.clickSearchButton();
report.takeScreenShot();
extentTest.log(LogStatus.INFO, "Click on search Button");

String actualstatus = driver.findElement(By.xpath(FBConstants.Status))
		.getText();
Assert.assertTrue(actualstatus.equals("Pending for approval"), "Sataus is not Pending for Approval");
}
		}




		


	}

	


	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "OperationManagerPending");
	}
}
