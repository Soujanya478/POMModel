package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	//Private By Locators
	private WebDriver driver;
	private By emailId = By.id("input-email");
	private By password=By.cssSelector("#input-password");
	private By LoginBtn = By.xpath("//input[@value='Login']");
	private By ForgottenPwdLink = By.linkText("Forgotten Password");
	private By Register = By.linkText("Register");
	private By LogoutBtn = By.xpath("//input[@value='Logout']");

	
	//public page constructors
	
	ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//public page methods
	@Step(".......getting login page title...........")
	public String getLoginPageTitle() {
		String title = eleUtil.waitforTitleIs(constants.LOGIN_PAGE_TITLE, constants.MIN_WAIT_TIMEOUT);
		System.out.println(title);
		return title;
	}
	@Step(".......getting login page URL...........")
	public String getLoginPageURL() {
		String url =eleUtil.waitForURLContains(constants.LOGIN_PAGE_URL_FRACTION, constants.MIN_WAIT_TIMEOUT);
		System.out.println(url);
		return url;
	}
	@Step(".......Checking forgot password link...........")
	public boolean IsForgottenPwdLinkAvailable() {
		return driver.findElement(ForgottenPwdLink).isDisplayed();
	}
	//Navigating to other page(Accounts page).. Hence responsible for returning the object of other page(Accounts page).
	@Step(".....Login with Username : {0} and password : {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("App credentials are : " +username+" : "+pwd);
		eleUtil.waitForElementVisible(emailId,constants.MIN_WAIT_TIMEOUT,constants.POLLING_TIME).sendKeys(username);;
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(LoginBtn);
		return new AccountsPage(driver);
		
	}
	
	@Step("Navigating to register page.....")
	public RegisterPage doRegistration() {
		eleUtil.doClick(Register);
		return new RegisterPage(driver);
	}
}
