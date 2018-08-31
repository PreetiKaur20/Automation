package tests;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import BasePage.TestBase;

import com.relevantcodes.extentreports.LogStatus;

public class DateSelectionVerification extends TestBase{

	TestUtil report = new TestUtil();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "DateSelectionVerification")) {
			throw new SkipException("Runmode set to No");
		}
	}



	@Test(dataProvider = "getData", priority = 1)
	public void DateSelectionVerification(Hashtable<String, String> data)
			throws InterruptedException {

		extentTest = extentReports.startTest("Veried Able to Select Future Date");
		String futuredate=	report.seletCurrentDay(2);
		ValidateDataEntryPage page = new ValidateDataEntryPage();
		page.dataEntrySearch();


driver.findElement(By.xpath(FBConstants.Calendar)).click();
report.takeScreenShot();
extentTest.log(LogStatus.INFO, "Clicked on Calendar");


List<WebElement> all =driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr"));
for(int i=0;i<=all.size()-1;i++){
	System.out.println(all.get(i).getText());
	if(all.get(i).getText().equals(futuredate))
	{
		all.get(i).click();
		report.takeScreenShot();
		extentTest.log(LogStatus.INFO, "Clicking on Future date ");
	}
	
	try{
	
Assert.assertTrue(isElementPresent_xpath("//*[@id='ui-datepicker-div']"), "Unable to Select Future date");
report.takeScreenShot();
extentTest.log(LogStatus.INFO, "Furure Date not selected: "+futuredate);}catch(Exception e){}
			
}



	}





	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "DateSelectionVerification");
	}
}

