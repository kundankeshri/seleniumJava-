package com.cerner.a11y.auto.core;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import com.deque.axe.AXE;
import com.deque.axe.AXE.Builder;

public class A11YCapableWebDriverEventListener extends AbstractWebDriverEventListener {

	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		
		try{
			testAccessibility(url,driver);
			System.out.println("Navigated to:'" + url + "'");
		  }
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("After navigating to: '" + driver.getCurrentUrl() + "'");
		
		
	}
	public void testAccessibility(String url, WebDriver driver) {
		URL scriptURL = null;
		try{
			scriptURL = this.getClass().getResource(("/resource/axe.min.js"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JSONObject responseJSON=	new AXE.Builder(driver,scriptURL).analyze(); 
		
		JSONArray violations = responseJSON.getJSONArray("violations");
		
		String strTest = AXE.report(violations);
		
		System.out.println("test"+strTest);
		
		JSONArray array = new JSONArray();  
		
		for(int k = 0 ;k <violations.length();k++ ){
			JSONObject json = new JSONObject();
			json.put("URL",url);
			json.put("help",violations.getJSONObject(k).get("help"));
			json.put("impact",violations.getJSONObject(k).get("impact"));
			json.put("helpUrl",violations.getJSONObject(k).get("helpUrl"));
			json.put("description",violations.getJSONObject(k).get("description"));
			JSONArray violationList = violations.getJSONObject(k).getJSONArray("nodes");
			JSONArray details = new JSONArray();
			for(int l = 0;l <violationList.length();l++){
				JSONObject json1 = new JSONObject();
				JSONObject obj = violationList.getJSONObject(l);
				json1.put("html",obj.get("html"));	
				json1.put("target",obj.getJSONArray("target").get(0));
				//json1.put("mesage",obj.getJSONArray("any").getJSONObject(0).get("message"));
				
				details.put(json1);
			}
			json.put("list",details);
			System.out.println("kk"+violationList.toString());
			array.put(json);
		}
		System.out.println("sanjay"+array.toString());
		
		
		
		
		String  url1 =  (String) responseJSON.get("url");
		String str[] = url1.split("/");
		System.out.println(str[str.length-1]);
        String url2 = "C:/Users/C15602/Desktop/AxeImp/A11YAutoEvaluator/AxeLogs/"+str[str.length-1];
		if (violations.length() == 0) {
			//assertTrue("No violations found", true);
			System.out.println("No Violations");
		} else {
			
			AXE.writeResults(url2, array);
			System.out.println("Violations:responseJSON"+url1);
			//assertTrue(AXE.report(violations), false);
		}
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
	//System.out.println("Value of the:" + element.toString()
			//	+ " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		//System.out.println("Element value changed to: " + element.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		//System.out.println("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver) {
		//System.out.println("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) {
		//System.out.println("Navigating forward to next page");
	}

	public void afterNavigateForward(String url,WebDriver driver) {
		System.out.println("Navigated forward to next page");
	}

	public void onException(Throwable error, WebDriver driver) {
		//System.out.println("Exception occured: " + error);
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		//System.out.println("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		//System.out.println("Found Element By : " + by.toString());
	}

	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
		System.out.println("beforeScript : " + script.toString());
	}

	public void afterScript(String script, WebDriver driver) {
		System.out.println("beforeScript : " + script.toString());
	}
	
	public String getCurrentPageURL(WebDriver driver){
		
		return driver.getCurrentUrl();
	}
	
	
}

