package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("100:Design for loginPage for open cart app")
@Story("200 : Implement login page features app for open cart app")
public class LoginPageTest extends BaseTest {

	@Description("This is Login Page title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginPageTitleTest() {
	String ActTitle=	loginpage.getLoginPageTitle();
	Assert.assertEquals(ActTitle,constants.LOGIN_PAGE_TITLE);	
	}
	
	@Description("This is login URL page test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageURLTest() {
	String url=	loginpage.getLoginPageURL();
	Assert.assertTrue(url.contains(constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Check forgot Password link.....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void IsForgotPwdlinkExistsTest() {
		Assert.assertTrue(loginpage.IsForgottenPwdLinkAvailable());	
	}
	
	@Description("Logout functionality testing......")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void loginTest()
	{
		accountsPage =loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accountsPage.isLogoutLinkExists(), true);
	}
	
	@Test(priority=4)
	public void newTest()
	{
		Assert.assertEquals(false, true);
	}
	
	
	@Description("Assertion failed check test.........")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4, enabled=false)
	public void AssertionFailTest()
	{
		Assert.assertEquals(false, true);
	}
}
