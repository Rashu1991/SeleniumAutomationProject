package com.qa.flipkart.tests_smoke;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.pages.MyProfilePage;
import com.qa.flipkart.testbase.TestBase;
import com.qa.flipkart.testiutil.TestUtil;

import constantsUtil.Constants;

public class MyProfilePageTest extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	MyProfilePage myProfilePage;
	public static String sheetName="userData";

	public MyProfilePageTest() {
		// TODO Auto-generated constructor stub

		super();
	}


	@BeforeMethod
	public void setUp() {
		TestBase.initialize("chrome");
		TestBase.launchUrl();
		homePage = new HomePage();
		loginPage = new LoginPage();
		myProfilePage = new MyProfilePage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		TestUtil.waitForPageLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homePage.doGetMenuBarList(0);
		homePage.clickOnMyAccounts_MyProfile();

	}

	@Test
	public void verifyMyProfilePageTitleTest() {

		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);
		System.out.println();
		Assert.assertEquals(driver.getTitle(), Constants.MYPROFILE_PAGE_TITLE, "My Profile Page title not verified");

	}

	@Test
	public void verifyPersonalInfoclickOnEdit() {
		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);
		System.out.println();

		boolean isClicked = myProfilePage.clickOnPersonalInfoEdit();
		Assert.assertTrue(isClicked, "Not clicked on edit link");
	}

	@Test
	public void enterFirstNameTest() {

		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);

		boolean isClicked = myProfilePage.clickOnPersonalInfoEdit();
		Assert.assertTrue(isClicked, "Not clicked on edit link");

		boolean isEntered = myProfilePage.enterFirstNamePersonalInfo("Shobhit");
		Assert.assertTrue(isEntered, "First Name not entered");

		myProfilePage.clickOnCancelLnkPersonalInfo();
	}

	@Test
	public void enterLastNameTest() {

		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);

		boolean isClicked = myProfilePage.clickOnPersonalInfoEdit();
		Assert.assertTrue(isClicked, "Not clicked on edit link");

		boolean isEntered = myProfilePage.enterLastNamePersonalInfo("-");
		Assert.assertTrue(isEntered, "Last Name is not entered");
		myProfilePage.clickOnCancelLnkPersonalInfo();
	}


	@Test
	public void verifyClickOnEditEmailAddLnk() {

		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);

		boolean isDone = myProfilePage.clickOnEditEmailAddLnk(1);
		Assert.assertTrue(isDone, "Email add edit clicked and values in text box is entered");

		myProfilePage.clickOnCancelLnkEmailAdd();

	}

	@Test
	public void clickOnManageAddressesLinkTest() {
		boolean isClicked=myProfilePage.clickOnManageAddressesLink();
		Assert.assertTrue(isClicked,"Not clicked on Manage addresses link");
		
	}
	
	
	@DataProvider(name="getTestData")
	public static Object[][] getTestData(){
		try {
		Object [][]data=TestUtil.getData(sheetName);
		return data;
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			return null;
		}
	}
	
	@Test(dataProvider="getTestData")
	public void addAddressTest(String name,String mobile,String pincode,String locality,String address,
			String city,String state) {
		myProfilePage.clickOnManageAddressesLink();
		myProfilePage.addAddress(name, mobile, pincode, locality, address, city, state);

	}
	
	@AfterMethod
	public void tearDown() {
		TestBase.close();
	}

}