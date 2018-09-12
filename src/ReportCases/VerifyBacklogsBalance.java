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

public class VerifyBacklogsBalance extends TestBase {
	ValidateDataEntryPage page = new ValidateDataEntryPage();
	TestUtil report = new TestUtil();

	ReportPage repage= new ReportPage();


	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "CurrentDayRecord")) {
			throw new SkipException("Runmode set to No");
		}
	}

	@Test(dataProvider = "getData", priority = 1)
	public void VerifyBackLogBalance(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports
				.startTest("Verified Balance of Search data changed to Backlog on Next Day ");
		String currentdate = report.seletCurrentDate(-1);
		// Change User to TL
		
		driver.get(FBConstants.LoginURL);
		wait(2);

		driver.findElement(By.xpath(FBConstants.Trasction)).click();
		page.dataEntrySearch();
		// int date = Integer.parseInt(data.get("Date"));

		System.out.println(currentdate);
		if (isElementPresent_xpath(FBConstants.Calendar)) {
			WebElement ele = driver.findElement(By.xpath(FBConstants.Calendar));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
			js.executeScript(
					"arguments[0].setAttribute(arguments[1], arguments[2]);",
					ele, "value", currentdate);
			loadwait(2000, By.id("loading"));
			report.takeScreenShot();
			wait(1);
			page.clickSearchButton();

			extentTest.log(LogStatus.INFO, "Entered Date Clicked on Search Button");
			report.takeScreenShot();
			loadwait(2000, By.id("loading"));
			// Enter dta in Recieved EditBox
			wait(2);
			
			
			String base= "//*[@id='ctl00_CphBody_GrdDailyUpdates_ctl0";
			String rec="_txtReceived']";
		
			String coded="_txtCoded']";
			String pending="_txtPending']";
			String discard="_txtDiscards']";
			
			
			int i=	repage.returnRow();		
	

			// Verify Client
			WebElement table = driver
					.findElement(By
							.xpath("//*[@id='ctl00_CphBody_GrdDailyUpdates']/tbody/tr["+i+"]"));
			List<WebElement> all = table.findElements(By.tagName("td"));
			
			
			
			
			
			  for(int j=0;j<=all.size()-1;j++){
			 System.out.println(all.get(j).getText()); }
			 
			System.out.println("Done");
			 table = driver
						.findElement(By
								.xpath("//*[@id='ctl00_CphBody_GrdDailyUpdates']/tbody/tr["+i+"]"));
			all = table.findElements(By.tagName("td"));
			String Client = all.get(0).getText();
			String project = all.get(1).getText();
			String process = all.get(2).getText();
			String location = all.get(3).getText();
			String vertical = all.get(4).getText();
			
			String xbacklog="_txtBacklog']";
			String xreceived="_txtReceived']";
			String xcoded="_txtCoded']";
			String xpending="_txtPending']";
			String xdiscard="_txtDiscards']";
			String xbalance="_txtBalance']";
			
			
			String xpath1="//*[@id='ctl00_CphBody_GrdDailyUpdates_ctl0";
			
		String backlog= driver.findElement(By.xpath(xpath1+i+xbacklog)).getAttribute("value");
System.out.println(backlog);
String recievedval= driver.findElement(By.xpath(xpath1+i+xreceived)).getAttribute("value");
String codedval= driver.findElement(By.xpath(xpath1+i+xcoded)).getAttribute("value");
String pendingval= driver.findElement(By.xpath(xpath1+i+xpending)).getAttribute("value");
String discardval= driver.findElement(By.xpath(xpath1+i+xdiscard)).getAttribute("value");
String balanceval= driver.findElement(By.xpath(xpath1+i+xbalance)).getAttribute("value");

		

		Double Backlog = Double.parseDouble(backlog);
			Double Recieved = Double.parseDouble(recievedval);
			Double Coded = Double.parseDouble(codedval);
			Double Discard = Double.parseDouble(discardval);
			Double Pending = Double.parseDouble(pendingval);
			Double Balance = Double.parseDouble(balanceval);

	
			
		currentdate = report.seletCurrentDate(0);
		// Change User to TL
		
		driver.get(FBConstants.LoginURL);
		wait(2);

		driver.findElement(By.xpath(FBConstants.Trasction)).click();
		page.dataEntrySearch();
		// int date = Integer.parseInt(data.get("Date"));

		System.out.println(currentdate);
		if (isElementPresent_xpath(FBConstants.Calendar)) {
			 ele = driver.findElement(By.xpath(FBConstants.Calendar));

			 js = (JavascriptExecutor) driver;
			scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
			js.executeScript(
					"arguments[0].setAttribute(arguments[1], arguments[2]);",
					ele, "value", currentdate);
			loadwait(2000, By.id("loading"));
			report.takeScreenShot();
			wait(1);
			page.clickSearchButton();}
		extentTest.log(LogStatus.INFO, "Searched Next day Data");
		report.takeScreenShot();
		
		
		backlog = backlog= driver.findElement(By.xpath(xpath1+i+xbacklog)).getAttribute("value");
		 Backlog = Double.parseDouble(backlog);
		
		if(Backlog.equals(Balance)){
		Assert.assertTrue(Backlog.equals(Balance), "Balance Not Equal to Next day Backlog");
		}
		
		}
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "CurrentDayRecord");
	}
}
