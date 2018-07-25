package BasePage;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import util.FBConstants;
import util.Xls_Reader;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static Properties config = null;
	public static Properties OR = null;
	public static WebDriver driver = null;

	public static Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	public static boolean loggedIn = false;
	public static Xls_Reader datatable = null;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;

	public static final String LOG_PATH = FBConstants.REPORTS_PATH + "\\WEB\\";

@BeforeSuite
	public void initialize() throws IOException {

		config = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\config\\config.properties");
		config.load(fp);

		// load my xpaths
		APPLICATION_LOGS.debug("Loading Object XPATHS");
		OR = new Properties();
		fp = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\config\\OR.properties");
		OR.load(fp);
		// initilize datatable
		datatable = new Xls_Reader(System.getProperty("user.dir")
				+ "\\src\\data\\Data.xlsx");
		System.out.println(config.getProperty("browserType"));

		// openBrowser();
	}

	public static void openBrowser() {
		if ((config.getProperty("browserType")).equals("Firefox")) {

			/*
			 * APPLICATION_LOGS.debug("Starting the driver"); ProfilesIni
			 * profile = new ProfilesIni(); FirefoxProfile pro =
			 * profile.getProfile("default"); driver = new FirefoxDriver(pro);
			 */
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			System.out.println(OR.getProperty("signinURL"));
			// driver.get("http://10.1.2.85/aspire");
			driver.get("http://PreetiK:Happy@123@10.1.2.39/aspire/");

		} else {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\PreetiK\\workspace\\AutomationFinal\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://PreetiK:Happy@123@10.1.2.39/aspire/");

		}
	}

	public static WebElement getObject(String xpathKey) throws IOException {

		try {
			return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
		} catch (Throwable t) {

		}
		return null;
	}

	public static boolean isElementPresent_xpath(String objectXpath) {

		int count = driver.findElements(By.xpath(objectXpath)).size();
		if (count == 0)
			return false;
		else
			return true;

	}

	public void reportFailure(String failureMessage) {
		extentTest.log(LogStatus.FAIL, failureMessage);
		// takeScreenShot();
		Assert.fail(failureMessage);
	}

/*	public void waitForPageToLoad() {
		wait(1);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String state = (String) js.executeScript("return document.readyState");

		while (!state.equals("complete")) {
			wait(2);
			state = (String) js.executeScript("return document.readyState");
		}
	}*/

	public void wait(int timeToWaitInSec) {
		try {
			Thread.sleep(timeToWaitInSec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
