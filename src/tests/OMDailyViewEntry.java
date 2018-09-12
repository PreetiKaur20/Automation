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

public class OMDailyViewEntry extends TestBase {

	TestUtil report = new TestUtil();
	ValidateDataEntryPage page = new ValidateDataEntryPage();
	String currentdate = report.seletCurrentDate(0);

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "OperationManager")) {
			throw new SkipException("Runmode set to No");
		}
	}



	@Test(dataProvider = "getData", priority = 1)
	public void OMScreen(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Navigate to Date View Entry page as Operation Manager and Verified Approved status");
	
	
  driver.findElement(By.xpath(FBConstants.Trasction)).click();

// click on Data Entry

driver.get(FBConstants.Data_View_Entry);
extentTest.log(LogStatus.INFO, "Navigating To Data View  Entry Page");

report.takeScreenShot();

if(isElementPresent_xpath(FBConstants.From_Date)){
WebElement datefrom = driver.findElement(By
		.xpath(FBConstants.From_Date));
WebElement dateTo = driver.findElement(By.xpath(FBConstants.To_Date));
page.setDate(datefrom, currentdate);
page.setDate(dateTo, currentdate);
wait(1);
 WebElement TL=		driver.findElement(By.xpath(FBConstants.TLDrop));
 Select sc = new Select(TL);
sc.selectByIndex(1);
extentTest.log(LogStatus.INFO, "Selected Date and Client");
report.takeScreenShot();
page.clickSearchButton();
wait(3);






try{
	
String actualstatus = driver.findElement(By.xpath(FBConstants.Status))
		.getText();
Assert.assertTrue(actualstatus.equals("Approved"), "Sataus is not Approved");
report.takeScreenShot();
extentTest.log(LogStatus.INFO, "Click on search Button and Verified status");}catch(Exception e){}
	

}

		
		

	}

	



	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "OperationManager");
	}
}
