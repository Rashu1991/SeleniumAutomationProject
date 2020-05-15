package com.qa.flipkart.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.flipkart.testbase.TestBase;
import com.qa.flipkart.testiutil.TestUtil;

import constantsUtil.Constants;

public class HomePage extends TestBase {

	URL url;
	WebElement groceryLink,offersLink;

	@FindBy(name = "q")
	WebElement srchBar;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement srchBtn;

	@FindBy(xpath = "//div[@class='_1jA3uo']/child::div")
	List<WebElement> menuBarList;

	@FindBy(xpath = "//div/ul[@class='RlwYhr undefined']/child::li/a/div[text()='Logout']")
	WebElement logOutBtn;
	@FindBy(xpath="//div/ul[@class='RlwYhr undefined']/child::li/a/div[text()='My Profile']")
	WebElement myProfileLnk;
	
	@FindBy(tagName = "a")
	List<WebElement> links;
	@FindBy(xpath="//div[@class='_3zdbog _3Ed3Ub']/ul/child::li")
	List<WebElement> subMenuBarList;
	@FindBy(xpath="//div[@class='_3zdbog _3Ed3Ub']/ul/child::li/a/span[text()='Grocery']")
	WebElement groceryLnk;
	@FindBy(xpath="//div[@class='_3zdbog _3Ed3Ub']/ul/child::li/a/span[text()='Offer Zone']")
	WebElement offerZoneLnk;
	@FindBy(xpath="//div[@class='bhgxx2 col-12-12']")
	List<WebElement> listOfItems;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// Page Actions

	public void doSearchBar(String text) {
		//TestUtil.waitTillVisibilityOfElement(srchBar);
		srchBar.sendKeys(text);
		srchBtn.click();
	}

	public void doGetMenuBarList() {
		System.out.println(menuBarList.size());
		for (int i = 0; i < menuBarList.size(); i++) {
			System.out.println(menuBarList.get(i).getText());
			Actions action = new Actions(driver);
			action.moveToElement(menuBarList.get(i)).build().perform();

		}
	}

	public void doGetMenuBarList(int index) {
		
		Actions action = new Actions(driver);
		action.moveToElement(menuBarList.get(index)).build().perform();
	}

	public void logout() {
		Actions action = new Actions(driver);
		action.moveToElement(logOutBtn).build().perform();
		logOutBtn.click();
	}

	public void getAllLinksVerify() throws IOException {
		links.size();
		for (WebElement link : links) {

			String urlLink = link.getAttribute("href");

			try {
				url = new URL(urlLink);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(Constants.URL_CONN_TIMEOUT);
			urlConnection.connect();

			if (urlConnection.getResponseCode() == Constants.RES_STATUS_OK) {
				System.out.println(urlLink + " " + urlConnection.getResponseMessage());
			} else if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println("Link Broken " + urlLink + " " + urlConnection.getResponseMessage() + " "
						+ HttpURLConnection.HTTP_NOT_FOUND);

			}
		}
	}
	
	public void verifySubMenuBarList() {
		
		for(int i=0;i<subMenuBarList.size();i++) {
			if(subMenuBarList.get(i).getText().contentEquals("Grocery")){
				
			}
			else if(subMenuBarList.get(i).getText().contentEquals("Offer Zone")) {
				
			}
			else {
				Actions action = new Actions(driver);
				action.moveToElement(subMenuBarList.get(i)).build().perform();
			}	
				
			}
		}
	
	
	public GroceryPage verifyGroferSubMenu() {
		groceryLnk.click();
		return new GroceryPage();
		
		
	}
	public OffersPage verifyOfferZoneSubMenu() {
		offerZoneLnk.click();
		return new OffersPage();
	}
	
	public MyProfilePage clickOnMyAccounts_MyProfile() {
		TestUtil.moveToElement(driver, myProfileLnk);
		myProfileLnk.click();
		return new MyProfilePage();	
	}
	
	public void selectItemFromSearch() {
		for(int i=0;i<listOfItems.size();i++) {
			System.out.println(listOfItems.get(i).getText());
		}
		
	}
}
