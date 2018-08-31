package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import util.FBConstants;
import BasePage.NtlgReplace;
import BasePage.TestBase;

public class AccountConfigurationPage extends TestBase{

	
	public void changeUser(String olduser, String newuser) throws IOException {
		NtlgReplace changeuser = new NtlgReplace();
		changeuser.replceNtlg(olduser, newuser);
		
	}
	
	public void selectAccountName(String data)
	{
		WebElement AccountName = driver.findElement(By.xpath(FBConstants.Account_Name));
		AccountName.sendKeys(data);
	}
	public void selectBillingType(String data){
		WebElement BillingType = driver.findElement(By.xpath(FBConstants.Billing_Type));
		BillingType.sendKeys(data);
	}
	
	public void selectRevenueType(String data){
		WebElement RevenueType = driver.findElement(By.xpath(FBConstants.Revenue_Type));
		RevenueType.sendKeys(data);
	}
	public void clickShowButton(){
		driver.findElement(By.xpath(FBConstants.Show_button)).click();
	}
	public void clickSubmit(){
		driver.findElement(By.xpath(FBConstants.Submit)).click();
	}
	public void clickshowButton(){
		driver.findElement(By.xpath(FBConstants.Show_button)).click();
	}
}
