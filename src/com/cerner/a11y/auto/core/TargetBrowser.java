package com.cerner.a11y.auto.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TargetBrowser {
	public static enum BROWSER{FIREFOX,CHROME,IEXPLORER,SAFARI};
	private BROWSER chosen;
	protected EventFiringWebDriver eventFiringDriverForA11y;
	protected A11YCapableWebDriverEventListener a11yListerner;
	
	public TargetBrowser toUse(BROWSER browser){
		chosen = browser;
		return this;
	}
	
	public WebDriver webDriverWithExeAt(String exeLocation){
		WebDriver driver = null;
		try {
			
			if (chosen == BROWSER.FIREFOX) {

				/*DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
				desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
				FirefoxProfile fprofile = new FirefoxProfile();
				
				//fprofile.setPreference("browser.download.dir", ReportPath.getInstance().getReportPath());
				fprofile.setPreference("browser.download.folderList", 2); 
				fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"+ "application/pdf;" 
				+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" + "text/plain;" + "text/csv"); 
				fprofile.setPreference( "browser.download.manager.showWhenStarting", false ); 
				fprofile.setPreference( "pdfjs.disabled", true ); 
				//driver = new FirefoxDriver(fprofile);	
				driver = new FirefoxDriver();
			}
			if(chosen == BROWSER.CHROME){
				System.setProperty("webdriver.chrome.driver",exeLocation);
				DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
				
				desiredCapabilities.setCapability("start-maximized",true);
				desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver=new ChromeDriver(desiredCapabilities);
				
			}
			if(chosen == BROWSER.IEXPLORER){
				System.setProperty("webdriver.ie.driver",exeLocation);
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability("nativeEvents", false);
				ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
				ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
				ieCapabilities.setCapability("disable-popup-blocking", true);
				ieCapabilities.setCapability("enablePersistentHover", true);
				ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				driver = new InternetExplorerDriver(ieCapabilities);
			}
			if(chosen == BROWSER.SAFARI){

				SafariOptions safariOptions = new SafariOptions();
				safariOptions.setUseCleanSession(true);
				driver=new SafariDriver(safariOptions);

			}
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			eventFiringDriverForA11y = new EventFiringWebDriver(driver);
			a11yListerner = new A11YCapableWebDriverEventListener();
			eventFiringDriverForA11y.register(a11yListerner);
			
			
		} catch (Exception exception) {
			//TBD Logger
			//report.reportException("getDriver", new RuntimeException(exception.getMessage()));
		} 	
		return eventFiringDriverForA11y;
		

	}
	
	
}
