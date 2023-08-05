package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void RegistrationSetUp() {
		registerpage = loginpage.doRegistration();
	}
	
	public String generateRandomEmailId() {
		return "opencart"+System.currentTimeMillis()+"@opencart.com";
	}
	
	@DataProvider
	public Object[][] GetUserRegData() {
		return new Object[][] {
			{"sam","tom","998833990","test@12367","yes"},
			{"som","nom","998833923","test@12367","no"},
			{"samy","tomy","998833890","test@15677","yes"}
		};
	}
	
	public Object[][] getExcelRegistrationData() {
		return ExcelUtil.getDataFromExcel(constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider="GetUserRegData")
	public void registrationTest(String firstname,String lastname,String telephone,String email,String subscribe) {
	Boolean Reg_status = registerpage.getRegistration(firstname, lastname, generateRandomEmailId(), telephone,email, subscribe);
	Assert.assertTrue(Reg_status);
	
}
}
