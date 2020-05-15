package com.qa.flipkart.testiutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.flipkart.testbase.TestBase;

public class TestUtil extends TestBase {

	public static FileInputStream fis;
	public static Workbook book;public static Sheet sheet;
	public static String testSheetPath="C:\\Users\\Mudit\\eclipse-workspace\\POMAutomationPractice\\src\\main\\java\\com\\qa\\flipkart\\testdata\\testData.xlsx";
	public static Actions action;
	public Select sel;
	public static void waitForPageLoad(WebDriver driver) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(30000))
				.pollingEvery(Duration.ofMillis(5000))
				.ignoring(NoSuchElementException.class);

		boolean loaded=wait.until(new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				System.out.println("Interval time " + new Date());
				
				((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				System.out.println("Interval time " + new Date());
				return true;
			
				
			}
		});
		
		if(loaded) {
			System.out.println("Page is fully loaded");
		}

	}
	
	
	public static String getScreenshotOfEntirePage() throws IOException  {
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDirectory =System.getProperty("user.dir");
	
			String destination = "currentDirectory+\"/screenshotsPage/\"+System.currentTimeMillis()+\".png\"";
			FileUtils.copyFile(srcFile, new File(destination));
			return destination;
	}
	

	
	public static void getScreenshotOfElement(WebElement ele) {
		File srcFile=ele.getScreenshotAs(OutputType.FILE);
		String currDir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(srcFile, new File(currDir+"/screenshotsEle/"+System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public static Object[][] getData(String sheetName) throws EncryptedDocumentException, IOException{
		
		fis = new FileInputStream(testSheetPath);
		
		book=WorkbookFactory.create(fis);
		sheet = book.getSheet(sheetName);
		Object [][]data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) {
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
			}
		}
		
		return data;
		
	}
	
	
	public static void moveToElement(WebDriver driver,WebElement ele) {
		action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		
	}
	
	// select by text from drop down
	public void selectFromDropDownByText(WebElement selElement,String text) {
		sel = new Select(selElement);
		sel.selectByVisibleText(text);
	}
	
	// select by value from drop down
	public void selectFromDropDownByValue(WebElement selElement,String value) {
		sel = new Select(selElement);
		sel.selectByValue(value);
	}
	
	// select by index from drop down
	public void selectFromDropDownByIndex(WebElement selElement,int index) {
		sel = new Select(selElement);
		sel.selectByIndex(index);
	}
	
	// Explicit wait to visibilityOf Element
	public static void waitTillVisibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	}
