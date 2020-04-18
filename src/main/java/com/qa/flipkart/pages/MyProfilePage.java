package com.qa.flipkart.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.flipkart.testbase.TestBase;

public class MyProfilePage extends TestBase{

	@FindBy(xpath="//div/span[text()='Edit']")
	WebElement editPersonalInfoLink;
	@FindBy(name="firstName")
	WebElement firstNamePersonalInfo;
	@FindBy(name="lastName")
	WebElement lastNamePersonalInfo;
	@FindBy(xpath="//div[@class='_2kN0A- row']/button[@type='submit']")
	WebElement saveBtn;
	@FindBy(name="gender")
	List<WebElement> radioGender;
	@FindBy(xpath="//div/span[text()='Cancel']")
	WebElement cancelLnkPersonalInfo;
	@FindBy(xpath="//div[@class='_3oYEid']/a[text()='Edit']")
	List<WebElement> editEmailAddLnk;
	@FindBy(name="email")
	WebElement emailTxtBox;
	@FindBy(xpath="//div/a[text()='Cancel']")
	WebElement cancelLnkEmailAdd;
	
	
	
	public MyProfilePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Page Actions
	public boolean clickOnPersonalInfoEdit() {
		if(editPersonalInfoLink.isEnabled()) {
		editPersonalInfoLink.click();
		return true;
	}
		return false;
		
	}
	
	public boolean enterFirstNamePersonalInfo(String fName) {
		if(firstNamePersonalInfo.isEnabled()) {
			firstNamePersonalInfo.clear();
			firstNamePersonalInfo.sendKeys(fName);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean enterLastNamePersonalInfo(String lName) {
		if(lastNamePersonalInfo.isEnabled()) {
			lastNamePersonalInfo.clear();
			lastNamePersonalInfo.sendKeys(lName);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean selectRadioBtnGender(String gender) {
		if(gender.contentEquals("Male")) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeSelected(radioGender.get(0)));
			radioGender.get(0).click();
			return true;
		}
		else if(gender.contentEquals("Female")) {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.elementToBeSelected(radioGender.get(1)));
			radioGender.get(1).click();
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clickOnCancelLnkPersonalInfo() {
		cancelLnkPersonalInfo.isEnabled();
		cancelLnkPersonalInfo.click();
	}
	
	public void clickOnCancelLnkEmailAdd() {
		cancelLnkEmailAdd.isEnabled();
		cancelLnkEmailAdd.click();
	}
	
	public boolean clickOnEditEmailAddLnk(int index) {
		
		if(index==1) {
		editEmailAddLnk.get(0).click();
		emailTxtBox.isEnabled();
		emailTxtBox.clear();
		emailTxtBox.sendKeys(prop.getProperty("username"));
		return true;
	}
	
	return false;
}
}
