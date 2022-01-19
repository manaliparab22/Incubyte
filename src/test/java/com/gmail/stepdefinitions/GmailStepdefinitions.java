package com.gmail.stepdefinitions;

import com.gmail.helper.CommonHelper;
import com.gmail.helper.Gmail;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GmailStepdefinitions extends CommonHelper {
	CommonHelper commonHelpher;

	public GmailStepdefinitions() throws Exception {
		super();
		commonHelpher = new CommonHelper();
	}

	@Given("^user launches \"([^\"]*)\" website$")
	public void userLaunchesWebsite(String environment) throws Exception {
		try {
			Gmail.userLaunchesWebsite(environment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("^user submit username and password$")
	public void userSubmitUsernameAndPassword() {
		try {
			Gmail.userSubmitUsernameAndPassword();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^user should be logged in$")
	public void userShouldBeLoggedIn() {
		try {
			Gmail.verifyUserLoggedIn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("^user read the test data sheet \"([^\"]*)\" from file \"([^\"]*)\"$")
	public void userReadTheTestDataSheetFromFile(String packtSheet, String packtTestdata) throws Throwable {
		try {
			commonHelpher.readExcel(packtSheet, packtTestdata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("^user clicks on \"([^\"]*)\" button$")
	public void userClicksOnButton(String buttonName) throws Throwable {
		try {
			Gmail.userClicksOnButton(buttonName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("^enters email body with subject$")
	public void entersEmailBodyWithSubject() throws Throwable {
		try {
			Gmail.entersEmailBodyWithSubject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^verify email is sent$")
	public void verifyEmailIsSent() throws Throwable {
		try {
			Gmail.verifyEmailIsSent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("^user signs out$")
	public void userSignsOut() throws Throwable {
		try {
			Gmail.userSignsOut();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("^user closes the browser$")
	public void userClosesTheBrowser() throws Throwable {
		try {
			CommonHelper.closeBrowser();
			CommonHelper.endExtendReport();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}