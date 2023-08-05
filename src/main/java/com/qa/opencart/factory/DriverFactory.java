package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager OptionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * This is browser initialization method that returns driver
	 * 
	 * @param browserName
	 * @return
	 */

	public WebDriver initDriver(Properties prop) { // Passing the properties reference for getting the values from file
													// that has been loaded from config.properties

	      String browser = prop.getProperty("browser");	
		//String browser = System.getProperty("browser");
		System.out.println("You are logging into " + browser + " browser");

		highlight = prop.getProperty("highlight");

		OptionsManager = new OptionsManager(prop);

		switch (browser.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver(OptionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
			break;
		case "firefox":
			// driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
			break;
		case "edge":
			// driver = new EdgeDriver(OptionsManager.getEdgeOptions());
			tldriver.set(new EdgeDriver(OptionsManager.getEdgeOptions()));
			break;
		case "safari":
			// driver = new SafariDriver();
			tldriver.set(new SafariDriver());
			break;
		default:
			System.out.println("Please pass the right browser..." + browser);
			break;
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * This is initialization method of properties class
	 * 
	 * @return
	 */
	public Properties initProp() {	
		//mvn clean install -Denv="qa"
		FileInputStream ip =null;
		prop = new Properties();
		
		String envName = System.getProperty("env");
		
		System.out.println("The env you selected is "+envName);
		
	try {	
		if (envName == null) {
			System.out.println("Running the testcases in QA env as no env is selected");
			ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
		} else	{		
		switch (envName.toLowerCase().trim()) {
		case "qa":
			ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
			break;
		case "uat":
			ip=new FileInputStream("./src/test/resources/config/uat.config.properties");
			break;
		case "dev":
			ip=new FileInputStream("./src/test/resources/config/dev.config.properties");
			break;
		case "prod":
			ip=new FileInputStream("./src/test/resources/config/config.properties");
			break;
		default:
			System.out.println("Please pass the right environment to run the test...."+envName);
			break;
			}
		}
	}
		catch(FileNotFoundException e ) {
			e.printStackTrace();
		}
	try {
		prop.load(ip);
	} catch (IOException e) {
		e.printStackTrace();
	}
		return prop;
	}
	
	public static String getScreenshot(String methodName)
	{
		File filesrc = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+ methodName+"_" +System.currentTimeMillis()+ ".png";
		File filedestination = new File(path);
	
		try {
			FileHandler.copy(filesrc,filedestination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
