package tests;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.LoginPage;
import util.DataUtil;
import util.TestUtil;
import BasePage.TestBase;

public class LoginTest extends TestBase {
	TestUtil report = new TestUtil();

	@BeforeTest
	public void isSkipped() {
		openBrowser();
		if (!DataUtil.isTestExecutable(datatable, "LoginTest")) {
			throw new SkipException("Runmode set to No");
			}
		System.out.println("Befor Test");
	
	}

	@BeforeMethod
	public void start() {
		System.out.println("before method");
		report.startreport();

	}

	@Test(dataProvider = "getData", priority = 1)
	public void testLogin(Hashtable<String, String> data)
			throws InterruptedException {

		LoginPage login = new LoginPage();
		PageFactory.initElements(driver, login);
		login.dologin(data.get("Username"), data.get("Password"));
	

	}

	@Test( priority = 2)
	public void dologout(){

		LoginPage login = new LoginPage();
	PageFactory.initElements(driver, login);
		login.doLogout();
	}
	
	

	@AfterTest
	public void aftertest() {
System.out.println("After Test");
driver.quit();
	}

	@AfterMethod
	public void aftermethod() {
		System.out.println("after method");

		extentReports.endTest(extentTest);

		extentReports.flush();
		extentReports.close();
		

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "LoginTest");
	}

}
