package com.cerner.auto.testpages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.TestCase;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cerner.a11y.auto.core.TargetBrowser;


public class TestPage1 extends TestCase{

	@Test
	public  void test1() throws Exception{
		
		TargetBrowser browser = new TargetBrowser();
		String exeLocation = "C:/Users/C15602/Desktop/AxeImp/A11YAutoEvaluator/browser/chromedriver.exe";
		WebDriver webDriver = browser.toUse(TargetBrowser.BROWSER.IEXPLORER).
									webDriverWithExeAt(exeLocation);
				
		//webDriver.get("http://discernabu.cerner.corp:3000/");
		/*webDriver.get("http://localhost:9001/test.htm");
		webDriver.switchTo().defaultContent();
		webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[contains(@src,'Frame1.htm')]")));
		WebElement Frame1Btn =webDriver.findElement(By.xpath("//button[contains(text(),'Change Content')]"));
		Frame1Btn.click();
		webDriver.switchTo().defaultContent();
		webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[contains(@src,'Frame2.htm')]")));
		WebElement Frame2Btn =webDriver.findElement(By.xpath("//input[contains(@onclick,'makePage()')]"));
		Frame2Btn.click();*/
		
		webDriver.get("http://localhost:3005");
        Thread.sleep(3000);
        WebElement ele = webDriver.findElement(By.xpath("//input[contains(@name,'username')]"));
        System.out.println("");
        ele.sendKeys("rajiv");
        WebElement Password = webDriver.findElement(By.xpath("//input[contains(@name,'password')]"));
        Password.sendKeys("Pass_enter");
        
        webDriver.findElement(By.xpath("//input[contains(@name,'password')]")).submit();
        
        
         webDriver.get(webDriver.getCurrentUrl());
          
         WebElement ele1 = webDriver.findElement(By.xpath("//input[contains(@id,'solutionname')]"));
        System.out.println("");
        ele1.sendKeys("rajiv");
        
        /*System.out.println("YYYYYY");
       URL url = new URL(new URL("file:"), "resource/discermabu.js");
        getContents(url );
        System.out.println("ZZZZZ");*/
       /* webDriver.findElement(By.xpath("//a[contains(@title,'Tables')]")).click();
        
       webDriver.findElement(By.xpath("//span[contains(text(),'Typography')]")).click();
       webDriver.findElement(By.xpath("//a[contains(@title,'Content & Containers')]")).click();
       webDriver.findElement(By.xpath("//span[contains(text(),'Component')]")).click();
       webDriver.findElement(By.xpath("//a[contains(@title,'Other')]")).click();
       Thread.sleep(2000);
       webDriver.findElement(By.xpath("//div[contains(text(),'State')]")).click();
       webDriver.findElement(By.xpath("//div[contains(text(),'Buttons & Selection')]")).click();
       webDriver.findElement(By.xpath("//div[contains(text(),'Forms')]")).click();
       webDriver.findElement(By.xpath("//div[contains(text(),'Popup')]")).click();
       webDriver.findElement(By.xpath("//div[contains(text(),'Search')]")).click();
       webDriver.findElement(By.xpath("//div[contains(text(),'Helpers')]")).click();
       webDriver.findElement(By.xpath("//div[contains(text(),'Navigation')]")).click();
       webDriver.findElement(By.xpath("//div[contains(text(),'Tagging')]")).click();
       
		*/
	
	}
	private static final String lineSeparator = System.getProperty("line.separator");
	private static String getContents(final URL script) {
		final StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		
		try {
			URLConnection connection = script.openConnection();
			
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			System.out.println("XXXXX");
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append(lineSeparator);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try { reader.close(); }
                catch (IOException ignored) {}
			}
		}
		System.out.println(sb);
		return sb.toString();
	}
	
}
