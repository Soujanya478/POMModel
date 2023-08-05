package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class cartpage {
	
	By product = By.linkText("Macbook");
	
	public void clickProduct(By locator) {
		System.out.println("Click on product..");
	}

}
