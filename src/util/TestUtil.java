package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;





import org.testng.Assert;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import BasePage.TestBase;








public class TestUtil  extends TestBase {

	
	public  void takeScreenShot(){
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=FBConstants.REPORTS_PATH+"\\screenshots\\"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(filePath));
		
	
}
	
	
	


	

	public static void login(String username, String password)
			throws InterruptedException, IOException {
		Thread.sleep(3000L);

		driver.get(config.getProperty("signinurl"));
		APPLICATION_LOGS.debug("Navigating to Login Page");
		getObject("login_email").sendKeys(username);
		getObject("login_password").sendKeys(password);
	
		getObject("signin_button").click();

		APPLICATION_LOGS.debug("Logged in Successfully");
		Thread.sleep(1000L);
		
		Thread.sleep(3000);
	}



	public static void settingMenus(String menu, int v)
			throws InterruptedException {

		WebElement ele = driver.findElement(By
				.xpath("//*[@id='nav-menu']/ul[1]/li[5]/span"));
		if (ele.getText().equalsIgnoreCase("Settings")) {
			ele.click();
			v = 5;
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].style.border='3px solid red'", ele);

			}

		}

		WebElement ele1 = driver.findElement(By
				.xpath("//*[@id='nav-menu']/ul[1]/li[6]/span"));
		if (ele1.getText().equalsIgnoreCase("Settings")) {
			ele1.click();
			v = 6;
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].style.border='3px solid red'", ele1);

			}

		}

		// Select Company Settings

		String part1 = "//*[@id='nav-menu']/ul[1]/li[" + v + "]/ul/li[";
		String part2 = "]/a";
		int i = 1;
		while (isElementPresent_xpath(part1 + i + part2)) {
			String text = driver.findElement(By.xpath(part1 + i + part2))
					.getText();
			System.out.println(text);
			if (text.equalsIgnoreCase(menu)) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(part1 + i + part2)).click();
			}
			i++;
		}

	}
	
	//Change made in isSkip
	public static boolean isSkip(String testCase){
		for(int i=2; i<=datatable.getRowCount("TestCases");i++ ){
			if(datatable.getCellData("TestCases", "TCID", i).equals(testCase)){
				if(datatable.getCellData("TestCases", "Runmode", i).equals("Y")) {
					return false;
				} else {
					return true;
				}
			}

		}

		return false;
	}


	public static Object[][] getData(String sheetName){
		// return test data;
		// read test data from xls

		int rows=datatable.getRowCount(sheetName)-1;
		if(rows <=0){
			Object[][] testData =new Object[1][0];
			return testData;

		}
		rows = datatable.getRowCount(sheetName);  // 3
		int cols = datatable.getColumnCount(sheetName);
		System.out.println("total rows -- "+ rows);
		System.out.println("total cols -- "+cols);
		Object data[][] = new Object[rows-1][cols];

		for( int rowNum = 2 ; rowNum <= rows ; rowNum++){

			for(int colNum=0 ; colNum< cols; colNum++){
				data[rowNum-2][colNum]=datatable.getCellData(sheetName, colNum, rowNum);
			}
		}

		return data;
	

	}



	// make zip of reports
	public static void zip(String filepath){
		try
		{
			File inFolder=new File(filepath);
			File outFolder=new File("Reports.zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data  = new byte[1000];
			String files[] = inFolder.list();
			for (int i=0; i<files.length; i++)
			{
				in = new BufferedInputStream(new FileInputStream
						(inFolder.getPath() + "/" + files[i]), 1000);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while((count = in.read(data,0,1000)) != -1)
				{
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void signout() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='nav-menu']/ul[2]/li[2]/img"))
		.click();
		Thread.sleep(3000);
		String p1 = "//*[@id='company-list']/li[";
		String p2 = "]/a";
		int i = 1;
		while (isElementPresent_xpath(p1 + i + p2)) {
			String text = driver.findElement(By.xpath(p1 + i + p2))
					.getText();
			System.out.println(text);
			if (("Sign out").equals(text)) {
				driver.findElement(By.xpath(p1 + i + p2)).click();
			}

			i++;
		}

	}
	
	
	
	public static void selectMenu(String menus)
	{
		driver.findElement(By.xpath("//*[@id='mCSB_1_container']/ul[1]/li/div")).click();
		WebElement box =driver.findElement(By.xpath("//*[@id='mCSB_1_container']/ul[1]/li"));
List<WebElement> all =box.findElements(By.tagName("a"));
System.out.println(all.get(2).getText());
for(int i =0;i<=all.size()-1;i++)
{
	if((all.get(i).getText()).equals(menus))
	{
		all.get(i).click();
	}
}
	}


	
	//BeforeTest
	public  void startreport(){
		Date d=new Date();
		String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
		String reportPath = LOG_PATH + fileName;

		extentReports = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);

		
		extentReports.loadConfig(new File(System.getProperty("user.dir")+"\\ReportsConfig.xml"));
		// optional
		extentReports.addSystemInfo("Selenium Version", "2.53.0").addSystemInfo(
				"Environment", "QA");
	}

//@Test
	 public  void passTest(){
		 //extent.startTest("TestCaseName", "Description")
		 //TestCaseName – Name of the test
		 //Description – Description of the test
		 //Starting test
		 extentTest = extentReports.startTest("passTest");
		 Assert.assertTrue(true);
		 //To generate the log when the test case is passed
		 extentTest.log(LogStatus.PASS, "Test Case Passed is passTest");
		 }
	 
	 public  void failTest(){
		 extentTest = extentReports.startTest("failTest");
		 Assert.assertTrue(false);
		 extentTest.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
		 }
	 
	// @Aftermethod
	 public   void getResult(ITestResult result){
		 if(result.getStatus() == ITestResult.FAILURE){
		 extentTest.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		 extentTest.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		 }else if(result.getStatus() == ITestResult.SKIP){
		 extentTest.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		 }
		 // ending test
		 //endTest(logger) : It ends the current test and prepares to create HTML report
		 extentReports.endTest(extentTest);
		 }

	//@Aftertest 
	 public  void endReport(){
		 // writing everything to document
		 //flush() - to write or update test information to your report. 
		                extentReports.flush();
		                //Call close() at the very end of your session to clear all resources. 
		                //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		                //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		                //Once this method is called, calling any Extent method will throw an error.
		                //close() - To close all the operation
		                extentReports.close();
		    }

}
