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

public class EditData extends TestBase {
	ValidateDataEntryPage page = new ValidateDataEntryPage();
	TestUtil report = new TestUtil();
	ReportPage repage= new ReportPage();
	String currentdate = report.seletCurrentDate(-1);

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "CurrentDayRecord")) {
			throw new SkipException("Runmode set to No");
		}
	}


	@Test(dataProvider = "getData", priority = 1)
	public void EditData(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Edit Data");
		
		//Change User to TL
		WebElement username = driver.findElement(By.xpath(FBConstants.Username));
     driver.get(FBConstants.LoginURL);
		wait(2);
	
	driver.findElement(By.xpath(FBConstants.Trasction)).click(); 
		page.dataEntrySearch();
	//	int date = Integer.parseInt(data.get("Date"));
	
		System.out.println(currentdate);
		if(isElementPresent_xpath(FBConstants.Calendar)){
		WebElement ele = driver.findElement(By.xpath(FBConstants.Calendar));

	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
		js.executeScript(
				"arguments[0].setAttribute(arguments[1], arguments[2]);", ele,
				"value", currentdate);
		loadwait(2000, By.id("loading"));
		report.takeScreenShot();
		wait(1);
		page.clickSearchButton();
				
		String base= "//*[@id='ctl00_CphBody_GrdDailyUpdates_ctl0";
		String rec="_txtReceived']";
	
		String coded="_txtCoded']";
		String pending="_txtPending']";
		String discard="_txtDiscards']";
		
		
		
/*		int i = 2;
		String part1 = "//*[@id='ctl00_CphBody_GrdDailyUpdates']/tbody/tr[";
		String part2 = "]/td[3]";
		String col="]/td[1]";
		while (isElementPresent_xpath(part1 + i + part2)) {
			System.out.println(driver.findElement(
					By.xpath(part1 + i + part2)).getText());
			
			
			
			if (driver.findElement(By.xpath(part1+i+col)).getText().contains("McKesson PD")){		
			
			System.out.println(driver.findElement(By.xpath(part1+i+col)).getText());
			if (driver.findElement(By.xpath(part1+i+part2)).getText().contains("RAI-Denial Review")){
				
				driver.findElement(By.xpath(base+i+rec)).clear();
				driver.findElement(By.xpath(base+i+rec)).sendKeys(data.get("received"));

				extentTest.log(LogStatus.INFO,
						"Entered Data in Reciedved box");
				driver.findElement(By.xpath(base+i+coded)).clear();
				driver.findElement(By.xpath(base+i+coded)).sendKeys(data.get("coded"));
				driver.findElement(By.xpath(base+i+pending)).clear();
				driver.findElement(By.xpath(base+i+pending)).sendKeys(data.get("Pending"));
				driver.findElement(By.xpath(base+i+discard)).clear();
				driver.findElement(By.xpath(base+i+discard)).sendKeys(data.get("Discard"));
				break;
			}
			}
			i++;
		}
*/
		int i=	repage.returnRow();
		driver.findElement(By.xpath(base+i+rec)).clear();
		driver.findElement(By.xpath(base+i+rec)).sendKeys(data.get("received"));

		extentTest.log(LogStatus.INFO,
				"Entered Data in Reciedved box");
		driver.findElement(By.xpath(base+i+coded)).clear();
		driver.findElement(By.xpath(base+i+coded)).sendKeys(data.get("coded"));
		driver.findElement(By.xpath(base+i+pending)).clear();
		driver.findElement(By.xpath(base+i+pending)).sendKeys(data.get("Pending"));
		driver.findElement(By.xpath(base+i+discard)).clear();
		driver.findElement(By.xpath(base+i+discard)).sendKeys(data.get("Discard"));
	

		page.clickSaveButton();
		
		wait(3);
		waitAlert(8000);
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
		}
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Clicked on Save Button");
		// Navigate to Data View Entry
		
		
		
		 ele = driver.findElement(By.xpath(FBConstants.Calendar));

		
		 js = (JavascriptExecutor) driver;
		 scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
		js.executeScript(
				"arguments[0].setAttribute(arguments[1], arguments[2]);", ele,
				"value", currentdate);
		loadwait(2000, By.id("loading"));
		report.takeScreenShot();
		wait(1);
		page.clickSearchButton();
		
		extentTest.log(LogStatus.INFO, "Clicked on Search Button");
		loadwait(2000, By.id("loading"));
		
		// Enter dta in Recieved EditBox
		
		
	


		
	
}
	}



	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "CurrentDayRecord");
	}
}
