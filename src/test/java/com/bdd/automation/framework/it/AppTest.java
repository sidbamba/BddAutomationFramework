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
@CucumberOptions(plugin ="json:target/jsonReports/cucumber.json", features = {"src\\test\\resources\\bdd\\cukes\\orders\\HomePage\\HomeTest.feature"},
glue = {"com.bdd.automation.framework"})
	
public class AppTest {
	@AfterClass
	public static void tearDown() {
		BaseStep.tearDown();
	}
}