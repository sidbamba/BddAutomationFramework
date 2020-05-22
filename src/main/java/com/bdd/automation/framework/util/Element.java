package com.bdd.automation.framework.util;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Element {
	
	public static final String DEFAULT_PLACEHOLDER = "##PLACEHOLDER##";
	public static final Logger LOGGER = LoggerFactory.getLogger(Element.class);
	
		public static Optional<WebElement> xpath(WebDriver driver, String xpath, String... replacement){
			try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5,TimeUnit.SECONDS)
			.pollingEvery(500, TimeUnit.MILLISECONDS);
			String path = FrameworkProperties.getProperty(xpath);
			if(replacement.length >= 1) {
				path = path.replace(DEFAULT_PLACEHOLDER,replacement[0]);
			}
			return Optional.of(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path))));
			}catch (Exception e) {
				LOGGER.error(String.format("error fetching element by path %s", xpath));
				return Optional.empty();
			}
			
			
		}
		
		public static List<WebElement> elementsList(WebDriver driver,String xpath) {
			try {
				String path = FrameworkProperties.getProperty(xpath);
				return driver.findElements(By.xpath(path));
			}
			catch (Exception e) {
				LOGGER.error(String.format("error fetching elements by path %s", xpath));
				return Collections.emptyList();
				// TODO: handle exception
			}
		}
}
