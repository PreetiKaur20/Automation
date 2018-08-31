package tests;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

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
import com.thoughtworks.selenium.webdriven.commands.IsAlertPresent;

import BasePage.TestBase;

public class VerifyTLConnfigEdit extends TestBase {

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
	public void VerifyTLConnfigEdit(Hashtable<String, String> data)
			throws InterruptedException, IOException {

		extentTest = extentReports.startTest("Verified EDit Link TL connfiguration");
		wait(2);
		WebElement username = driver.findElement(By.xpath(FBConstants.Username));
		String User = username.getText();
		wait(1);
		System.out.println(User);
		if(User.equals(FBConstants.OMUser))
		{
			acpage.changeUser(data.get("OM1"), data.get("Admin"));
		}
		if(User.equals(FBConstants.TLUser))
		{
			acpage.changeUser(data.get("TL1"), data.get("Admin"));
		}
		if(User.equals(FBConstants.SDHUser))
		{
			acpage.changeUser(data.get("SDH"), data.get("Admin"));
		}
		
		
		
	
		driver.get(FBConstants.LoginURL);
		wait(1);
	     driver.get(FBConstants.ApplicationURL);
	    wait(1);
	    if(isElementPresent_xpath(FBConstants.Admin)){
		driver.findElement(By.xpath(FBConstants.Admin)).click();
	     wait(1);
	     driver.findElement(By.xpath(FBConstants.TLConfig)).click();
	     wait(1);
		driver.findElement(By.xpath(FBConstants.Admin)).click();
	
		driver.findElement(By.xpath(FBConstants.TLConfig)).click();
		 wait(1);
			report.takeScreenShot();
		extentTest
				.log(LogStatus.INFO, "Navigate to TL Configuration Page");
	
		
		wait(1);
		// Selecting Vertical
	
		WebElement showbutton =driver.findElement(By.xpath(FBConstants.Show_button));
	
		extentTest
				.log(LogStatus.INFO, "Select Records and click on Show Button ");
		
		
		showbutton.click();
	
		wait(2);
		//click on status
		driver.findElement(By.xpath(FBConstants.StatusFilter)).click();
WebElement box = driver.findElement(By.xpath("//*[@id='ctl00_CphBody_grdTLMapping']/tbody/tr/td[11]"));
List<WebElement> all=box.findElements(By.tagName("a"));
for(int i =0; i<=all.size()-1;i++){
	System.out.println(all.get(i).getText());
	if(all.get(i).getText().equals("Edit |"))
	{
		System.out.println("Found");
		all.get(i).click();
	
		extentTest
				.log(LogStatus.INFO, "Clicked on Edit");
		break;
	}
}
	    }

extentTest
		.log(LogStatus.INFO, "Click on Edit Button ");
report.takeScreenShot();

//Change TL 
wait(1);
WebElement TL = driver.findElement(By.xpath(FBConstants.TL));
wait(1);
Select sc = new Select(TL);
sc.selectByIndex(3);



if(isAlertPresent()){
	System.out.println(isAlertPresent());

	driver.switchTo().alert().accept();
}

driver.findElement(By.xpath(FBConstants.Submit_Button)).click();
extentTest
.log(LogStatus.INFO, "Click on Submit Button ");




if(isAlertPresent()){
	driver.switchTo().alert().accept();
	System.out.println(isAlertPresent());
}

report.takeScreenShot();
	

	}
  
	
		
	
		
	
		
		

	
		
		
	
		


	public boolean retryclicking(String xpath,String data){
	boolean result =false;
	int i=0;
	while(i<=1)
	{
		try{
			 wait(1);
			 
		WebElement ele = driver.findElement(By.xpath(xpath));
		
	Select	sc = new Select(ele);
	ele = driver.findElement(By.xpath(xpath));
 
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
