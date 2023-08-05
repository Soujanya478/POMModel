package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Description;

public class AccountsPage {

    //Private By locators
	private WebDriver driver;
	private By My_Orders_Label=By.xpath("//div[@id='content']/h2[contains(text(),'My Orders')]");
	private By Account_headers = By.xpath("//div[@id='content']/h2");
	private By Home_Icon = By.cssSelector("ul.breadcrumb li");
	private By logout = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon =By.cssSelector("div#search button");
	
	private ElementUtil eleUtil;
	
	//public constructor
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	//public methods

public String VerifyTitleAccountsPage() {
	String title = eleUtil.waitforTitleIs(constants.ACCOUNT_PAGE_TITLE, constants.MIN_WAIT_TIMEOUT);
	return title;
}	
public String VerifyURLAccountsPage() {
	String url =  eleUtil.waitForURLContains(constants.ACCOUNT_PAGE_URL_FRACTION, constants.MIN_WAIT_TIMEOUT);
	System.out.println(url);
		return url;
	}

public Boolean isLogoutLinkExists() {
return eleUtil.getElementIsDisplayed(logout);
}


public Boolean verifyHomeIconDisplayed() {
	
	return eleUtil.getElementIsDisplayed(Home_Icon);
}

public String MyOrdersLabel() {
	WebElement My_Orders_Text = eleUtil.waitForElementPresence(My_Orders_Label, 10, 0);
	return My_Orders_Text.getText();
}
public List<String> getAccountsPageHeader() {
	List<WebElement> headersList = eleUtil.waitForElementsVisible(Account_headers,constants.MIN_WAIT_TIMEOUT);
	List<String> headerList = new ArrayList<String>();
	for(WebElement e :headersList)	{
		String HeaderTextList = e.getText();
		headerList.add(HeaderTextList);
	}
	System.out.println("Actual Headers are " +headerList);
	return headerList;
	
}

public int AccountPageHeaderCount() {
	return eleUtil.waitForElementsVisible(Account_headers, constants.MIN_WAIT_TIMEOUT).size();
}

@Description("Searching for products and navigating to search results page...")
public SearchResultsPage doSearch(String searchKey)
{
	WebElement SearchField = eleUtil.waitForElementVisible(search, constants.MIN_WAIT_TIMEOUT, 0);
	SearchField.clear();
	SearchField.sendKeys(searchKey);
	eleUtil.doClick(searchIcon);
	return new SearchResultsPage(driver);
	
}
}