package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil ;

	private By ProductResults = By.cssSelector("div.product-layout");
	
	
	
	public SearchResultsPage(WebDriver driver) {
	this.driver=driver;
	eleUtil= new	ElementUtil(driver);
	}
	
	public int SearchProductResultsCount() {
		return eleUtil.waitForElementsVisible(ProductResults, constants.MIN_WAIT_TIMEOUT).size();
	}
	
	public ProductInfoPage SearchProduct(String ProductName)
	{
		eleUtil.clickElementWhenReady(By.linkText(ProductName),constants.MIN_WAIT_TIMEOUT);
		return new ProductInfoPage(driver);
	}

}

