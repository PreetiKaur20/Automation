
//Your entries not yet approved by your SDH for the selected date. So you wil be notable to see the entered numbers and will be not allowed to enter the numbers until it approves.

package tests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.ValidateDataEntryPage;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;
import BasePage.NtlgReplace;
import BasePage.TestBase;

import com.relevantcodes.extentreports.LogStatus;

public class VerifyReportsTL extends TestBase {

	TestUtil report = new TestUtil();
	String cureentprevfour = report.seletCurrentDate(-1);
	ValidateDataEntryPage page = new ValidateDataEntryPage();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "EditDataPendingStatus")) {
			throw new SkipException("Runmode set to No");
		}
	}



	@Test(dataProvider = "getData", priority = '1')
	public void VerifyReportsTL(Hashtable<String, String> data) throws IOException {
		extentTest = extentReports
				.startTest("Logged in as TL, Navigate to Production Report, verified Show Report button is Enable and Verified Reports");
		driver.get(FBConstants.LoginURL);
		extentTest.log(LogStatus.INFO, "Navigate to Applicatiion");
		report.takeScreenShot();
		if(isElementPresent_xpath(FBConstants.Reports)){
			System.out.println("Clicked on Report");
		driver.findElement(By.xpath(FBConstants.Reports)).click();
		extentTest.log(LogStatus.INFO, "Clicked on Report");
		report.takeScreenShot();
		wait(1);
		driver.findElement(By.xpath(FBConstants.Production_Report)).click();
		 
		}
			
		
	
String codedvalue;

if(isElementPresent_xpath(FBConstants.Show_Report)){
	WebElement showReport=		driver.findElement(By.xpath(FBConstants.Show_Report));
// call the executeScript method
//js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", showReport);

 showReport.click();
	extentTest.log(LogStatus.INFO, "Clicked on Production and Verified Report");
	
}

wait(1);


driver.switchTo().frame("ReportFramectl00_CphBody_RpvProduction");
driver.switchTo().frame("report");


if(!isElementPresent_xpath("//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[2]/td[3]")){
	System.out.println("not found");
}
else{
	System.out.println("found");
}

String part1="//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[2]/td[";
String part2="]/div";
int i=3;
while(isElementPresent_xpath(part1+i+part2)){
String date=	driver.findElement(By.xpath(part1+i+part2)).getText();
if(date.equals(cureentprevfour)){
	System.out.println("Coloum number:"+i);
	codedvalue=driver.findElement(By.xpath(part1+i+part2)).getText();
}
System.out.println(date);
i++;
}








wait(1);


/*
int i=3;
while(isElementPresent_xpath(part1+i+part2)){
String date=	driver.findElement(By.xpath(part1+i+part2)).getText();
System.out.println(date);
i++;
}
	*/	





		}

	






	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "EditDataPendingStatus");

	}
}
