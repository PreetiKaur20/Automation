package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import BasePage.TestBase;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TestUtil extends TestBase {

	public void takeScreenShot() {
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_")
				.replace(" ", "_")
				+ ".png";
		String filePath = FBConstants.REPORTS_PATH + "\\screenshots\\"
				+ screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(filePath));

	}

	// Change made in isSkip
	public static boolean isSkip(String testCase) {
		for (int i = 2; i <= datatable.getRowCount("TestCases"); i++) {
			if (datatable.getCellData("TestCases", "TCID", i).equals(testCase)) {
				if (datatable.getCellData("TestCases", "Runmode", i)
						.equals("Y")) {
					return false;
				} else {
					return true;
				}
			}

		}

		return false;
	}

	public static Object[][] getData(String sheetName) {
		// return test data;
		// read test data from xls

		int rows = datatable.getRowCount(sheetName) - 1;
		if (rows <= 0) {
			Object[][] testData = new Object[1][0];
			return testData;

		}
		rows = datatable.getRowCount(sheetName); // 3
		int cols = datatable.getColumnCount(sheetName);
		System.out.println("total rows -- " + rows);
		System.out.println("total cols -- " + cols);
		Object data[][] = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = datatable.getCellData(sheetName,
						colNum, rowNum);
			}
		}

		return data;

	}

	// make zip of reports
	public static void zip(String filepath) {
		try {
			File inFolder = new File(filepath);
			File outFolder = new File("Reports.zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data = new byte[1000];
			String files[] = inFolder.list();
			for (int i = 0; i < files.length; i++) {
				in = new BufferedInputStream(new FileInputStream(
						inFolder.getPath() + "/" + files[i]), 1000);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void selectMenu(String menus) {
		driver.findElement(By.xpath("//*[@id='mCSB_1_container']/ul[1]/li/div"))
				.click();
		WebElement box = driver.findElement(By
				.xpath("//*[@id='mCSB_1_container']/ul[1]/li"));
		List<WebElement> all = box.findElements(By.tagName("a"));
		System.out.println(all.get(2).getText());
		for (int i = 0; i <= all.size() - 1; i++) {
			if ((all.get(i).getText()).equals(menus)) {
				all.get(i).click();
			}
		}
	}

	// BeforeTest
	public void startreport() {
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_")
				+ ".html";
		String reportPath = LOG_PATH + fileName;

		extentReports = new ExtentReports(reportPath, true,
				DisplayOrder.NEWEST_FIRST);

		extentReports.loadConfig(new File(System.getProperty("user.dir")
				+ "\\ReportsConfig.xml"));
		// optional
		extentReports.addSystemInfo("Selenium Version", "2.53.0")
				.addSystemInfo("Environment", "QA");
	}

	public void SelectedDate(String date) {

		String datevale[] = date.split("\\.");
		String expectedDate = removeLeadingZeroes(datevale[0]);
		for (int i = 1; i <= 4; i++) {
			String part1 = "//*[@id='ui-datepicker-div']/table/tbody/tr[";
			String part2 = "]";
			for (int j = 1; j <= 7; j++) {
				String data1 = part1 + i + part2 + "/td[";
				String data2 = "]/a";
				if (isElementPresent_xpath(data1 + j + data2)) {
					String actualvalue = driver.findElement(
							By.xpath(data1 + j + data2)).getText();
					System.out.println(actualvalue);
					if (actualvalue.equals(date)) {
						driver.findElement(By.xpath(data1 + j + data2)).click();
						return;
					}
				}}}

			
		}
	
	
	
	

	public void selectDatewithRow(String date, String rownumber) {
		String rowvalue[] = rownumber.split("\\.");
		String row = rowvalue[0];
		int i = Integer.parseInt(row);
		String datevale[] = date.split("\\.");
		String expectedDate = datevale[0];
		String part1 = "//*[@id='ui-datepicker-div']/table/tbody/tr[";
		String part2 = "]";
		for (int j = 1; j <= 7; j++) {
			String data1 = part1 + i + part2 + "/td[";
			String data2 = "]/a";
			if (isElementPresent_xpath(data1 + j + data2)) {
				String actualvalue = driver.findElement(
						By.xpath(data1 + j + data2)).getText();
				System.out.println(actualvalue);
				if (actualvalue.equals(date)) {
					driver.findElement(By.xpath(data1 + j + data2)).click();
					return;
				}
			}
		}

	}

	// Selecting Current Date like 06,12
	public static String seletCurrentDay(int numberOfDaysAddtoCurrentDay) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date currentDate = new Date();
	
		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		//adding number of days
		c.add(Calendar.DATE, numberOfDaysAddtoCurrentDay);

		// convert calendar to date
		Date currentDatePlusOne = c.getTime();
		

		String currentdate = dateFormat.format(currentDatePlusOne);
		String dayvalues[] = currentdate.split("/");

		return removeLeadingZeroes(dayvalues[2]);
	}
	
	

	// Selecting Current date
	public static String seletCurrentDate(int numberOfDaysAddtoCurrentDay) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = new Date();
	
		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		//adding number of days
		c.add(Calendar.DATE, numberOfDaysAddtoCurrentDay);

		// convert calendar to date
		Date currentDatePlusOne = c.getTime();
		

		String currentdate = dateFormat.format(currentDatePlusOne);
	

		return currentdate;
	}
	
	//Remove leading Zeros
	public static String removeLeadingZeroes(String value) {
	     while (value.indexOf("0")==0)
	         value = value.substring(1);
	          return value;
	}
}
