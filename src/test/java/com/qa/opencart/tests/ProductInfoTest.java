package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Feature("Product Search")
@Epic("103: Design for product page for opencart app")
@Story("203 : Implementation of product page for open cart")
public class ProductInfoTest extends BaseTest{
	
@Feature("Product Search")
@Description("This is product info Setup page....")
@BeforeClass
public void ProductInfoPageSetup() {
	accountsPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
}

@DataProvider
public Object[][] ProductData() {
	return new Object[][] {
		{"Macbook","MacBook Pro"},{"Macbook","MacBook Air"},{"imac","iMac"},{"samsung","Samsung SyncMaster 941BW"},{"samsung","Samsung Galaxy Tab 10.1"}
	};
}
@Description("Searching for product header with the search key :{0} and product {1}")
@Test(dataProvider="ProductData")
public void ProductHeaderTest(String SeachKey, String ProductName) {
	searchPage = accountsPage.doSearch(SeachKey);
	prodInfoPage = searchPage.SearchProduct(ProductName);
	String ActualproductHeaderValue = prodInfoPage.getProductHeaderValue();
	Assert.assertEquals(ActualproductHeaderValue,ProductName);
}

@DataProvider
public Object[][] SearchProductsCount() {
	return new Object[][] {
		{"Macbook","MacBook Pro",4},
		{"Macbook","MacBook Air",4},
		{"imac","iMac",3},
		{"samsung","Samsung SyncMaster 941BW",1},
		{"samsung","Samsung Galaxy Tab 10.1",7}
	};
}

@Test(dataProvider="SearchProductsCount")
 	public void ProductImageCountTest(String SeachKey, String ProductName, int ExpectedproductImagesCount) {
	searchPage = accountsPage.doSearch(SeachKey);
	prodInfoPage = searchPage.SearchProduct(ProductName);
	int ActualproductImagesCount = prodInfoPage.getProductImagesCount();
	Assert.assertEquals(ActualproductImagesCount, ExpectedproductImagesCount);
 	}

@Test
public void productInfoTest() {
	searchPage = accountsPage.doSearch("MacBook");
	prodInfoPage  = searchPage.SearchProduct("MacBook Pro");
	Map<String, String> productActualData = prodInfoPage.getProductData();
	System.out.println(productActualData);
	
	SoftAssert.assertEquals(productActualData.get("Brand"), "Apple");
	SoftAssert.assertEquals(productActualData.get("Availability"), "In Stock");
	SoftAssert.assertEquals(productActualData.get("price"), "$2,000.00"); 
	SoftAssert.assertAll();
	
}

}
