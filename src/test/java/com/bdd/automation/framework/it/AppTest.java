package com.bdd.automation.framework.it;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.bdd.automation.framework.core.BaseStep;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/html/Report.html" ,"json:target/html/cucumber.json"}, features = {""},
glue = {""}, tags = {""}, monochrome= true)
	
public class AppTest {
	@AfterClass
	public static void tearDown() {
		BaseStep.tearDown();
	}
}