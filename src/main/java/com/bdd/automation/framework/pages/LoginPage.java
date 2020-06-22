package com.bdd.automation.framework.pages;



import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.bdd.automation.framework.core.BaseStep;
import com.bdd.automation.framework.util.Element;
import com.bdd.automation.framework.util.EncryptHelper;
import com.bdd.automation.framework.util.FrameworkProperties;

public class LoginPage extends BaseStep {
	
		public static void login() throws Exception {
		//	String userName = FrameworkProperties.getProperty("user.valid.email");
		//	String password = FrameworkProperties.getProperty("user.valid.password");
			if(Element.xpath(driver,"login.button").isPresent()) {
				Element.xpath(driver,"login.button").get().click();
				Thread.sleep(50000);
		//		Element.xpath(driver, "name.login").get().click();
		//		Element.xpath(driver, "name.login").get().sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.BACK_SPACE,userName);
		//		Element.xpath(driver, "name.password").get().sendKeys(EncryptHelper.decrypt(password).toString(),Keys.ENTER);
			}
		}
}
