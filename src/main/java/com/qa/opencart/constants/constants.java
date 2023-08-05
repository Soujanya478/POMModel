package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class constants {
	
	public static final String LOGIN_PAGE_TITLE= "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION="route=account/login";
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION="route=account/account";
	public static final int ACCOUNT_PAGE_HEADERS_COUNT =4;
	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final String REGISTRATION_SUCCESSFUL_MESSAGE = "Your Account Has Been Created!";

	//Waits
	
	public static final int MIN_WAIT_TIMEOUT = 2;
	public static final int MEDIUM_WAIT_TIMEOUT=4;
	public static final int MAX_WAIT_TIMEOUT=6;
	public static final int POLLING_TIME=2;
	
	//****************Sheet Names************//
	
	public static final String REGISTER_SHEET_NAME="register";
	
	

}
