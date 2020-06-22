package com.bdd.automation.framework.commonstepdefination;

import com.bdd.automation.framework.core.BaseStep;
import com.bdd.automation.framework.pages.LoginPage;

import cucumber.api.java.en.Given;

public class commonStepDefination extends BaseStep{
	
	public commonStepDefination() {
		initialize();
	}
	
	@Given("^user login into Paytm$")
	public void user_login_into_flipkart()  {
	    try {
			LoginPage.login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
