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

import pages.ValidateDataEntryPage;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.TestBase;
import util.DataUtil;
import util.FBConstants;
import util.TestUtil;

public class VerifyCodiningProductionReport extends TestBase {
	ValidateDataEntryPage page = new ValidateDataEntryPage();
	TestUtil report = new TestUtil();
	String currentdate = report.seletCurrentDate(-1);
	int k;
	int i;

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

		extentTest = extentReports
				.startTest("Verified Reports Balance Banklogs");

		// Change User to TL
		WebElement username = driver
				.findElement(By.xpath(FBConstants.Username));
		driver.get(FBConstants.LoginURL);
		wait(2);

		driver.findElement(By.xpath(FBConstants.Trasction)).click();
		page.dataEntrySearch();
		// int date = Integer.parseInt(data.get("Date"));

		System.out.println(currentdate);
		if (isElementPresent_xpath(FBConstants.Calendar)) {
			WebElement ele = driver.findElement(By.xpath(FBConstants.Calendar));

			
			
		

			driver.findElement(By.xpath(FBConstants.Reports)).click();
			extentTest.log(LogStatus.INFO, "Clicked on Report");
			driver.findElement(By.xpath(FBConstants.Production_Report)).click();
			WebElement showReport = driver.findElement(By
					.xpath(FBConstants.Show_Report));
			showReport.click();
			String codedvalue;

			driver.switchTo().frame("ReportFramectl00_CphBody_RpvProduction");
			driver.switchTo().frame("report");

			

			
			String process1="//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[";
			String process2="]/td[3]/div";
			
			String project1="//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[";
			String project2="]/td[2]/div";
					

			if(driver.findElement(By.xpath("//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[3]/div")).getText().equals(FBConstants.ProjectRep)){	
				System.out.println(driver.findElement(By.xpath("//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[3]/div")).getText());
						if((driver.findElement(By.xpath("//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[4]/div")).getText()).equals(FBConstants.PrdProcess)){
							k=3;
							}
				
			}
			

else{			
for(k=6;k<=30;k=k+3)
if(driver.findElement(By.xpath(project1+k+project2)).getText().equalsIgnoreCase(FBConstants.ProjectRep)){	
System.out.println(driver.findElement(By.xpath(process1+k+process2)).getText());
		if((driver.findElement(By.xpath(process1+k+process2)).getText()).equalsIgnoreCase(FBConstants.PrdProcess)){
				break;
			}
}	
		
}
		}		
		
		
			System.out.println("Row Number for Process:"+k);
			
			
			
			String part1 = "//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[2]/td[";
			String part2 = "]/div";
			 i = 3;
			while (isElementPresent_xpath(part1 + i + part2)) {
				String date = driver.findElement(
						By.xpath(part1 + i + part2)).getText();
				if (date.equals(currentdate)) {
					break;
				}
				i++;
			}
			
			System.out.println("Column Number for Date:"+i);	
			
			

	//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[9]/td[12]/div		
	//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[9]/td[12]/div
	String final1="//*[@id='oReportCell']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[";	
	String final2="]/td[";
	String final3="]/div";
	String codedx=		final1+k+final2+(i-1)+final3;
	System.out.println(codedx);
	
String valcoded=	driver.findElement(By.xpath(codedx)).getText();
System.out.println(valcoded);
System.out.println(data.get("coded"));
Assert.assertTrue(valcoded.contains(data.get("coded")), "not updated in records");
		}		
		
		
	

	

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "CurrentDayRecord");
	}
}
