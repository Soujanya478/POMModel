package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By ProductHeader = By.cssSelector("div#content h1");
	private By ProductImages = By.cssSelector("ul.thumbnails img");
	private By Qauntity = By.name("quantity");
	private By AddToCart = By.id("button-cart");
	private By ProductMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By ProductPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	Map<String,String>  productMap ;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue() {
		return eleUtil.doElementGetText(ProductHeader);

	}

	public int getProductImagesCount() {
		int actualProductImages = eleUtil.waitForElementsVisible(ProductImages, constants.MIN_WAIT_TIMEOUT).size();
		System.out.println("Total Product Images for " + getProductHeaderValue() + " " + actualProductImages);
		return actualProductImages;
	}

	private void getProductMetaData() {
		List<WebElement> MetaDataElements = eleUtil.waitForElementsVisible(ProductMetaData,
				constants.MIN_WAIT_TIMEOUT);
		//Map<String, String> metaMap = new HashMap<String, String>();
		for (WebElement e : MetaDataElements) {
			String MetaData = e.getText();
			String Key = MetaData.split(":")[0].trim();
			String Value = MetaData.split(":")[1].trim();
			productMap.put(Key, Value);
		}
	}

	private void getProductPricingData() {
		List<WebElement> priceList = eleUtil.waitForElementsVisible(ProductPricingData, constants.MIN_WAIT_TIMEOUT);
		//Map<String, String> priceMap = new HashMap<String,String>();
		String actPrice = priceList.get(0).getText().trim();
		String exTax = priceList.get(1).getText().split(":")[0].trim();
		String exTaxValue = priceList.get(1).getText().split(":")[1].trim();
		
		productMap.put("price", actPrice);
		productMap.put(exTax, exTaxValue);

	}
	
	public Map<String,String> getProductData() {
		//productMap = new HashMap<String,String>(); 
		//productMap = new LinkedHashMap<String,String>();  //for the insertion Order
		productMap = new TreeMap<String,String>(); // for Alphabetical order
		productMap.put("Product_Header", getProductHeaderValue());
		productMap.put("Product_Images", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPricingData();
		
		return productMap;
		
		
	}

}