package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.NtlgReplace;
import BasePage.TestBase;
import util.FBConstants;
import util.TestUtil;

public class ValidateDataEntryPage extends TestBase {
	TestUtil report = new TestUtil();

	public void dataEntrySearch() {

		driver.findElement(By.xpath(FBConstants.Trasction)).click();
		
		// click on Data Entry
		wait(1);
		driver.get(FBConstants.Data_Entry);
		extentTest.log(LogStatus.INFO, "Navigating To Data Entry Page");
		wait(2);
	
	

	}

	public void SDHrejection() {
		WebElement Transaction = driver.findElement(By
				.xpath(FBConstants.Trasction));
		wait(1);
		Transaction.click();
		// navigate to approval
		driver.get(FBConstants.Approval);
		wait(1);
		// Select number of recoords and filter with TL and Approve

		WebElement records = driver.findElement(By
				.xpath(FBConstants.Record_List));
		Select rec = new Select(records);
		rec.selectByIndex(3);

		wait(1);
		driver.findElement(By.xpath("//*[@id='selectAll']")).click();
		driver.findElement(By.xpath(FBConstants.TL_Filter)).click();
		driver.findElement(By.xpath(FBConstants.Reject_Button)).click();
		driver.switchTo().alert().accept();

	}

	public void selectCurrentDate() {
		WebElement da = driver
				.findElement(By
						.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active.ui-state-hover"));
		String currentDate = da.getText();
		// Select Date
		da.click();
	}

	public void receiveddata(String data) {
		driver.findElement(By.xpath(FBConstants.Received_EditBox)).click();
		driver.findElement(By.xpath(FBConstants.Received_EditBox)).clear();
		driver.findElement(By.xpath(FBConstants.Received_EditBox)).sendKeys(
				data);
		driver.findElement(By.xpath(FBConstants.Received_EditBox2)).click();
		driver.findElement(By.xpath(FBConstants.Received_EditBox2)).clear();
		driver.findElement(By.xpath(FBConstants.Received_EditBox2)).sendKeys(
				data);
	}

	public void codeddata(String data) {
		wait(1);
		driver.findElement(By.xpath(FBConstants.Coded_EditBox)).click();
		driver.findElement(By.xpath(FBConstants.Coded_EditBox)).clear();
		driver.findElement(By.xpath(FBConstants.Coded_EditBox)).sendKeys(data);
	}

	public void clickSaveButton() {
		driver.findElement(By.xpath(FBConstants.Save_Button)).click();
	}

	public void DataViewEntryNavigation() {
		wait(2);
		driver.get(FBConstants.Data_View_Entry);
	}

	public void clickSearchButton() {
		driver.findElement(By.xpath(FBConstants.Search_Button)).click();
	}

	public void changeUser(String olduser, String newuser) throws IOException {
		NtlgReplace changeuser = new NtlgReplace();
		changeuser.replceNtlg(olduser, newuser);
		//driver.get("http://PreetiK:Happy@123@10.1.2.85/OMH/");
	}

	public void SDHApproval() {
		WebElement Transaction = driver.findElement(By
				.xpath(FBConstants.Trasction));
		wait(1);
		Transaction.click();
		// navigate to approval
		driver.get(FBConstants.Approval);
		wait(1);
		// Select number of recoords and filter with TL and Approve

		WebElement records = driver.findElement(By
				.xpath(FBConstants.Record_List));
		Select rec = new Select(records);
		rec.selectByIndex(3);

		wait(1);
		driver.findElement(By.xpath("//*[@id='selectAll']")).click();
		driver.findElement(By.xpath(FBConstants.TL_Filter)).click();
		driver.findElement(By.xpath(FBConstants.Approved_Button)).click();
		driver.switchTo().alert().accept();

	}
	
	
	public void setDate(WebElement ele,String date)
	{
	    JavascriptExecutor js = (JavascriptExecutor) driver;
			String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
	js.executeScript(
					"arguments[0].setAttribute(arguments[1], arguments[2]);", ele,
					"value", date);
	}
}
