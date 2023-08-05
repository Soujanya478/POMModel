package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("102 : Design for account page for open cart app ")
@Story("201 : Implementing the features of account page ")
public class AccountsPageTest extends BaseTest { 	


	@Description("This is Account page setup method......")
	@BeforeClass
	public void AccountPageSetUp() {
		accountsPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Description("Verifying the logout link existence")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void isLogoutLinkExists() {
		Assert.assertTrue(accountsPage.isLogoutLinkExists());
	}

	@Test
	public void AccountsPageHeadersCountTest() {
		int actualAccountsPageHeadersCount = accountsPage.AccountPageHeaderCount();
		System.out.println("Actual Header count ==>" + actualAccountsPageHeadersCount);
		Assert.assertEquals(actualAccountsPageHeadersCount, constants.ACCOUNT_PAGE_HEADERS_COUNT);
	}

	public void AccountsPageHeadersList() {
		List<String> ActualaccountsPageHeader = accountsPage.getAccountsPageHeader();
		Assert.assertEquals(ActualaccountsPageHeader, constants.ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@DataProvider
	public Object[][] searchData() {
		return new Object[][] { { "Macbook",3 }, { "Samsung",2 }, { "imac",1 } };
	}

	@Description("Navigating to search using this method....")
	@Severity(SeverityLevel.MINOR)
	@Test(dataProvider = "searchData")
	public void SearchTest(String searchKey, int expectedProductcount) {
		searchPage = accountsPage.doSearch(searchKey);
		int searchResultsCount = searchPage.SearchProductResultsCount();
		Assert.assertEquals(searchResultsCount, expectedProductcount);
	}

	@Test
	public void MyOrdersLabelTest() {
		Assert.assertEquals(accountsPage.MyOrdersLabel(), "My Orders");
	}

	@Test
	public void homeIconDisplayedTest() {
		Assert.assertTrue(accountsPage.verifyHomeIconDisplayed());
	}

	@Test
	public void AccountsPageURLTest() {
		String current_URL = accountsPage.VerifyURLAccountsPage();
		Assert.assertTrue(current_URL.contains(constants.ACCOUNT_PAGE_URL_FRACTION));
	}

	@Test
	public void AccountsPageTitleTest() {
		String title = accountsPage.VerifyTitleAccountsPage();
		Assert.assertEquals(title, constants.ACCOUNT_PAGE_TITLE);
	}
}
