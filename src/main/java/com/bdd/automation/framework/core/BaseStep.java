package com.bdd.automation.framework.core;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdd.automation.framework.util.FrameworkProperties;

public class BaseStep {
		public static WebDriver driver;
		
		public static Logger LOGGER = LoggerFactory.getLogger(BaseStep.class);
		
		public static int retry = 0;
		
		public static void initialize() {
			if (driver == null) {
				System.out.println("Webdriver is Null. Initializing it");
				String browser = FrameworkProperties.getProperty("webdriver.name");
				initializeDriver(browser);
			}
		}
		
		enum Browser{
			CHROME,FIREFOX;
		}
		private static void initializeDriver(String browser) {
			Browser selection = Browser.valueOf(browser);
			
			switch(selection){
			case CHROME:
				try {
					if(System.getProperty("os.name").toLowerCase().contains("windows")) {
						System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + FrameworkProperties.getProperty("webdriver.path"));
					}
					//for linux
					else {
						System.setProperty("webdriver.chrome.driver","/home/sousr/Downloads/chromedriver");
					}
					driver = new ChromeDriver();
					retry++;
					break;			
				}catch(Exception e) {
					System.out.println("chrome didn't start trying again");
					
					if(retry < 2) {
						initializeDriver(browser);
					}else {
						e.printStackTrace();
						System.exit(1);
					}
				}
			case FIREFOX:
				try {
					if(System.getProperty("os.name").toLowerCase().contains("windows")) {
						System.setProperty("webdriver.firefox.driver",System.getProperty("user.dir") + FrameworkProperties.getProperty("webdriver.name"));
					}
					//for linux
					else {
						System.setProperty("webdriver.firefox.driver", "/home/sousr/Downloads/chromedriver");
					}
					driver = new FirefoxDriver();
					break;
				}catch(Exception e) {
					System.out.println("firefox didn't start trying again");
					
					if(retry < 2) {
						initializeDriver(browser);
					}else {
						e.printStackTrace();
						System.exit(1);
					}
				}
			default:
				LOGGER.debug("wrong value for driver");
				throw new RuntimeException("browser value null");
			}
		}
		
		protected static void refresh() {
			driver.navigate().refresh();
		}
		
		public static void killChromeDriver() {
			Process p;
			try {
			String driverVersion  = FrameworkProperties.getProperty("webdriver.path");
			p = Runtime.getRuntime().exec("taskkill /F /IM " + driverVersion);
			p.destroyForcibly();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
		}
}
