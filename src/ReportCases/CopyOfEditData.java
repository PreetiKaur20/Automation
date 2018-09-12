package ReportCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.tools.ant.taskdefs.Exit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

import pages.ReportPage;
import pages.ValidateDataEntryPage;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.TestBase;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;

public class CopyOfEditData extends TestBase {
	ValidateDataEntryPage page = new ValidateDataEntryPage();
	TestUtil report = new TestUtil();
	ReportPage repage = new ReportPage();
	String currentdate = report.seletCurrentDate(-1);

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "CurrentDayRecord")) {
			throw new SkipException("Runmode set to No");
		}
	}

	@Test(dataProvider = "getData", priority = 1)
	public void CurrentDayRecord(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Edit Data");

		// Change User to TL
		WebElement username = driver
				.findElement(By.xpath(FBConstants.Username));
		driver.get(FBConstants.LoginURL);
		wait(2);

		driver.findElement(By.xpath(FBConstants.Trasction)).click();
		page.dataEntrySearch();
		// int date = Integer.parseInt(data.get("Date"));

		System.out.println(currentdate);
		//if (isElementPresent_xpath(FBConstants.Calendar)) {
			/*WebElement ele = driver.findElement(By.xpath(FBConstants.Calendar));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
			js.executeScript(
					"arguments[0].setAttribute(arguments[1], arguments[2]);",
					ele, "value", currentdate);
			loadwait(2000, By.id("loading"));
			report.takeScreenShot();
			wait(1);
			page.clickSearchButton();

			String base = "//*[@id='ctl00_CphBody_GrdDailyUpdates_ctl0";
			String rec = "_txtReceived']";

			String coded = "_txtCoded']";
			String pending = "_txtPending']";
			String discard = "_txtDiscards']";*/

			
/*			String part = "//*[@id='ctl00_CphBody_GrdDailyUpdates']/tbody/tr[";
			String col3 = "]/td[3]";
			String col1 = "]/td[1]";
			String col2 = "]/td[2]";
		
	int i;		
			for( i=2;i<=7;i++){
				if((driver.findElement(By.xpath(part+i+col1)).getText().equals(FBConstants.ClientRep)))
System.out.println(driver.findElement(By.xpath(part+i+col2)).getText());{
	if(driver.findElement(By.xpath(part+i+col2)).getText().equals(FBConstants.ProjectRep)){
		
		if(driver.findElement(By.xpath(part+i+col3)).getText().equals(FBConstants.ProcessRep)){
			break;
		}
		
	}}
}
*/
	int k=	repage.returnRow();
	System.out.println("Row Number :"+k);
				

				/*
				 * page.clickSaveButton();
				 * 
				 * wait(3); waitAlert(8000); try {
				 * driver.switchTo().alert().accept(); } catch (Exception e) { }
				 * report.takeScreenShot(); extentTest.log(LogStatus.INFO,
				 * "Clicked on Save Button"); // Navigate to Data View Entry
				 * 
				 * 
				 * 
				 * ele = driver.findElement(By.xpath(FBConstants.Calendar));
				 * 
				 * 
				 * js = (JavascriptExecutor) driver; scriptSetAttrValue =
				 * "arguments[0].setAttribute(arguments[1],arguments[2])";
				 * js.executeScript(
				 * "arguments[0].setAttribute(arguments[1], arguments[2]);",
				 * ele, "value", currentdate); loadwait(2000, By.id("loading"));
				 * report.takeScreenShot(); wait(1); page.clickSearchButton();
				 * 
				 * extentTest.log(LogStatus.INFO, "Clicked on Search Button");
				 * loadwait(2000, By.id("loading"));
				 * 
				 * // Enter dta in Recieved EditBox
				 */
		
			
		

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "CurrentDayRecord");
	}
}
