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

public class OperationManager extends TestBase {

	TestUtil report = new TestUtil();
	ValidateDataEntryPage page = new ValidateDataEntryPage();

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

		extentTest = extentReports.startTest("Logged in As OM User and Select Date and TL and Verified Able to Edit data");
	
     page.changeUser(data.get("TL"), data.get("OM"));
	wait(2);
		extentTest.log(LogStatus.INFO, "Changed User from TL To OM");
		driver.get(FBConstants.LoginURL);
		wait(3);
		page.dataEntrySearch();
	
		String currentdate = report.seletCurrentDate(0);
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

page.clickSearchButton();

extentTest.log(LogStatus.INFO, "Clicked on Search Button");

loadwait(3000,By.id("loading"));
//Enter Data in Recieved
WebElement recieved =driver.findElement(By.xpath(FBConstants.Recieved_OM));
recieved.clear();
report.takeScreenShot();
recieved.sendKeys(data.get("Received"));

extentTest.log(LogStatus.INFO, "Edit Data ");

page.clickSaveButton();

wait(3);

try{
driver.switchTo().alert().accept();}catch(Exception e){}

}

		
		

	}

	


	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "OperationManager");
	}
}
