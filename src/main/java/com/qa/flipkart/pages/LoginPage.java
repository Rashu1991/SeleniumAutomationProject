package com.qa.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
	
}
