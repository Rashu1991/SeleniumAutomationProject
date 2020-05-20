package com.qa.flipkart.tests_smoke;

import org.testng.TestNG;

import com.qa.flipkart.extentreportlistener.ExtentReportListener;
import com.qa.flipkart.retry.MyTransformer;

public class TestRunnerSmoke {
	
	public static void main(String []args) {
		
		ExtentReportListener ext = new ExtentReportListener();
		MyTransformer t = new MyTransformer();
		TestNG testNg = new TestNG();
		testNg.setTestClasses(new Class[] {LoginPageTest.class,HomePageTest.class,GroceryPageTest.class,MyProfilePageTest.class,
				OffersPageTest.class});
		testNg.addListener(ext);
		testNg.addListener(t);
		testNg.run();
		
	
		
	}

}
