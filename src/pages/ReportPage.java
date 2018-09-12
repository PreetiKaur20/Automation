package pages;

import org.openqa.selenium.By;

import util.FBConstants;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.TestBase;

public class ReportPage extends TestBase {
	
public int returnRow(){
	String part = "//*[@id='ctl00_CphBody_GrdDailyUpdates']/tbody/tr[";
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
	return i;

}
	
}
