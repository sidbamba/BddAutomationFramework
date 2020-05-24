package com.bdd.automation.framework.core;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.bdd.automation.framework.util.FrameworkProperties;

public class BaseStep {
		public static WebDriver driver;
		
		public static Logger LOGGER = Logger.getLogger(BaseStep.class);
		
		public static int retry = 0;
		
		public static void initialize() {
			if (driver == null) {
				System.out.println("Webdriver is Null. Initializing it");
				String browser = FrameworkProperties.getProperty("webdriver.name");
				initializeDriver(browser);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				String website = FrameworkProperties.getProperty("get.url");
				driver.get(website);
			}
			else {
				String website = FrameworkProperties.getProperty("get.url");
				driver.get(website);
			}
			driver.manage().window().maximize();
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
					System.out.println(System.getProperty("webdriver.chrome.driver"));
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
		public static void tearDown() {
			Process p;
			if(driver != null && !driver.toString().contains("null")) {
				driver.close();
				driver.quit();
				driver = null;	
			}
			else if(driver.toString().contains(null)) {
				driver.quit();
				driver = null;
			}
				
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
