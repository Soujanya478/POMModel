package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	WebDriver driver;
	ElementUtil eleUtil;

	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By register = By.linkText("Register");
	private By logout = By.linkText("Logout");
	
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']");
	
	private By agreeButton = By.xpath("//input[@name='agree']");
	private By continueButton = By.xpath("//input[@value='Continue']");
	
	private By Successmsg = By.cssSelector("div#content h1");
	
	public Boolean getRegistration(String firstname, String lastname, String email,String telephone, String password, String subscribe ) {		
		
		eleUtil.waitForElementPresence(this.firstname, constants.MIN_WAIT_TIMEOUT, constants.POLLING_TIME).sendKeys(firstname);
		eleUtil.doSendKeys(this.lastname, lastname);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone,telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		if(subscribe.contains("yes")) {
			eleUtil.doClick(subscribeYes);
		}else
		{
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeButton);
		eleUtil.doClick(continueButton);
		
		String Actual_Mesg = eleUtil.waitForElementVisible(Successmsg, constants.MIN_WAIT_TIMEOUT, constants.POLLING_TIME).getText();
		System.out.println(Actual_Mesg);
		if(Actual_Mesg.contains(constants.REGISTRATION_SUCCESSFUL_MESSAGE)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(register);
			return true;
		}
		return false;
	}

}
