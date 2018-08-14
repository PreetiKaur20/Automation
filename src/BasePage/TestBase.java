package BasePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import util.FBConstants;
import util.Xls_Reader;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.Wait;

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
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
			driver.get("http://PreetiK:Happy@123@10.1.2.85/OMH/");
			// driver.get("http://10.1.2.85/aspire");

		} else {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\PreetiK\\workspace\\AutomationFinal\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			driver.get("http://PreetiK:Happy@123@10.1.2.85/OMH/");
			// navigate to Approval
			driver.get(FBConstants.Approval);

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

	/*
	 * public void waitForPageToLoad() { wait(1); JavascriptExecutor js =
	 * (JavascriptExecutor) driver; String state = (String)
	 * js.executeScript("return document.readyState");
	 * 
	 * while (!state.equals("complete")) { wait(2); state = (String)
	 * js.executeScript("return document.readyState"); } }
	 */

	public void wait(int timeToWaitInSec) {
		try {
			Thread.sleep(timeToWaitInSec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void implicitwait(int time, String id) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}

	public void WaitFor(WebDriver driver, By by) {
		WebDriverWait wait1 = new WebDriverWait(driver,1000);
		wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	
}
