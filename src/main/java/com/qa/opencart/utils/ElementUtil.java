package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

//import CustomException.frameworkException;

public class ElementUtil {
	
	private WebDriver driver;
	private Actions action;
	private JavaScriptUtil js;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		action = new Actions(driver);
		js = new JavaScriptUtil(driver);
	}
	@Step("....Getting the Element Locator.....Locator : {0}")
	public WebElement getElement(By locator)	{
		
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			js.flash(element);
		}
		return element;
	}
	
	
	public  boolean getDisabledElementCheck(By locator)
	{
		String disabled_value =  getElement(locator).getAttribute("disabled");
		if(disabled_value.equals("true")){
			return true;
		}return false;
	}
	
	@Description("Value passing to field is : {1} to the locator : {0}")
	public void doSendKeys(By locator,String value) { //Sendkeys by calling getElement method and passing sendkeys value and locator
		
		if(value==null) {
			System.out.println("Value cannot be null using sendkeys method");
		//	throw new frameworkException("CANNOTBENULL");
		}
		 getElement(locator).sendKeys(value);  
	}
	
	
	public void doClick(By locator)     //click method by calling getElement method by passing locator
	{
		getElement(locator).click();
	}
	
	public String doElementGetText(By locator) {
		String eleText = getElement(locator).getText();
		System.out.println("Element Text ===>  "+eleText);
		return eleText;
		
	}
	
	public  WebElement getLinkTextElement(String linkText)
	{
		return driver.findElement(By.linkText(linkText));
	}
	
	
	public Boolean getElementIsDisplayed(By locator) //checking element whether displaying or not with the help of find Element method
	{
		return getElement(locator).isDisplayed();
	}
	

	public String getElementText(By locator) //getText method using getElement method
	{
		return getElement(locator).getText();
	}
	
	
	public  String getAttributeText(By locator, String AttrValue)
	{
		return getElement(locator).getAttribute(AttrValue);
	}	
	
	
	public int getElementsCount(By locator)
	{
		return getElements(locator).size();
	}
	
	
	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	
	public List<String> getElementsTextList(By locator)
	{
		List<WebElement> eleList = getElements(locator);
		List<String> elementsList = new ArrayList<String>();
		for(WebElement e: eleList)
		{
			String text = e.getText();
			if(!text.isEmpty()) {
				elementsList.add(text);	
			}
		} return elementsList;
	}

	public void getLinkElements(By locator, String value)
	
	{
		List<WebElement> TotalLinks = getElements(locator);
		System.out.println(TotalLinks.size());
		for(WebElement e : TotalLinks) {
			String links = e.getText();
			System.out.println(links);
			if(links.equals(value)) {
				e.click();
				break;
			}
		}
		
	}
	
	public void doSearch(By SearchLocator, String SearchKey,By Suggestions_SearchLocator, String SearchValue) throws InterruptedException
	{
		doSendKeys(SearchLocator,SearchKey);
		Thread.sleep(5000);
		List<WebElement> suggestions_List = getElements(Suggestions_SearchLocator);
		System.out.println(suggestions_List.size());
		
		for(WebElement e : suggestions_List)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.contains(SearchValue))
			{
				e.click();
				break;
			}
		}
	}
	
	//*************dropdown Utils *****************************//
	
	public  void selectdropdownwithIndex(By locator, int index)
	{		
		if(index<0)
		{
			System.out.println("Index value cannot be less than zero... Please pass right Index");
			return;
		}
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public  void selectdropdownbyvalue(By locator,String value )
	{
		
		if(value==null)
		{
			System.out.println("Null cannot be taken as value.. Please pass the correct value");
		}
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public  void selectdropdownbyvisibleText(By locator,String VisibleText )
	{	
		if(VisibleText==null)
		{
			System.out.println("Null cannot be taken as value.. Please pass the correct value");
		}
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(VisibleText);
	}		
	
	public  List<String> doSelectDropdownAllOptions(By locator)
	{
		Select select = new Select(driver.findElement(locator));
		List<WebElement> allOptions = select.getOptions();
		List<String> OptionsList = new ArrayList<>();
		for(WebElement e: allOptions)
		{
			String text = e.getText();
			System.out.println(text);
			OptionsList.add(text);
		}
		return OptionsList;
	}
	
	public int getDropDownOptionsCount(By locator)
	{
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}
	
	public  void selectdropdownusinggetoptions(By locator, String Value)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for(WebElement e :optionsList)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.equals(Value)){
				e.click();
				break;
			}

		}
	}
	public  void SelectDropdownValueUsingLocator(By locator, String Dropdownvalue)
	{
	List<WebElement> optionsList = getElements(locator);
		
		for(WebElement e: optionsList)
		{
			String text = e.getText();
			if(text.equals(Dropdownvalue));
			e.click();
			break;
		}
	}
	
	//****************************Actions Utils ***********************//
	
	
	public  void doActionsClick(By locator)
	{
		action.click(getElement(locator)).perform();;
	}
	
	public  void doActionsSendKeys(By locator,String value)
	{
		action.sendKeys(getElement(locator),value).perform();
	}
	
	public  void rightClickElement(By rightClickLocator, String OptionValue)
	{
		action.contextClick(getElement(rightClickLocator)).perform();
		By optionvalue = By.xpath("//*[text()='"+OptionValue+"']");	
		doClick(optionvalue);
		
	}
	
	/**
	 * This Method will handle upto two levels
	 * @param Level1MenuLocator
	 * @param Level2MenuLocator
	 */

	public  void MultiLevelMenuHandling(By Level1MenuLocator, By Level2MenuLocator)
	{
		action.moveToElement(getElement(Level1MenuLocator)).perform();
		doClick(Level2MenuLocator);
	}
	
	/**
	 * This method allows upto 4 levels
	 * @param Level1locator
	 * @param level2
	 * @param level3
	 * @param level4
	 * @throws InterruptedException
	 */
	
	public  void MultiLevelMenuHandling(By Level1locator, String level2, String level3, String level4) throws InterruptedException
	{
		Thread.sleep(1000);
		action.moveToElement(getElement(Level1locator)).perform();
		Thread.sleep(1000);
		action.moveToElement(getLinkTextElement(level2)).perform();
		Thread.sleep(1000);
		action.moveToElement(getLinkTextElement(level3)).perform();
		Thread.sleep(1000);
		getLinkTextElement(level4).click();
	}
	
	public  void MultiLevelMenuHandling(By Level1locator, String level2, String level3) throws InterruptedException
	{
		Thread.sleep(1000);
		action.moveToElement(getElement(Level1locator)).perform();
		Thread.sleep(1000);
		action.moveToElement(getLinkTextElement(level2)).perform();
		Thread.sleep(1000);
		getLinkTextElement(level3).click();
	}
	
	/********************** Wait Utils**********************/
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. 
	 * This does not necessarily mean that the element is visible.
	 * @param locator
	 * @param timeout
	 * @return
	 */

	public WebElement waitForElementPresence(By locator, int timeout, int pollingTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout),Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));		
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	public WebElement waitForElementVisible(By locator, int timeout, int pollingTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout),Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param timeout
	 * @param Locator
	 * @return
	 */
	
	public List<WebElement> waitForElementsPresence(By Locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locator));
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. 
	 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0
	 * @param timeout
	 * @param Locator
	 * @return
	 */
	
	public List<WebElement> waitForElementsVisible(By Locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
	}

	
	/**
	 * An expectation for checking that the title contains a case-sensitive substring
	 * @param titleValue
	 * @param timeout
	 * @return
	 */
	
	public  String waitForTitleContains(String titleValue, int timeout)  {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
				if(wait.until(ExpectedConditions.titleContains(titleValue))) 	{
					return driver.getTitle();
				} else {
					System.out.println("Title is not available with the name " +titleValue);
					return null ;
				}
		} catch(Exception e)	{
			System.out.println("Title is not available with the name " +titleValue);
			return null;
		}
  }
	/**
	 * An expectation for checking that the title contains a case-sensitive substring
	 * @param titleValue
	 * @param timeout
	 * @return
	 */
	@Description("Waiting for title.... Title is : {0} and timout is {1}")
	public  String waitforTitleIs(String titleValue, int timeout)
	{
		try {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.titleIs(titleValue))) {
			return driver.getTitle();
		} else {
			System.out.println("Title is not present " +titleValue);
			return null;
		}
		}
		catch(Exception e) {
			System.out.println("Title is not present " +titleValue);
			return null;
		}
		}
	
	/**
	 * An expectation for the URL of the current page to contain specific text.
	 * @param URLFraction
	 * @param timeout
	 * @return
	 */
	
	public  String waitForURLContains(String URLFraction, int timeout)  {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
				if(wait.until(ExpectedConditions.urlContains(URLFraction))) 	{
					return driver.getCurrentUrl();
				} else {
					System.out.println("Title is not available with the name " +URLFraction);
					return null ;
				}
		} catch(Exception e)	{
			System.out.println("Title is not available with the name " +URLFraction);
			return null;
		}
  }
	/**
	 * An expectation for the URL of the current page to be a specific url.
	 * @param URLFraction
	 * @param timeout
	 * @return
	 */
	
	public  String waitForURLExactToBe(String URLFraction, int timeout)  {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
				if(wait.until(ExpectedConditions.urlToBe(URLFraction))) 	{
					return driver.getCurrentUrl();
				} else {
					System.out.println("Title is not available with the name " +URLFraction);
					return null ;
				}
		} catch(Exception e)	{
			System.out.println("Title is not available with the name " +URLFraction);
			return null;
		}
  }
	
	/**
	 * An expectation for the JSAlert appeared on the current page.
	 * @param timeOut
	 * @return
	 */
	public Alert WaitForJsAlert(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.alertIsPresent());		
	}
	
	public  Boolean WaitforNumberOfBrowserWindows(int timeout, int windowsCount)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(windowsCount));
	}
	

	public void waitforFrameLocator(int timeout, By frameLocator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public void waitforFrameIndex(int timeout, int frameIndex)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public void waitforFrameNameorID(int timeout, String frameNameorID)  {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameorID));

	}
	
	public void waitforFrameElement(int timeout, WebElement frameElement)	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param timeout
	 * @param locator
	 */
	public  void clickElementWhenReady(By locator, int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	/*********************************Fluent Waits ************************/
	
	public WebElement WaitForElementVisibleWithFluentWait(int timeout, int pollingtime, By locator)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoSuchElementException.class)
				.withMessage("--------------------Element is not found... TimeOut is Done-------------- ");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement WaitForElementPresenceWithFluentWait(int timeout, int pollingtime, By locator)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoSuchElementException.class)
				.withMessage("--------------------Element is not found... TimeOut is Done-------------- ");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public Alert WaitForAlertPresenceWithFluentWait(int timeout, int pollingtime, By locator)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoAlertPresentException.class)
				.withMessage("--------------------Element is not found... TimeOut is Done-------------- ");
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void WaitForFramePresenceWithFluentWait(int timeout, int pollingtime, String FrameNameOrId)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoSuchFrameException.class)
				.withMessage("--------------------Frame is not found... TimeOut is Done-------------- ");
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameNameOrId));
	}
	public void waitForElementandEnterTheValue(int pollingTime, By locator, String value, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.pollingEvery(Duration.ofSeconds(pollingTime))
		.ignoring(NoSuchElementException.class)
		.withMessage("Element is not found ")
		.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(value);		
	}
	
	public void waitForElementandClick(int pollingTime, By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.pollingEvery(Duration.ofSeconds(pollingTime))
		.ignoring(NoSuchElementException.class)
		.withMessage("Element is not found ")
		.until(ExpectedConditions.presenceOfElementLocated(locator)).click();;		
	}

}

