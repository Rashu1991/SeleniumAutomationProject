package com.qa.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.flipkart.testbase.TestBase;

public class LoginPage extends TestBase{

	@FindBy(xpath="//a[text()='Login']")
	WebElement btnLogin;
	@FindBy(xpath="//div[@class='_3Njdz7']//following::div/input[@type='text']")
	WebElement username;
	@FindBy(xpath="//div[@class='_3Njdz7']//following::div/input[@type='password']")
	WebElement password;
	@FindBy(xpath="//div[@class='_3Njdz7']//following::div/button[@type='submit']")
	WebElement loginButton;
	@FindBy(xpath="//a/span[contains(text(),'Forgot?')]")
	WebElement forgotLnk;
	@FindBy(xpath="//a/span[contains(text(),'Change?')]")
	WebElement changeLnk;
	@FindBy(xpath="//a/span[contains(text(),'Resend?')]")
	WebElement resendLnk;
	@FindBy(xpath="//span[contains(text(),'Please enter valid')]")
	WebElement inValidUsernameErrMsg;
	@FindBy(xpath="//span[contains(text(),'Please enter Password')]")
	WebElement enterPassErrMsg;
	@FindBy(xpath="//span[contains(text(),'Your username or password is incorrect')]")
	WebElement incorrectUserPassMsg;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//PageActions
	
	public HomePage login(String uname,String pass) {
		username.sendKeys(uname);
		password.sendKeys(pass);
		loginButton.click();
		return new HomePage();
	}
	
	public boolean loginForgotPassChangeEmail(String uname) {
		username.sendKeys(uname);
		if(forgotLnk.isEnabled()) {
		forgotLnk.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(changeLnk));
		changeLnk.click();
		return true;
		}
		return false;
	}
	
	public boolean loginForgotPassResendOTP(String uname) {
		username.sendKeys(uname);
		if(forgotLnk.isEnabled()) {
		forgotLnk.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(resendLnk));
		resendLnk.click();
		return true;
		}
		return false;
	}
	
	public String loginInvalidUsername(String uname) {
		
		username.sendKeys(uname);
		loginButton.click();
		return (inValidUsernameErrMsg.getText());
	}
	
	public String loginInvalidPass(String pass) {
		username.sendKeys(pass);
		loginButton.click();
		return (inValidUsernameErrMsg.getText());
	}
	
	public String loginValidUsernameBlankPass(String uname,String pass) {
		username.sendKeys(uname);
		password.sendKeys(pass);
		loginButton.click();
		return (enterPassErrMsg.getText());
		
	}
	
	public String loginInValidUsernameAndPass(String uname,String pass) {
		username.sendKeys(uname);
		password.sendKeys(pass);
		loginButton.click();
		return (incorrectUserPassMsg.getText());
		
	}
	
}
