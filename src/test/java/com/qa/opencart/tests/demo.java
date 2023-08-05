package com.qa.opencart.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ElementUtil;

public class demo extends BaseTest {
	
	ElementUtil eleUtil;
	
	@BeforeClass
	public void demoSetUp() {
		driver.get("https://classic.crmpro.com/");
		eleUtil= new ElementUtil(driver);
	}
	
	@Test
	public void loginTest() {
		eleUtil.doSendKeys(By.name("username"), "automationTest");
		eleUtil.doSendKeys(By.name("password"), "automationTest");
		eleUtil.doClick(By.xpath("//input[@value='Login']"));
	}

}
